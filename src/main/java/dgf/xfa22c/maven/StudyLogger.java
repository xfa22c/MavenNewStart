package dgf.xfa22c.maven;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StudyLogger {

    private static final String LOG_FILE = "study_logs_DEPRECATED.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @SuppressWarnings("unused")
    public static void logLesson(String topic, int min){
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(LOG_FILE, true))){
            String date = LocalDateTime.now().format(DATE_FORMATTER);
            fileWriter.write(date + " -- " + min + " мин (" + topic + ")" + System.lineSeparator());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @SuppressWarnings("unused")
    public static void showLog(){
        try(BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))){
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
