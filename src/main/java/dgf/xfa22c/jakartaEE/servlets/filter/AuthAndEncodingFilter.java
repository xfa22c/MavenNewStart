package dgf.xfa22c.jakartaEE.servlets.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter("/*")
public class AuthAndEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Encoding type set Exception " + e.getMessage());
        }

        HttpServletRequest hReq = (HttpServletRequest) req;
        HttpServletResponse hResp = (HttpServletResponse) resp;
        String pas = hReq.getParameter("pas");
        if (!"blankEnd".equals(pas)){
            try {
                hResp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                hResp.setContentType("text/plain; charset=UTF-8");
                hResp.getWriter().println("Ошибка 403: Forbidden");
            } catch (IOException e) {
                System.err.println("Writer Exception " + e.getMessage());
            }
        }else{
            try {
                chain.doFilter(req, resp);
            } catch (IOException e) {
                System.out.println("FilterChain IO Exception " + e.getMessage());
                throw e;
            } catch (ServletException e) {
                System.out.println("FilterChain Servlet exception " + e.getMessage());
                throw e;
            }
        }



    }

}
