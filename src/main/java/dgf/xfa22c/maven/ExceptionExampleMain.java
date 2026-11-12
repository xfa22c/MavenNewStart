package dgf.xfa22c.maven;

import dgf.xfa22c.exceptions.TooYoungException;

public class ExceptionExampleMain {

    public static void main(String[] args) {

        try {
            ExceptionExampleMethod.checkAge(20);
        }catch (TooYoungException e){
            System.out.println("Age is not 18 or above");
        }catch (IllegalArgumentException e){
            System.out.println("Age is over 100");
        }


    }

}
