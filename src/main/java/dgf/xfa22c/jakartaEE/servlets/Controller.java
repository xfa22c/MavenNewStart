package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebServlet("/Hi")
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        if (name == null){
            name = "Gäst";
        }
        req.setAttribute("userName", name);
        req.setAttribute("currentDate", new Date());
        try {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            System.err.println("Something Went Wrong " + e.getMessage());
        }
    }

}
