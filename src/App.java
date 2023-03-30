import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception{
        // fazer uma conexão HTTP e buscar os tops 250 filmes
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-10-14&end_date=2022-10-15";
        ExtractorContent extractor = new ExtractorNasa();


        var http = new ClientHttp();
        String json = http.searchData(url);


        //variaveis de cores
        var color_reset = Colors.RESET;
        var green = Colors.COLOR_GREEN;
        var purple = Colors.PURPLE_BACKGROUND;

        //exibir e manipular os dados


        List<Content> contents = extractor.extractContent(json);


        //System.out.println( green + "Top 10 Movies" + color_reset);
        // System.out.println();

        var generator = new PictureGenerator();
        for(Content content: contents) {

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            String fileName = ("output/" + content.getTitle() + ".png");


            generator.create(inputStream, fileName);

           // System.out.println("Titulo: " + content.get("title"));
           // System.out.println("Poster: " + content.get("image"));
           // System.out.println(purple + "Classificação: " + content.get("imDbRating") + color_reset);

           // System.out.println();

        }
    }
}