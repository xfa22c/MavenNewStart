package dgf.xfa22c.sqlConnection;

public class Main {

    public static void main(String[] args) {

        //Usual Object
        UserService u = new UserService();

        //Method Here
        u.listUsers();


        //Close Entity Manager and Entity Manager Factory
        u.close();

    }

}
