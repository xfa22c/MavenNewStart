package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
            resp.setContentType("text/html; charset=UTF-8");
        try {
            resp.getWriter().println("<h1>Hej världen!</h1>");
            resp.getWriter().println("<p>Hur mår du?</p>");
        } catch (IOException e) {
            System.err.println("Something went wrong - " + e.getMessage());
        }
    }

}
