package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        try {
            req.setCharacterEncoding("UTF8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding type setting exception " + e.getMessage());
        }

        Cookie[] cookies = req.getCookies();
        String username = "Stranger";

        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("name".equals(cookie.getName())){
                    username = cookie.getValue();
                }
            }
        }

        String newName = req.getParameter("name");
        String errorMessage = null;

        if (newName != null && !newName.isEmpty()){
            if (newName.matches("[a-zA-Z0-9_-]+$")){
                Cookie nameCookie = new Cookie("name", newName);
                nameCookie.setMaxAge(60*60*24);
                resp.addCookie(nameCookie);
                username = newName;
            }else {
                errorMessage = "АНГЛИЙСКИМИ буквами нужно";
            }
        }

        resp.setContentType("text/html; charset=UTF-8");
        try {
            resp.getWriter().println("Привет " + username);

            if (errorMessage != null){
                resp.getWriter().println("<br><b style='color:red'>" + errorMessage + "</b>");
            }
        } catch (IOException e) {
            System.out.println("Print name exception " + e.getMessage());
        }


    }

}
