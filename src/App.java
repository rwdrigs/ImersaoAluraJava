import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception{
        // fazer uma conexão HTTP e buscar os tops 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // extrair so os dados que interessam (titulo, poster, classificações)
        var parser = new JsonParser();
        List<Map<String, String>> MovieList = parser.parse(body);

        //variaveis de cores
        var color_reset = Colors.RESET;
        var green = Colors.COLOR_GREEN;
        var purple = Colors.PURPLE_BACKGROUND;

        //exibir e manipular os dados
        System.out.println( green + "Top 10 Movies" + color_reset);
        System.out.println();



        var generator = new PictureGenerator();
        for(Map<String, String> filme: MovieList) {

            String urlImage = filme.get("image");
            String title = filme.get("title");

            InputStream inputStream = new URL(urlImage).openStream();
            String fileName = ("output/" + (title + ".png"));


            generator.create(inputStream, fileName);

            System.out.println("Titulo: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println(purple + "Classificação: " + filme.get("imDbRating") + color_reset);

            System.out.println();

        }
    }
}