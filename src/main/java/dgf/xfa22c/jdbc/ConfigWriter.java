package dgf.xfa22c.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigWriter {
    public static void main(String[] args) {
        Properties props = new Properties();

        props.setProperty("db.url", "jdbc:postgresql://localhost:5432/repeat_db");
        props.setProperty("db.user", "postgres");
        props.setProperty("db.password", "[ДАННЫЕ УДАЛЕНЫ]");

        try (FileOutputStream out = new FileOutputStream("DB_config.properties")) {
            props.store(out, "Database Config");
            out.flush();
            System.out.println("Stuffs wrote");
        } catch (IOException e) {
            System.err.println("IO Exception while writing " + e.getMessage());
        }
    }
}
