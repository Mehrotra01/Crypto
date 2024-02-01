import java.util.Scanner;
public class autokeyCipher {

    private static String ARR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String abc = "abcdefghijklmnopqrstuvwxyz";

    private static Scanner sc = new Scanner(System.in);

    private static String checkPlainString() {
        boolean flag = true;
        String str = "";
        String ans = "";
        while (flag) {

            do {
                ans = sc.nextLine().trim();
            } while (ans.isEmpty());

            if (isPlainText(ans)) {
                flag = false;
                str = ans;
            } else {
                System.out.println("The string contains UpperCase letters or symbols.");
            }

        }
        return str;
    }

    private static boolean isPlainText(String str) {
        return str.matches("^[a-z ]*$");
    }

    private static int CheckIntegerInput() {
        boolean flag = true;
        int ans = 0;
        while (flag) {

            String userInput = sc.next();
            if (isKey(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
                // System.out.println("Your key is " + ans);
            } else {
                System.out.println("Enter a valid key");
            }
        }
        return ans;
    }

    private static boolean isKey(String str) {
        return str.matches("^[0-9]+$");
    }

    private static int checkInitalInput() {
        boolean flag = true;
        int ans = 0;
        while (flag) {

            String userInput = sc.next();
            if (isCmd(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
            } else {
                System.out.println("press a valid key between 1-4");
            }
        }
        return ans;
    }

    private static boolean isCmd(String str) {
        return str.matches("[0-4]");
    }

    private static String checkCypherString() {
        boolean flag = true;
        String str = "";
        String ans = "";
        while (flag) {
            do {
                ans = sc.nextLine().trim();
            } while (ans.isEmpty());

            if (isCypherText(ans)) {
                flag = false;
                str = ans;
            } else {
                System.out.println("The string contains lowerCase letters or symbols.");
            }

        }
        return str;
    }

    private static boolean isCypherText(String str) {
        return str.matches("^[A-Z ]*$");
    }

    private static String encryption(String pText, int key) {
        String res = "";
        int len = pText.length();
        int newKey = key;

        for (int i = 0; i < len; i++) {
            if ((int) pText.charAt(i) == 32) {
                res += " ";
                continue;
            }

            int c = abc.indexOf(pText.charAt(i));
            // System.out.println(c);
            while (c < 0) {
                c += 26;
            }
            // System.out.println(newKey);
            int total = (c + newKey) % 26;
            newKey = c;
            // System.out.println(total);
            res = res + ARR.charAt(total);
        }
        return res;
    }

    private static String discryption(String cText, int key) {
        String res = "";
        int newKey = key;

        for (int i = 0; i < cText.length(); i++) {

            if ((int) cText.charAt(i) == 32) {
                res += " ";
                continue;
            }
            int c = (int) cText.charAt(i) - 65;
            while (c < newKey) {
                c += 26;
            }
            int total = (c - newKey) % 26;
            res += abc.charAt(total);
            newKey = total;
        }
        return res;
    }

    private static void bruteForce(String pText, String cText) {
        for (int i = 0; i < 26; i++) {

            int c = (int) cText.charAt(0) - 65;
            while (c < i) {
                c += 26;
            }
            int x = (c - i) % 26;
            int ch = abc.indexOf(pText.charAt(0));

            if (x == ch) {
                System.out.println(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("0 to Exit");
        System.out.println("1 to Encryption");
        System.out.println("2 to Decryption");
        System.out.println("3 to Rowbustway");
        int userInput;
        do {
            System.out.print("\nEnter a command: ");
            userInput = checkInitalInput();
            switch (userInput) {
                case 1:
                    System.out.print("Enter the Plain Text: ");
                    String eStr = checkPlainString();
                    System.out.print("Enter the key : ");
                    int ekey = CheckIntegerInput();
                    String encryptedStr = encryption(eStr, ekey);
                    System.out.println(encryptedStr);
                    break;
                case 2:
                    System.out.print("Enter the Cipher Text: ");
                    String cStr = checkCypherString();
                    System.out.print("Enter the key : ");
                    int ckey = CheckIntegerInput();
                    String dicryptedStr = discryption(cStr, ckey);
                    System.out.println(dicryptedStr);
                    break;
                case 3:
                    System.out.print("Enter the Plain Text: ");
                    String BEStr = checkPlainString();
                    System.out.print("Enter the Cipher Text: ");
                    String BCStr = checkCypherString();
                    bruteForce(BEStr, BCStr);
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userInput != 0);
    }
}
