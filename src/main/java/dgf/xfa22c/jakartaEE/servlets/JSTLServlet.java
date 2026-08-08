package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/JSTL")
public class JSTLServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("name", "Angelina");
        req.setAttribute("numbers", List.of(1, 2, 3, 4, 5, 6));

        try {
            req.getRequestDispatcher("jstlExample.jsp").forward(req, resp);
        } catch (ServletException| IOException e) {
            System.err.println("Something went wrong  " + e.getMessage());
        }
    }

}
