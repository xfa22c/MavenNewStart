package dgf.xfa22c.maven.url;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UrlExample {

    public static void main(String[] args) throws IOException, URISyntaxException {


        URI uri = new URI("https://catfact.ninja/fact");
        URL url =  uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();

        String json = sb.toString();
        CatFact catFact = new Gson().fromJson(json, CatFact.class);
        System.out.println(catFact.fact);

        URI uri2 = new URI("http://api.open-notify.org/iss-now.json");
        URL url2 =  uri2.toURL();
        conn = (HttpURLConnection) url2.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line1;
        StringBuilder sb1 = new StringBuilder();
        while ((line1 = reader1.readLine()) != null) {
            sb1.append(line1);
        }
        reader1.close();

        String json1 = sb1.toString();
        Iss iss = new Gson().fromJson(json1, Iss.class);
        System.out.println(iss.getIss_position().getLatitude());
        System.out.println(iss.getIss_position().getLongitude());

        URI uriPost = new URI("https://jsonplaceholder.typicode.com/posts");
        URL urlPost =  uriPost.toURL();
        conn = (HttpURLConnection) urlPost.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String input = "{\"title\":\"Мой пост\",\"body\":\"Это тестовое сообщение\",\"userId\":1}";

        try (OutputStream os = conn.getOutputStream()) {
            os.write(input.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        int code = conn.getResponseCode();
        System.out.println("Код ответа: " + code);

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line5;
            while ((line5 = br.readLine()) != null) {
                response.append(line5);
            }
        }
        System.out.println("Ответ: " + response);








    }

}
