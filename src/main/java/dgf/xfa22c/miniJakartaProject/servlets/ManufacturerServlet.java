package dgf.xfa22c.miniJakartaProject.servlets;

import dgf.xfa22c.miniJakartaProject.entities.ManufacturerEntity;
import dgf.xfa22c.miniJakartaProject.service.MiceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Manufacturer/*")
public class ManufacturerServlet extends HttpServlet {
    private final MiceService ms = new MiceService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getPathInfo();

        try {
            if (path == null || "/listManufacturers".equals(path)){
                req.setAttribute("manufacturers", ms.listManufacturers());
                req.getRequestDispatcher("/listManufacturers.jsp").forward(req, resp);
            } else if ("/addManufacturer".equals(path)) {
                req.getRequestDispatcher("/addManufacturer.jsp").forward(req, resp);
            } else if ("/editManufacturer".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    Long lId = Long.parseLong(id);
                    ManufacturerEntity manufacturer = ms.getManufacturerById(lId);
                    req.setAttribute("manufacturer", manufacturer);
                    req.getRequestDispatcher("/editManufacturer.jsp").forward(req, resp);
                }else{
                    resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
                }
            }else{
                resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
            }

        }catch(ServletException | IOException e) {
            System.err.println("Forward exception doGet " + e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){

    }

}
