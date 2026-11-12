package dgf.xfa22c.maven;

import dgf.xfa22c.exceptions.TooYoungException;

public class ExceptionExampleMethod {

    public static void checkAge(int age){
        if (age < 18){
            throw new TooYoungException("User is not 18 or above");
        } else if (age > 100) {
            throw new IllegalArgumentException("User is too old");
        }else{
            System.out.println("Access granted");
        }
    }

}
