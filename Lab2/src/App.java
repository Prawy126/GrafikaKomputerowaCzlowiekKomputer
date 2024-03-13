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
            mainRenderer.drawLine(300, 350, 50, 50, mainRenderer.getLineAlgo());
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