import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private JTextField textColor;
    private JTextField textVerb;
    private JTextField textAdjective;
    private JTextField textNoun;

    Game() {
        setTitle("MadLib App");
        getContentPane().setBackground(SystemColor.activeCaption);
        getContentPane().setLayout(null);

        JLabel madLibLabel = new JLabel("MadLib App");
        madLibLabel.setFont(new Font("Stencil", Font.PLAIN, 18));
        madLibLabel.setHorizontalAlignment(SwingConstants.CENTER);
        madLibLabel.setBounds(106, 11, 322, 29);
        getContentPane().add(madLibLabel);

        JLabel colorLabel = new JLabel("Enter a color:");
        colorLabel.setFont(new Font("Snap ITC", Font.PLAIN, 11));
        colorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        colorLabel.setBounds(260, 54, 146, 14);
        getContentPane().add(colorLabel);

        JLabel verbLabel = new JLabel("Enter a verb ending in -ed:");
        verbLabel.setFont(new Font("Snap ITC", Font.PLAIN, 11));
        verbLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        verbLabel.setBounds(10, 91, 180, 14);
        getContentPane().add(verbLabel);

        JLabel adjectiveLabel = new JLabel("Enter an adjective:");
        adjectiveLabel.setFont(new Font("Snap ITC", Font.PLAIN, 11));
        adjectiveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        adjectiveLabel.setBounds(63, 54, 125, 14);
        getContentPane().add(adjectiveLabel);

        JLabel nounLabel = new JLabel("Enter a noun:");
        nounLabel.setFont(new Font("Snap ITC", Font.PLAIN, 11));
        nounLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nounLabel.setBounds(296, 91, 110, 14);
        getContentPane().add(nounLabel);

        textColor = new JTextField();
        textColor.setForeground(new Color(46, 139, 87));
        textColor.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        textColor.setText("pink");
        textColor.setBounds(416, 51, 86, 20);
        textColor.setColumns(10);
        getContentPane().add(textColor);

        textVerb = new JTextField();
        textVerb.setForeground(new Color(0, 128, 0));
        textVerb.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        textVerb.setText("burped");
        textVerb.setBounds(200, 88, 86, 20);
        textVerb.setColumns(10);
        getContentPane().add(textVerb);

        textAdjective = new JTextField();
        textAdjective.setForeground(new Color(0, 128, 0));
        textAdjective.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        textAdjective.setText("silly");
        textAdjective.setBounds(200, 51, 86, 20);
        textAdjective.setColumns(10);
        getContentPane().add(textAdjective);

        textNoun = new JTextField();
        textNoun.setForeground(new Color(0, 128, 0));
        textNoun.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
        textNoun.setText("roller skate");
        textNoun.setBounds(416, 88, 86, 20);
        textNoun.setColumns(10);
        getContentPane().add(textNoun);

        JTextArea textOutput = new JTextArea();
        textOutput.setWrapStyleWord(true);
        textOutput.setLineWrap(true);
        textOutput.setText("The pink dragon burped at the silly roller skate.\r\nAnd everyone lived happily ever after.\r\nThe end.");
        textOutput.setForeground(new Color(255, 0, 51));
        textOutput.setFont(new Font("Monotype Corsiva", Font.BOLD, 24));
        textOutput.setBackground(new Color(255, 255, 204));
        textOutput.setBounds(10, 162, 514, 88);
        getContentPane().add(textOutput);

        JButton btn = new JButton("Press here to view your MadLib creation!");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String adjective = textAdjective.getText();
                String color = textColor.getText();
                String verb = textVerb.getText();
                String noun = textNoun.getText();
                textOutput.setText("The " + color + " dragon " + verb + " at the " + adjective + " " + noun + ".\n" +
                        "And everyone lived happily ever after.\n" +
                        "The end.");
            }
        });
        btn.setBackground(new Color(153, 255, 204));
        btn.setFont(new Font("Sitka Text", Font.BOLD, 11));
        btn.setBounds(106, 128, 322, 23);
        getContentPane().add(btn);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.setSize(new Dimension(550, 300));
        game.setVisible(true);
    }
}