import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static void main(String[] args) {
        JFrame app = new JFrame("Bubble Draw App");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         app.getContentPane().add(new BubblePanel());
        app.setSize(new Dimension(600, 400));
        app.setVisible(true);
    }
}