import java.util.Scanner;

public class HiLo {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String playAgainText = "";
        do {
            int tries = 0;
            int userInput = 0;
            int randomNumber = (int) (Math.random() * 200) - 100;
            System.out.printf("Random number: %d\n", randomNumber);
            while (userInput != randomNumber) {
                tries += 1;
                System.out.print("Guess a number between 1 and 100: ");
                userInput = scan.nextInt();
                if (userInput < randomNumber) {
                    System.out.println("You entered " + userInput + ". It's too low. Try again.");
                } else if (userInput > randomNumber) {
                    System.out.println("You entered " + userInput + ". It's too high. Try again.");
                } else {
                    System.out.println("You entered " + userInput + ". You win!\nTries: " + tries);
                }
            }
            System.out.println("Would you like to play again (y/n)?");
            playAgainText = scan.next();
        } while (playAgainText.equalsIgnoreCase("y"));
        System.out.println("Thank you for playing! Goodbye.");
        scan.close();
    }
}