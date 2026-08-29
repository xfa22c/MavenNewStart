package dgf.xfa22c.sqlConnection;

public class MiceAndManufacturerMain {

    public static void main(String[] args) {

        MiceManufacturerService manufacturerService = new MiceManufacturerService();
        MouseService mouseService = new MouseService();


        MiceManufacturer manufacturer = new MiceManufacturer();
        manufacturer.setBrandName("ATK");
        manufacturerService.addMouseManufacturer(manufacturer);

        Mouse mouse = new Mouse(null, "PAW9995", 50, 16000, null);
        mouseService.addMouse(mouse, manufacturer.getId());

        System.out.println("--- Список производителей ---");
        manufacturerService.listManufacturers();

        System.out.println("--- Список мышей ---");
        mouseService.listMice();

        mouseService.close();
        manufacturerService.close();

    }

}
