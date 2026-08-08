package dgf.xfa22c.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {

    private static final Properties props = new Properties();

    static {
        try(FileInputStream fis = new FileInputStream("DB_config.properties")){
            props.load(fis);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getUrl(){
        return props.getProperty("db.url");
    }

    public static String getUser(){
        return props.getProperty("db.user");
    }

    public static String getPassword(){
        return props.getProperty("db.password");
    }

}
