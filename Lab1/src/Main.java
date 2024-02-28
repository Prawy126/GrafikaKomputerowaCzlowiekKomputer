import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        BufferedImage img = null;
        File f = null;
        try{
            f = new File("img/all_black.png");
            img = ImageIO.read(f);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        int szerokosc = img.getWidth();
        int wysokosc = img.getHeight();
        int p = img.getRGB(szerokosc/2,wysokosc/2);

        int a = (p>>24) & 0xff;
        int r = (p>>16) & 0xff;
        int g = (p>>8) & 0xff;
        int b = p & 0xff;

        a = 255;
        r = 250;
        g = 255;
        b = 255;
        img.setRGB(szerokosc/2,wysokosc/2,p);

        try{
            f = new File("img/modified.png");
            ImageIO.write(img,"png",f);
        }catch (IOException e){
            System.out.println((e.getMessage()));
        }
        allWhite(img);
        negative();
    }
    public static void allWhite(BufferedImage img){
        File f = null;
        try{
            f = new File("img/all_black.png");
        }catch (Exception e){
            System.out.println("Nie znaleziono obrazka");
        }
        int szerokosc = img.getWidth();
        int wysokosc = img.getHeight();
        for(int i = 1; i < szerokosc;i++){
            for(int j = 1; j < wysokosc;j++){
                int p = img.getRGB(i,j);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                a = 255<<24;
                b = 255<<16;
                g = 255<<8;
                r = 255;
                p = a|r|g|b;
                img.setRGB(i,j,p);
            }
        }
        try {
            f = new File("img/zadanie3.png");
            ImageIO.write(img, "png", f);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public static void negative(){
        BufferedImage img = null;
        File file = null;
        try{
            file = new File("img/obraz.png");
            img = ImageIO.read(file);
        }catch (Exception e){
            System.out.println("Nie znaleziono obrazka");
        }
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {

                int p = img.getRGB(i, j);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                p = a << 24 | r << 16 | g << 8 | b;

                img.setRGB(i, j, p);
            }
        }

        try {
            file = new File("./img/zadanie4.png");
            ImageIO.write(img, "png", file);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}