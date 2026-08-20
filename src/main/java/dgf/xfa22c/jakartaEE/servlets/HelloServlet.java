package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/hello",
        initParams = {
        @WebInitParam(name = "greeting", value = "Hej världen!"),
                @WebInitParam(name = "question", value = "Hur mår du?")
        }
)
public class HelloServlet extends HttpServlet {

    private String greeting;
    private String question;

    @Override
    public void init(ServletConfig config){
        try {
            super.init(config);

            this.greeting = getInitParameter("greeting");
            this.question = getInitParameter("question");
        } catch (ServletException e) {
            System.out.println("Servlet exception while getting config " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
            resp.setContentType("text/html; charset=UTF-8");
        try {
            resp.getWriter().println("<h1>"+ greeting + "</h1>");
            resp.getWriter().println("<p>" + question +"</p>");
        } catch (IOException e) {
            System.err.println("Something went wrong - " + e.getMessage());
        }
    }

}
