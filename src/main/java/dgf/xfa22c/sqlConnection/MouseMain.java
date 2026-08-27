package dgf.xfa22c.sqlConnection;

public class MouseMain {

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Mouse mouse = new Mouse(null, "PAW3389", 45, 1500);

        MouseService ms = new MouseService();

        ms.listMice();

    }

}
