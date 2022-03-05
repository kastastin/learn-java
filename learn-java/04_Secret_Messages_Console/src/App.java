import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message to encode or decode, or press ENTER to end:");
        String message = scan.nextLine();

        while (message.length() > 0) {
            String output = "";
            int keyValue = 0;

            while (keyValue == 0) {
                System.out.println("Enter a secret key (-25 to 25):");
                try {
                    keyValue = Integer.parseInt(scan.nextLine());
                } catch (Exception ex) {
                    System.out.println("Not a valid integer.");
                }
            }

            char key = (char) keyValue;
            for (int i = 0; i < message.length(); i++) {
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
                    input += (keyValue % 10);
                    if (input > '9') input -= 10;
                    if (input < '0') input += 10;
                }
                output += input;
            }

            System.out.println(output);
            System.out.println("Enter a message to encode or decode, or press ENTER to end:");
            message = scan.nextLine();
        }
        scan.close();
    }
}