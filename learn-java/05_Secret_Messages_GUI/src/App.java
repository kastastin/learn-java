import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    private JTextField textKey;
    private JTextArea textIn;
    private JTextArea textOut;
    private JSlider slider;
    private JButton btnMoveUp;
    private JScrollPane scrollPaneUp;
    private JScrollPane scrollPaneDown;

    App() {
        getContentPane().setBackground(new Color(135, 206, 235));
        setTitle("Secret Messages App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        scrollPaneUp = new JScrollPane();
        scrollPaneUp.setBounds(10, 11, 564, 140);
        getContentPane().add(scrollPaneUp);

        textIn = new JTextArea();
        scrollPaneUp.setViewportView(textIn);
        textIn.setWrapStyleWord(true);
        textIn.setLineWrap(true);
        textIn.setFont(new Font("Lucida Console", Font.PLAIN, 18));

        scrollPaneDown = new JScrollPane();
        scrollPaneDown.setBounds(10, 210, 564, 140);
        getContentPane().add(scrollPaneDown);

        textOut = new JTextArea();
        scrollPaneDown.setViewportView(textOut);
        textOut.setWrapStyleWord(true);
        textOut.setLineWrap(true);
        textOut.setFont(new Font("Lucida Console", Font.PLAIN, 18));

        textKey = new JTextField();
        textKey.setHorizontalAlignment(SwingConstants.CENTER);
        textKey.setText("3");
        textKey.setBounds(258, 173, 44, 20);
        textKey.setColumns(10);
        textKey.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                int key = 0;
                try {
                    key = Integer.parseInt(textKey.getText());
                } catch (Exception ex) {
                    showError(textKey, "Please enter integer.");
                }
                slider.setValue(key);
            }
        });
        getContentPane().add(textKey);

        JLabel labelKey = new JLabel("Key:");
        labelKey.setHorizontalAlignment(SwingConstants.RIGHT);
        labelKey.setBounds(202, 176, 46, 14);
        getContentPane().add(labelKey);

        JButton btn = new JButton("Encode/Decode");
        btn.setBackground(new Color(135, 206, 235));
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String message = textIn.getText();
                    int key = Integer.parseInt( textKey.getText() );
                    String output = encode( message, key );
                    textOut.setText( output );
                } catch (Exception ex) {
                    showError(textKey, "Please enter a whole number value for the encryption key.");
                }
            }
        });
        btn.setBounds(312, 172, 144, 23);
        getContentPane().add(btn);

        slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                textKey.setText( "" + slider.getValue() );
                String message = textIn.getText();
                int key = slider.getValue();
                String output = encode( message, key );
                textOut.setText( output );
            }
        });
        slider.setValue(3);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(13);
        slider.setMinorTickSpacing(1);
        slider.setMinimum(-26);
        slider.setMaximum(26);
        slider.setPaintLabels(true);
        slider.setBackground(new Color(135, 206, 235));
        slider.setBounds(10, 162, 200, 45);
        getContentPane().add(slider);

        btnMoveUp = new JButton("Move Up ^");
        btnMoveUp.setBackground(new Color(135, 206, 235));
        btnMoveUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String temp = textIn.getText();
                textIn.setText(textOut.getText());
                slider.setValue(-slider.getValue());
            }
        });
        btnMoveUp.setBounds(466, 172, 108, 23);
        getContentPane().add(btnMoveUp);
    }

    public String encode( String message, int keyVal ){
        String output = "";
        char key = (char) keyVal;

        for ( int i = 0; i < message.length(); i++ ) {
            char input = message.charAt(i);
            if (input >= 'A' && input <= 'Z') {
                input += key;
                if (input > 'Z') input -= 26;
                if (input < 'A') input += 26;
            } else if (input >= 'a' && input <= 'z') {
                input += key;
                if (input > 'z') input -= 26;
                if (input < 'a') input += 26;
            } else if (input >= '0' && input <= '9') {
                input += (keyVal % 10);
                if (input > '9') input -= 10;
                if (input < '0') input += 10;
            }
            output += input;
        }
        return output;
    }

    public static void showError(JTextField key, String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage);
        key.requestFocus();
        key.selectAll();
    }

    public static void main(String[] args) {
        App app = new App();
        app.setSize(new Dimension(600,400));
        app.setVisible(true);
    }
}