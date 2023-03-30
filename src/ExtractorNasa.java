import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtractorNasa implements ExtractorContent {

    public List<Content> extractContent(String json) {

        // extrair so os dados que interessam (titulo, poster, classificações)
        var parser = new JsonParser();
        List<Map<String, String>> AttributesList = parser.parse(json);

        List<Content>  contents = new ArrayList<>();

        // popular a lista de conteudo

        for (Map<String, String> attributes : AttributesList ) {

            String title = attributes.get("title");
            String urlImage = attributes.get("url");
            var content = new Content(title, urlImage);

            contents.add(content);
        }

        return contents;

    }

}
