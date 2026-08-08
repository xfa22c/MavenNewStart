package dgf.xfa22c.maven.jsonAndGson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        Person person = new Person("Ангелина", 21);
        String json = gson.toJson(person);
        System.out.println(json);

        String jsonP = "{\"name\":\"Ангелина\",\"age\":21}";
        Person person1 = gson.fromJson(jsonP, Person.class);
        System.out.println(person1);

        List<Person> personList = List.of(
                new Person("Ангелина-Лист", 21),
                new Person("Faceless_Void", 45000)
        );

        String jsonList = gson.toJson(personList);
        System.out.println(jsonList);
        Type type = new TypeToken<List<Person>>(){}.getType();
        List<Person> personList1 = gson.fromJson(jsonList, type);
        System.out.println(personList1);

        System.out.println();
        System.out.println();

        BufferedWriter bw = new BufferedWriter(new FileWriter("person.json"));
        Person person2 = new Person("Ангелина_Json", 21);
        String json2 = gson.toJson(person2);
        bw.write(json2);
        bw.close();

        BufferedReader br = new BufferedReader(new FileReader("person.json"));
        String readed = br.readLine();
        Person personFile = new Gson().fromJson(readed, Person.class);
        System.out.println(personFile.getName());
        System.out.println(personFile.getAge());


        System.out.println();
        System.out.println();

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("person1.json"));
        String toJson = gson.toJson(person);
        bw1.write(toJson);
        Person person3 = new Person("Войд", 45000);
        String json3 = gson.toJson(person3);
        bw1.newLine();
        bw1.write(json3);
        bw1.close();

        BufferedReader br1 = new BufferedReader(new FileReader("person1.json"));
        String line;
        while ((line = br1.readLine()) != null) {
            Person p =  gson.fromJson(line, Person.class);
            System.out.println(p);
        }




    }

}
