package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession();
        Integer count = (Integer) session.getAttribute("count");
        if (count == null){
            count = 0;
        }
        count++;
        session.setAttribute("count", count);

        resp.setContentType("text/html; charset=UTF-8");
        try {
            resp.getWriter().println("Вы посетили эту страницу - " + count + " Раз");
        } catch (IOException e) {
            System.out.println("Print exception " + e.getMessage());
        }
    }

}
