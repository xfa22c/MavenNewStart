package dgf.xfa22c.miniJakartaProject.servlets;

import dgf.xfa22c.miniJakartaProject.dto.ManufacturerDTO;
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
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String path = req.getPathInfo();

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

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getPathInfo();

            if ("/deleteManufacturer".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()) {
                    Long lId = Long.parseLong(id);
                    ms.deleteManufacturer(lId);
                }
                resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
                return;
            }

        String name = req.getParameter("name");
        String yearOfCreation = req.getParameter("yearOfCreation");

        ManufacturerDTO dto = new ManufacturerDTO();
        dto.setName(name);

        try {
            if (yearOfCreation == null) {
                throw new IllegalArgumentException("Year of Creation is null");
            }
            dto.setYearOfCreation(Integer.parseInt(yearOfCreation));

            if ("/addManufacturer".equals(path)){
                ms.addManufacturer(dto);
            } else if ("/editManufacturer".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    Long lId = Long.parseLong(id);
                    ms.updateManufacturer(dto, lId);
                }
            }

            resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
        }catch (NumberFormatException e){
            forwardWithError(req, resp, path, "All numeric fields must contain numbers");
        }catch (IllegalArgumentException e){
            forwardWithError(req, resp, path, e.getMessage());
        }

    }

    private void forwardWithError(HttpServletRequest req, HttpServletResponse resp, String path, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        if ("/editManufacturer".equals(path)){
            req.getRequestDispatcher("/editManufacturer.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/addManufacturer.jsp").forward(req, resp);
        }
    }

}
