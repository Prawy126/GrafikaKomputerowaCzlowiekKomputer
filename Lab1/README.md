# Lab 1

## Zadanie 1

Uruchom projekt korzystając z interfejsu gradle'a w linii poleceń, lub importując go do ulubionego IDE.

## Zadanie 2
Uzupełnij linię kodu odpowiedzialną za zapis zmodyfikowanych kanałów do zmiennej p. Uruchom program i sprawdź rezultat.

#### Rozwiązanie

```Java
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
        
    }
```

## Zadanie 3
Zaimplementuj metodę `allWhite()`, która przyjmuje obiekt klasy BufferedImage jako parametr i ustawia wszystkie jego piksele na kolor biały (255,255,255,255).

#### Rozwiązanie

```java
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
```

## Zadanie 4
Zaimplementuj metodę `imgNegative()`, która przyjmuje obiekt klasy BufferedImage jako parametr i zmienia wartości pikseli tak, aby uzyskać negatyw obrazu. Negatyw będzie można uzyskać odejmując wartości poszczególnych kanałów od wartości maksymalnej (255).

#### Rozwiązanie
```java
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
```

## Użycie klasy Color

```java
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

```