import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class PictureGenerator {

    public void create(InputStream inputStream, String fileName) throws Exception {
        // leitura de imagem
        BufferedImage imageOriginal = ImageIO.read(inputStream);

        // cria nova imagem com transparência e com tamanho novo
        int width = imageOriginal.getWidth();
        int height = imageOriginal.getHeight();
        int newHeight = height + 200;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
        // copiar a imagem original para nova imagem (em mémoria)
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(imageOriginal, 0 ,0, null);

        // configurar a fonte
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 86);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(font);



        // manoel gomes
        InputStream manoel = new URL("https://o.remove.bg/downloads/11bb403e-7432-4b04-8696-75aaa3e45bf2/image-removebg-preview.png").openStream();
        BufferedImage manoelImage = ImageIO.read(manoel);
        graphics.drawImage(manoelImage, 286, 1200, null);

        //escrever uma frase na nova imagem
        graphics.drawString("Gostei demaize", 0, newHeight - 100);

        // escrever a imagem em um arquivo
        ImageIO.write(newImage, "png", new File(fileName));
    }

}
