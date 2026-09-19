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

        String path = req.getPathInfo();

        try {
            if ("/deleteManufacturer".equals(path)) {
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()) {
                    Long lId = Long.parseLong(id);
                    ms.deleteManufacturer(lId);
                }
                resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
                return;
            }
        }catch (IOException e) {
            System.err.println("Redirect exception (delete) " + e.getMessage());
        }

        String name = req.getParameter("name");
        String yearOfCreation = req.getParameter("yearOfCreation");
        if (yearOfCreation == null) {
            System.err.println("Year of Creation is null");
            throw new IllegalArgumentException("Year of Creation is null");
        }
        int year;
        try {
            year = Integer.parseInt(yearOfCreation);

        }catch (NumberFormatException e){
            System.err.println("Year of creation is not number");
            throw new IllegalArgumentException("Year must be a number");
        }

        ManufacturerDTO dto = new ManufacturerDTO();
        dto.setName(name);
        dto.setYearOfCreation(year);

        if ("/addManufacturer".equals(path)){
            ms.addManufacturer(dto);
        } else if ("/editManufacturer".equals(path)) {
            String id = req.getParameter("id");
            if (id != null && !id.isBlank()){
                Long lId = Long.parseLong(id);
                ms.updateManufacturer(dto, lId);
            }
        }

        try {
            resp.sendRedirect(req.getContextPath() + "/Manufacturer/listManufacturers?pas=blankEnd");
        } catch (IOException e) {
            System.err.println("Redirect exception " + e.getMessage());
        }

    }

}
