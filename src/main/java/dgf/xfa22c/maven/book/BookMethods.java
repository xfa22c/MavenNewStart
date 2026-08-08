package dgf.xfa22c.maven.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookMethods {

    static Scanner sc = new Scanner(System.in);
    static List<Book> books = new ArrayList<>();
    static int step = 0;
    static int nextId;
    static Gson gson = new Gson();

    public static void runLoop() {
        int ch;

        while (step == 0) {
            manu();
            ch = getInt();
            step = handleChoice(ch);
        }
    }

    public static int handleChoice(int ch){
        switch (ch) {
            case 1:
                addBook();
                return again();

            case 2:
                searchBook();
                return again();

            case 3:
                updateBook();
                return again();

            case 4:
                deleteBook();
                return again();

                default:
                    System.out.println("Invalid's choice");
                    return 1;
        }
    }

    public static void addBook(){
        System.out.println("Введите название книги");
        String name = sc.next();
        System.out.println("Введите автора");
        String author = sc.next();
        System.out.println("Введите год выпуска книги");
        int year = getInt();
        boolean exists = books.stream().anyMatch(b -> b.getName().equalsIgnoreCase(name)
                && b.getAuthor().equalsIgnoreCase(author));
        if (exists) {
            System.out.println("Книга уже есть");
            return;
        }

        Book book = new Book(nextId++, name, author, year, false);

            books.add(book);
            saveBooks();

    }

    public static void searchBook(){
        List<Book> result;
        System.out.println("Выберите параметр для поиска");
        System.out.println("1. Название  2. Автор  3. Год выпуска");
        int choice = getInt();
        switch (choice) {
            case 1:
                System.out.println("Введите название книги");
                String name = sc.next();
                result = books.stream().filter(b -> b.getName().contains(name)).collect(Collectors.toList());
                if (result.isEmpty()) {
                    System.out.println("Результатов нет");
                }else{
                    System.out.println("Результат  - " + result);
                }
                break;

            case 2:
                System.out.println("Введите автора книги");
                String author = sc.next();
                result = books.stream().filter(b -> b.getAuthor().contains(author)).collect(Collectors.toList());
                if (result.isEmpty()) {
                    System.out.println("Результатов нет");
                }else{
                    System.out.println("Результат  - " + result);
                }
                break;

            case 3:
                System.out.println("Введите год выпуска книги");
                int year = getInt();
                result = books.stream().filter(b -> b.getYear() == year).collect(Collectors.toList());
                if (result.isEmpty()) {
                    System.out.println("Результатов нет");
                }else {
                    System.out.println("Результат  - " + result);
                }
                break;

            default:
                System.out.println("Такого выбора нет");
                step = 1;
                break;
        }
    }

    public static void updateBook(){
        System.out.println(books);
        System.out.println("Введите ID Для изменения");
        int id = getInt();
        if (id < 0 || id >= books.size()) {
            System.out.println("Книги с таким ID нет");
            return;
        }
        System.out.println("Выберите параметр для изменения");
        System.out.println("1. Всё  2. Выборочно");
        int choice = getInt();
        switch (choice) {
            case 1:
                System.out.println("Введите новое название книги");
                String nName = sc.next();
                System.out.println("Введите нового автора книги");
                String nAuthor = sc.next();
                System.out.println("Введите новый год выпуска книги");
                int nYear = getInt();

                boolean isExists = books.stream().filter(b -> b.getId() != id)
                        .anyMatch(b -> b.getName().equalsIgnoreCase(nName)
                        && b.getAuthor().equalsIgnoreCase(nAuthor) && b.getYear() == nYear);
                if (isExists) {
                    System.out.println("Такие параметры уже есть у книги");
                }else{
                    books.get(id).setName(nName);
                    books.get(id).setAuthor(nAuthor);
                    books.get(id).setYear(nYear);
                    saveBooks();
                }
                break;

            case 2:
                System.out.println("Выберите параметр для изменения");
                System.out.println("1. Название  2. Автор  3. Год выпуска  4. Прочитано");
                int choice2 = getInt();
                switch (choice2) {
                    case 1:
                        System.out.println("Введите новое название");
                        String nName1 = sc.next();
                        boolean exists = books.stream().filter(b -> b.getId() != id)
                                .anyMatch(b -> b.getName().equalsIgnoreCase(nName1));
                        if (exists) {
                            System.out.println("Такие параметр уже есть у книги");
                        }else{
                            books.get(id).setName(nName1);
                            System.out.println("Название изменено");
                            saveBooks();
                        }
                        break;

                    case 2:
                        System.out.println("Введите нового автора книги");
                        String nAuthor1 = sc.next();
                        boolean exists1 = books.stream().filter(b -> b.getId() != id)
                                .anyMatch(b -> b.getAuthor().equalsIgnoreCase(nAuthor1));
                        if (exists1) {
                            System.out.println("Этот параметр уже есть у книги");
                        }else{
                            books.get(id).setAuthor(nAuthor1);
                            System.out.println("Автор изменён");
                            saveBooks();
                        }
                        break;

                    case 3:
                        System.out.println("Введите новый год выпуска");
                        int year1 = getInt();
                        boolean exists2 = books.stream().anyMatch(b -> b.getYear() == year1);
                        if (exists2) {
                            System.out.println("Этот параметр уже есть у книги");
                        }else{
                            books.get(id).setYear(year1);
                            System.out.println("Год выпуска изменён");
                            saveBooks();
                        }
                        break;

                    case 4:
                        System.out.println("Выберите параметр");
                        System.out.println("1. Не прочитано __ НЕ 1. Прочитано");
                        int choice3 = getInt();
                        books.get(id).setRead(choice3 != 1);
                        saveBooks();
                        break;

                    default:
                        System.out.println("Ti Invalid");
                        step = 1;
                        break;
                }
                break;

            default:
                System.out.println("Ti Invalid");
                step = 1;
                break;
        }
    }

    public static void deleteBook(){
        if (books.isEmpty()) {
            System.err.println("Коллекция пуста, там ещё нет книг, олух");
        }else{
            System.out.println(books);
            System.err.println("Введите ID Для удаления");
            int id = getInt();
            if (id < 0 || id >= books.size()) {
                System.err.println("Ты дебил да?");
            }else{
                books.remove(id);
                saveBooks();
            }
        }
    }

    private static void saveBooks() {
        try (FileWriter writer = new FileWriter("books.json")) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    protected static void loadBooks() {
        File file = new File("books.json");
        if(!file.exists()) {
            System.out.println("Файл с книгами не найден, начинаем с пустого списка");
            return;
        }

        try(FileReader fileReader = new FileReader(file)){
            Type listType = new TypeToken<List<Book>>(){}.getType();
            List<Book> loaded = gson.fromJson(fileReader, listType);
            if(!loaded.isEmpty()) {
                books.addAll(loaded);
                System.out.println("Загружено книг : " + books.size());
            }
         }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



    public static void manu(){
        System.out.println("1. Добавить книгу   2. Искать книгу");
        System.out.println("3. Редактировать книгу  4. Удалить книгу");
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
