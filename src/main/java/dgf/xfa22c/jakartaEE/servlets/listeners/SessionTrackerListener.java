package dgf.xfa22c.jakartaEE.servlets.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionTrackerListener implements HttpSessionListener {

    private static final AtomicInteger activeSessions = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se){
        int total = activeSessions.incrementAndGet();
        String sessionId = se.getSession().getId();
        System.out.printf("[SESSION START] ID: %s | Активных пользователей: %d%n", sessionId, total);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        int total = activeSessions.incrementAndGet();
        String sessionId = se.getSession().getId();
        System.out.printf("[SESSION END]   ID: %s | Активных пользователей: %d%n", sessionId, total);
    }

    public static int getActiveSessions(){
        return activeSessions.get();
    }

}
