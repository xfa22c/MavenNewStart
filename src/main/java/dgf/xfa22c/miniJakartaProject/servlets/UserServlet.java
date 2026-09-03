package dgf.xfa22c.miniJakartaProject.servlets;

import jakarta.validation.ConstraintViolation;
import dgf.xfa22c.miniJakartaProject.dto.UserDTO;
import dgf.xfa22c.miniJakartaProject.entities.UserEntity;
import dgf.xfa22c.miniJakartaProject.service.UserEntityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/usersweb")
public class UserServlet extends HttpServlet {

    private final UserEntityService userEntityService = new UserEntityService();
    private Validator validator;

    @Override
    public void init(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        List<UserEntity> users = userEntityService.listUsers();
        req.setAttribute("users", users);
        try {
            req.getRequestDispatcher("/users.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            System.err.println(e.getMessage() + " Something went wrong on forward to JSP");
        }

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int age = Integer.parseInt(req.getParameter("age"));

        UserDTO userDTO = new UserDTO();
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setAge(age);

        Set<ConstraintViolation<UserDTO>> violation = validator.validate(userDTO);
        if (!violation.isEmpty()){
            req.setAttribute("errors", violation);
            doGet(req, resp);
            return;
        }
        UserEntity user = userDTO.toUser();
        userEntityService.addUser(user);

        try {
            resp.sendRedirect("usersweb?pas=blankEnd");
            System.out.println("doPost called");
        } catch (IOException e) {
            System.err.println("Redirect error: " + e.getMessage());
        }
    }

}
