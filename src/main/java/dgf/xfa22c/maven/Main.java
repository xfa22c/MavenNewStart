package dgf.xfa22c.maven;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        User u = new  User();
        u.setName("Ангелина");
        u.setAge(20);
        u.setEmail("NakiriA@mail.com");

        System.out.println(u);

        System.out.println();
        System.out.print(u.getName() + " ");
        System.out.print(u.getAge() + " ");
        System.out.print(u.getEmail());
        System.out.println();

        User uC = new User("Ангелина()", 20, "NakiriA@mail.com");
        System.out.println(uC);
        System.out.println();

        System.out.println("Введите имя");
        String name = sc.next();
        System.out.println("Введите возраст");
        int age = getInt();
        if (age < 0 || age > 130){
            System.out.println("Неподходящий возраст");
        }else{
            System.out.println("Введите E mail");
            String email = sc.next();
            if (email.contains("@")){
                User user = new User(name, age, email);
                System.out.println(user);
            }else{
                System.err.println("Это не эмейл");
            }
        }

    }

    public static int getInt(){
        while(!sc.hasNextInt()){
            String input = sc.next();
            System.err.println("В жопу свой '" + input + "' засунь, ладно?");
        }
        return sc.nextInt();
    }

}
