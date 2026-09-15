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
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getPathInfo();

        try {
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
                  resp.sendRedirect("/Mouse/mouseList?pas=blankEnd");
               }

           }else{
               resp.sendRedirect("/Mouse/mouseList?pas=blankEnd");
           }
        } catch (ServletException | IOException e) {
            System.err.println("Forward exception doGet " + e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        String path = req.getPathInfo();

        try {
            if ("/delete".equals(path)){
                String id = req.getParameter("id");
                if (id != null && !id.isBlank()){
                    Long lId = Long.parseLong(id);
                    ms.deleteMouse(lId);
                }
                resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");
                return;
            }
        } catch (IOException e) {
            System.out.println("Redirect Exception (delete) " + e.getMessage());
        }

        String name = req.getParameter("name");
        String sensor = req.getParameter("sensor");
        String maxAccelParam = req.getParameter("maxAccel");
        String pollingRateParam = req.getParameter("pollingRate");
        String priceParam = req.getParameter("price");
        String manufacturerIdParam = req.getParameter("manufacturerId");
        if (maxAccelParam == null){
            System.err.println("Max Acceleration is unknown");
            throw new IllegalArgumentException();
        }
        if (pollingRateParam == null){
            System.err.println("Polling Rate is unknown");
            throw new IllegalArgumentException();
        }
        if (priceParam == null){
            System.err.println("Price is null");
            throw new IllegalArgumentException("Price is null");
        }
        if (manufacturerIdParam == null){
            System.err.println("ManufacturerID is null");
            throw new IllegalArgumentException("ManufacturerID is null");
        }

        MouseDTO mouseDTO = new MouseDTO();
        mouseDTO.setName(name);
        mouseDTO.setMaxAcceleration(Integer.parseInt(maxAccelParam));
        mouseDTO.setPollingRate(Integer.parseInt(pollingRateParam));
        mouseDTO.setPrice(Integer.parseInt(priceParam));
        mouseDTO.setManufacturerId(Long.parseLong(manufacturerIdParam));
        mouseDTO.setSensor(sensor);

        if ("/addMouse".equals(path)){
            ms.addMouse(mouseDTO);
        } else if ("/editMouse".equals(path)) {
            String id = req.getParameter("id");
            if (id != null && !id.isBlank()){
                Long lId = Long.parseLong(id);
                ms.updateMouse(mouseDTO, lId);
            }
        }

        try {
            resp.sendRedirect(req.getContextPath() + "/Mouse/mouseList?pas=blankEnd");
        } catch (IOException e) {
            System.err.println("Redirect exception " + e.getMessage());
        }

    }

}
