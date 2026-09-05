package dgf.xfa22c.miniJakartaProject.servlets;

import dgf.xfa22c.miniJakartaProject.dto.UserDTO;
import dgf.xfa22c.miniJakartaProject.entities.UserEntity;
import dgf.xfa22c.miniJakartaProject.service.UserEntityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usersweb/*")
public class UserServlet extends HttpServlet {

    private final UserEntityService ues = new UserEntityService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getPathInfo();

        try {
            if (path == null || "/userslist".equals(path)) {
                req.setAttribute("users", ues.listUsers());
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
            } else if("/add".equals(path)) {
                req.getRequestDispatcher("/userAdd.jsp").forward(req, resp);
            } else if("/edit".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()) {
                    Long lId = Long.parseLong(id);
                    UserEntity user = ues.getUserByID(lId);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect(req.getContextPath() + "/usersweb/userslist?pas=blankEnd");
                }



            } else {
                resp.sendRedirect(req.getContextPath() + "/usersweb/userslist?pas=blankEnd");
            }
        } catch (ServletException | IOException e) {
            System.err.println("Forwarding error: " + e.getMessage());
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getPathInfo();

        try{
            if ("/delete".equals(path)){
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    Long lId = Long.parseLong(id);
                    ues.deleteUser(lId);
                }
                resp.sendRedirect(req.getContextPath() + "/usersweb/userslist?pas=blankEnd");
                return;
            }
        } catch (IOException e) {
            System.err.println("Redirect Exception " + e.getMessage());
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String ageParam = req.getParameter("age");
        int age = (ageParam != null && !ageParam.isBlank()) ? Integer.parseInt(ageParam) : 0;

            UserDTO userDTO = new UserDTO();
            userDTO.setName(name);
            userDTO.setEmail(email);
            userDTO.setAge(age);


            if ("/add".equals(path)){
                ues.addUser(userDTO.toUser());
            } else if ("/edit".equals(path)){
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    ues.updateUser(userDTO, Long.parseLong(id));
                }
            }

            try {
                resp.sendRedirect(req.getContextPath() + "/usersweb/userslist?pas=blankEnd");
            } catch (IOException e) {
                System.out.println("Redirect Exception " + e.getMessage());
            }

    }

}
