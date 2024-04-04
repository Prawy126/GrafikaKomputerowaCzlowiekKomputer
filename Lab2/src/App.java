import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    String version = "0.02";

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java App <path to output file> <width> <height> <line algorithm>");
            System.exit(1);
        }

        try {
            Renderer mainRenderer = new Renderer(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
            mainRenderer.clear();
            mainRenderer.drawTriangle(new Vec2f(100,50),new Vec2f(150,75),new Vec2f(200,250),new Vec3f(32,42,25));
            mainRenderer.save();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            System.out.println("Width and height must be integers.");
            System.exit(1);
        }
    }

    public String getVersion() {
        return this.version;
    }

}