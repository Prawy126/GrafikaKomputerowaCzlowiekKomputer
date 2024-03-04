package klasaColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("img/all_black.png"));
            allWhite(img);
            ImageIO.write(img, "png", new File("img/modified.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage img2 = ImageIO.read(new File("img/obraz.png"));
            img2 = imgNegative(img2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void allWhite(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        Color color = new Color(255, 255, 255);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                img.setRGB(j, i, color.getRGB());
            }
        }

        try {
            ImageIO.write(img, "png", new File("img/zadanie3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage imgNegative(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color1 = img.getRGB(j, i);
                int r = (color1 >> 16) & 0xFF;
                int g = (color1 >> 8) & 0xFF;
                int b = color1 & 0xFF;

                int nr = 255 - r;
                int ng = 255 - g;
                int nb = 255 - b;

                Color color2 = new Color(nr, ng, nb);
                img.setRGB(j, i, color2.getRGB());
            }
        }

        try {
            ImageIO.write(img, "png", new File("img/zadanie4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}
