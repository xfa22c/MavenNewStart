package dgf.xfa22c.jakartaEE.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@WebServlet("/logs")
public class StudyLogServlet extends HttpServlet {

    private final Path path = Path.of("study_log_web.txt");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        if (!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.err.println("IO Exception  " +e.getMessage());
            }
        }

        try {
            List<String> logs = Files.readAllLines(path);
            Collections.reverse(logs);
            req.setAttribute("logs", logs);
            req.getRequestDispatcher("/logs.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            System.err.println("IO exception - Reading all lines || Servlet Exception  " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String topic = req.getParameter("topic");
        String timeSpent = req.getParameter("timeSpent");
        if (topic != null && !topic.isBlank()){
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            String logEntry = currentDate + " -- " + timeSpent + " Мин " + "(" + topic + ")\n";

            try {
                if (Files.exists(path) && Files.size(path) > 0){
                    String content = Files.readString(path);
                    if (!content.endsWith("\n")){
                        logEntry = "\n" + logEntry;
                    }
                }
                try {
                    Files.writeString(path, logEntry, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.err.println("IO Exception - FilesWriteString  " + e.getMessage());
                }
            } catch (IOException e) {
                System.err.println("Files Size Exception " + e.getMessage());
            }
        }

        try {
            resp.sendRedirect("logs");
        } catch (IOException e) {
            System.out.println("IO Redirect Exception  " + e.getMessage());
        }
    }
}
