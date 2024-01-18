import java.util.Scanner;

public class playFair {
    static Scanner scanner = new Scanner(System.in);
    public static int checkInitalInput() {
        boolean flag = true;
        int ans = 0;
        while (flag) {

            String userInput = scanner.next().trim();
            if (isCmd(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
            } else {
                System.out.println("press a valid key between 1-4");
            }
        }
        return ans;
    }

    public static boolean isCmd(String str) {
        return str.matches("[0-4]");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter a command: ");
            System.out.println("1 to Encryption");
            System.out.println("2 to Decryption");
            System.out.println("3 to Rowbustway");
            System.out.println("4 to Exit");

            int userInput = checkInitalInput();

            if (userInput == 4) {
                System.out.println("Exiting program...");
                System.exit(0);
            }

            
        }
    }
}
