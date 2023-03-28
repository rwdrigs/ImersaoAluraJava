import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        // fazer uma conexão HTTP e buscar os tops 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();


        // extrair so os dados que interessam (titulo, poster, classificações)
        var parser = new JsonParser();
        List<Map<String, String>> ListaDeFilmes = parser.parse(body);

        //exibir e manipular os dados
        System.out.println("Top 10 Filmes");
        System.out.println();

        for(Map<String, String> filme: ListaDeFilmes) {
            System.out.println("Titulo: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("Classificação: " + filme.get("imDbRating"));

            System.out.println();

        }
    }
}