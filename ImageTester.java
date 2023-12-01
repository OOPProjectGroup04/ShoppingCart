import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;



public class ImageTester {

    JFrame frame;
    JLabel displayField;
    Image image;

    public ImageTester() {
        frame = new JFrame("Image Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL imageURL = new URL("https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_UF1000,1000_QL80_.jpg");
            image = ImageIO.read(imageURL);

            // Resize the image 
            int newWidth = 200; 
            int newHeight = 200; 
            Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            displayField = new JLabel(new ImageIcon(resizedImage)); // Use resizedImage here
            frame.add(displayField);
        } catch (IOException e) {
            e.printStackTrace(); // Print the exception stack trace
            System.out.println("Image not found");
        }
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create an instance of ImageTester
        ImageTester i = new ImageTester();
    }
}
