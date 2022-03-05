import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    private JTextField userInput;
    private JLabel labelOutput;
    private int randomNumber;
    private int tries;
    JButton btnPlayAgain;
    JButton btnGuess;

    public static void main(String[] args) {
        Game game = new Game();
        game.newGame();
        game.setSize(new Dimension(450, 300));
        game.setVisible(true);
    }

    public void newGame() {
        tries = 0;
        randomNumber = (int)(Math.random() * 100 + 1);
        System.out.println("Random Number: " + randomNumber);
        btnGuess.setVisible(true);
        btnPlayAgain.setVisible(false);
        userInput.setText("0");
        userInput.requestFocus();
        userInput.selectAll();
    }

    public void checkAnswer() {
        String message = "";
        try {
            tries++;
            int input = Integer.parseInt(userInput.getText());
            if (input < randomNumber) {
                message = input + " is too low. Try again.";
            } else if (input > randomNumber) {
                message = input + " is too high. Try again.";
            } else {
                message = input + " is correct. You win! " + tries + " tries!";
                btnGuess.setVisible(false);
                btnPlayAgain.setVisible(true);
                // newGame();
            }
        } catch (Exception e) {
            message = "Enter a whole number between 1 and 100.";
        } finally {
            labelOutput.setText(message);
            userInput.requestFocus();
            userInput.selectAll();
        }
    }

    public Game() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hi-Lo Game");
        getContentPane().setLayout(null);

        JLabel labelHilo = new JLabel("Hi-Lo Game");
        labelHilo.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelHilo.setHorizontalAlignment(SwingConstants.CENTER);
        labelHilo.setBounds(10, 37, 414, 24);
        getContentPane().add(labelHilo);

        JLabel labelGuessText = new JLabel("Guess a number between 1 and 100:");
        labelGuessText.setHorizontalAlignment(SwingConstants.RIGHT);
        labelGuessText.setBounds(10, 98, 272, 14);
        getContentPane().add(labelGuessText);

        userInput = new JTextField();
        userInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        userInput.setBounds(292, 95, 43, 20);
        getContentPane().add(userInput);
        userInput.setColumns(10);

        btnGuess = new JButton("Guess!");
        btnGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        btnGuess.setBounds(172, 149, 89, 23);
        getContentPane().add(btnGuess);

        labelOutput = new JLabel("Enter a number above, and click Guess!");
        labelOutput.setHorizontalAlignment(SwingConstants.CENTER);
        labelOutput.setBounds(10, 209, 414, 14);
        getContentPane().add(labelOutput);

        btnPlayAgain = new JButton("Play Again");
        btnPlayAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        btnPlayAgain.setBounds(167, 149, 99, 23);
        getContentPane().add(btnPlayAgain);
    }
}