package dgf.xfa22c.miniJakartaProject.servlets;

import dgf.xfa22c.miniJakartaProject.dto.MouseDTO;
import dgf.xfa22c.miniJakartaProject.entities.MouseEntity;
import dgf.xfa22c.miniJakartaProject.service.MiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/Mouse/*")
public class MouseServlet extends HttpServlet {
    private final MiceService ms = new MiceService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getPathInfo();

           if (path == null || "/mouseList".equals(path)) {
               req.setAttribute("mice", ms.listMice());
               req.getRequestDispatcher("/listMice.jsp").forward(req, resp);
           } else if ("/addMouse".equals(path)) {
               req.setAttribute("manufacturers", ms.listManufacturers());
               req.getRequestDispatcher("/addMouse.jsp").forward(req,resp);
           }else if ("/editMouse".equals(path)){
               String id = req.getParameter("id");
               if (id != null && !id.isBlank()){
                   Long lId = Long.parseLong(id);
                   req.setAttribute("manufacturers", ms.listManufacturers());
                   MouseEntity mouse = ms.getMouseById(lId);
                   req.setAttribute("mouse", mouse);
                   req.getRequestDispatcher("/editMouse.jsp").forward(req, resp);
               }else{
                  resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");
               }

           }else{
               resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");
           }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getPathInfo();

            if ("/delete".equals(path)){
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    Long lId = Long.parseLong(id);
                    ms.deleteMouse(lId);
                }
                resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");
                return;
            }


        String name = req.getParameter("name");
        String sensor = req.getParameter("sensor");
        String maxAccelParam = req.getParameter("maxAccel");
        String pollingRateParam = req.getParameter("pollingRate");
        String priceParam = req.getParameter("price");
        String manufacturerIdParam = req.getParameter("manufacturerId");

        MouseDTO mouseDTO = new MouseDTO();
        mouseDTO.setName(name);
        mouseDTO.setSensor(sensor);

        try {

            if (maxAccelParam == null || pollingRateParam == null || priceParam == null) {
                throw new IllegalArgumentException("All fields are required");
            }

            if ("/addMouse".equals(path) && manufacturerIdParam == null) {
                throw new IllegalArgumentException("Manufacturer is required");
            }

            mouseDTO.setMaxAcceleration(Integer.parseInt(maxAccelParam));
            mouseDTO.setPollingRate(Integer.parseInt(pollingRateParam));
            mouseDTO.setPrice(Integer.parseInt(priceParam));

            if (manufacturerIdParam != null && !manufacturerIdParam.isBlank()) {
                mouseDTO.setManufacturerId(Long.parseLong(manufacturerIdParam));
            }

        } catch (NumberFormatException e) {
            forwardWithError(req, resp, path, "All numeric fields must contain numbers");
            return;
        }catch (IllegalArgumentException e){
            forwardWithError(req, resp, path, e.getMessage());
            return;
        }

        try {
            if ("/addMouse".equals(path)) {
                ms.addMouse(mouseDTO);
            } else if ("/editMouse".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()) {
                    Long lId = Long.parseLong(id);
                    ms.updateMouse(mouseDTO, lId);
                }
            }

            resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");

        }catch (IllegalArgumentException e) {
            forwardWithError(req, resp, path, e.getMessage());
        }

    }

    private void forwardWithError(HttpServletRequest req, HttpServletResponse resp, String path, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        if ("/editMouse".equals(path)){
            req.getRequestDispatcher("/editMouse.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/addMouse.jsp").forward(req, resp);
        }
    }

}
