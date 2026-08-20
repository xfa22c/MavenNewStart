package dgf.xfa22c.jakartaEE.servlets.listeners;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

@WebListener
public class RequestLoggerListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre){
        if (!(sre.getServletRequest() instanceof HttpServletRequest req)){
            return;
        }

        String clientIp = req.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)){
            clientIp = req.getRemoteAddr();
        }else {
            clientIp = clientIp.split(",")[0].trim();
        }

        String method = req.getMethod();
        String uri = req.getRequestURI();
        String userAgent = req.getHeader("User-Agent");

        System.out.println("--------------------------------------------------");
        System.out.printf("[REQUEST] %s %s%n", method, uri);
        System.out.printf("  IP:         %s%n", clientIp);
        System.out.printf("  User-Agent: %s%n", userAgent != null ? userAgent : "N/A");
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre){
        ServletRequestListener.super.requestDestroyed(sre);
    }

}
