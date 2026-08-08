package dgf.xfa22c.maven.repeatCRUD;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonService {
    static Scanner sc = new Scanner(System.in);
    static List<Person> personList =  new ArrayList<>();
    static int step = 0;

    public static void runLoop(){
        int ch;

        while(step == 0){
            menu();
            ch = getInt();
            step = handleChoice(ch);
        }


    }

    public static int handleChoice(int ch){
        switch (ch){
            case 1 :
                addPerson();
                return again();

            case 2 :
                listPersons();
                return again();

            case 3:
                editPerson();
                return again();
            case 4:
                deletePerson();
                return again();

            default :
                System.out.println("Такого метода нет");
                return step =1;


        }
    }

    public static void menu(){
        System.out.println("1. Добавить человека   2. Лист всех людей в коллекции");
        System.out.println("3. Редактировать человека   4. Удалить человека");
    }

    public static void addPerson(){
        System.out.println("Введите имя");
        String name = sc.next();
        System.out.println("Введите фамилию");
        String surname = sc.next();
        System.out.println("Введите возраст");
        int age = getInt();
        System.out.println("Введите электронную почту");
        String email = sc.next();
        if (!email.contains("@") && (email.length() < 6)){
            System.err.println("Это не электронная почта (Тугодум?)");
        }else{
            System.out.println("Введите номер телефона");
            String phone = sc.next();
            if (phone.contains("-") && phone.length() < 11){
                System.err.println("Это не номер телефона");
            }else{
                Person person = new Person(name, surname, age, email, phone);
                personList.add(person);
            }
        }
    }

    public static void editPerson(){
        System.out.println("Выберите человека");
        listPersons();
        int index = getInt();
        if (index < 0 || index >= personList.size()){
            System.err.println("Индекс введёт НЕПРАВИЛЬНО");
            step = 1;
            return;
        }
        Person person = personList.get(index);



        System.out.println("Что вы хотите изменить?");
        System.out.println("1. Всё   2. Один параметр");
        int choice = getInt();
        if (choice == 1) {
            System.out.println("Введите имя");
            String name = sc.next();
            System.out.println("Введите фамилию");
            String surname = sc.next();
            System.out.println("Введите возраст");
            int age = getInt();
            System.out.println("Введите электронную почту");
            String email = sc.next();
            if (!email.contains("@") && (email.length() < 6)){
                System.err.println("Это не электронная почта (Тугодум?)");
            }else{
                System.out.println("Введите номер телефона");
                String phone = sc.next();
                if (phone.contains("-") || phone.length() < 11){
                    System.err.println("Это не номер телефона");
                }else{
                    person.setFirstName(name);
                    person.setLastName(surname);
                    person.setAge(age);
                    person.setEmail(email);
                    person.setPhone(phone);
                    System.out.println("Параметры успешно изменены");
                }
            }
        }else{
            System.out.println("Выберите что хотите изменить");
            System.out.println("1. " + person.getFirstName());
            System.out.println("2. " + person.getLastName());
            System.out.println("3. " +person.getAge());
            System.out.println("4. " +person.getEmail());
            System.out.println("5. " +person.getPhone());
            int ch = getInt();
            switch (ch) {
                case 1 :
                    System.out.println("Введите новое имя");
                    String name = sc.next();
                    person.setFirstName(name);
                    System.out.println("Параметр изменён");
                    break;
                    case 2 :
                        System.out.println("Введите новую фамилию");
                        String surname = sc.next();
                        person.setLastName(surname);
                        System.out.println("Параметр изменён");
                        break;
                        case 3 :
                            System.out.println("Введите новый возраст");
                            int age = getInt();
                            person.setAge(age);
                            System.out.println("Параметр изменён");
                            break;
                            case 4 :
                                System.out.println("Введите новый Email");
                                String email = sc.next();
                                if (!email.contains("@") && (email.length() < 6)){
                                    System.err.println("Это не электронная почта (Тугодум?)");
                                }else{
                                    person.setEmail(email);
                                    System.out.println("Параметр изменён");
                                }
                                break;
                                case 5 :
                                    System.out.println("Введите новый номер телефона");
                                    String phone = sc.next();
                                    if (phone.contains("-") || phone.length() < 11){
                                        System.err.println("Это не номер телефона");
                                    }else{
                                        person.setPhone(phone);
                                        System.out.println("Параметр изменён");
                                    }
                                    break;
                default:
                    System.err.println("Такого говна нет");
                    step = 1;
                    break;

            }
        }
    }

    public static void deletePerson(){
        System.err.println("Выберите человека для удаления (Из мира)");
        listPersons();
        int index = getInt();
        if (index < 0 || index >= personList.size()){
            System.err.println("Индекс введёт НЕПРАВИЛЬНО");
            step = 1;
            return;
        }
        personList.remove(index);
        System.out.println("Человек удалён (Из мира)");
    }


    public static void listPersons(){
        int count = 0;
        if(!personList.isEmpty()){
            for (Person person : personList){
                System.out.println(count + " " + person);
                count++;
            }
        }else{
            System.err.println("Людей ещё нет");
        }
    }

    public static int again(){
        System.out.println("Ещё задачу?");
        System.out.println("1. да 2. нет");
        int choice = sc.nextInt();
        return choice == 1 ? 0 : 1;
    }

    public static int getInt(){
        while (!sc.hasNextInt()) {
            String input = sc.next();
            System.err.println("В жопу свой '" + input + "' засунь, ладно?");
        }
        return sc.nextInt();
    }

}
