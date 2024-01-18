
// Additive Cipher
// Caesar Cipher
import java.util.Scanner;

public class AditiveCipher {
    static Scanner scanner = new Scanner(System.in);
    static char ARR[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    static char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static String checkPlainString() {
        boolean flag = true;
        String str = "";
        String ans = "";
        while (flag) {

            do {
                ans = scanner.nextLine().trim();
            } while (ans.isEmpty());

            System.out.println(ans);
            if (isPlainText(ans)) {
                flag = false;
                str = ans;
            } else {
                System.out.println("The string contains UpperCase letters or symbols.");
            }

        }
        return str;
    }

    public static boolean isPlainText(String str) {
        return str.matches("^[a-z ]*$");
    }

    public static String checkCypherString() {
        boolean flag = true;
        String str = "";
        String ans = "";
        while (flag) {
            do {
                ans = scanner.nextLine().trim();
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

    public static boolean isCypherText(String str) {
        return str.matches("^[A-Z ]*$");
    }

    public static long CheckIntegerInput() {
        boolean flag = true;
        long ans = 0;
        while (flag) {

            String userInput = scanner.next();
            if (isKey(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
                System.out.println("Your key is " + ans);
            } else {
                System.out.println("Enter a valid key");
            }
        }
        return ans;
    }

    public static boolean isKey(String str) {
        return str.matches("^[0-9]+$");
    }

    public static int checkInitalInput() {
        boolean flag = true;
        int ans = 0;
        while (flag) {

            String userInput = scanner.next();
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

    public static void Encryption(String text, long key) {

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) == 32) {
                result.append(" ");
                continue;
            }
            int c = (int) text.charAt(i) - 97;

            long ch = (long) ((c + key) % 26);
            result.append(ARR[(int) ch]);
            ch = 0;
        }
        System.out.println("your plain text: " + text);
        System.out.println("your Cipher text: " + result);
    }

    public static void Decryption(String text, long key) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) == 32) {
                result.append(" ");
                continue;
            }
            int c = (int) text.charAt(i) - 65;
            if (c < key) {
                c += 26;
            }
            long ch = (long) ((c - key) % 26);
            result.append(arr[(int) ch]);
            ch = 0;
        }
        System.out.println("your Cipher text " + text); // JCTUJ
        System.out.println("your plain text: " + result);
    }

    public static void robustway(String pText, String cText) {

        if (pText.length() != cText.length()) {
            System.out.println("You have Entered the wrong Input");
            return;
        }

        for (int i = 0; i < 26; i++) {
            int c = (int) cText.charAt(0) - 65;

            if (c < i) {
                c += 26;
            }
            int ch = ((c - i) % 26);

            if (arr[ch] == pText.charAt(0)) {
                System.out.println("Your key is " + i);
                break;
            }

        }
    }

    public static void main(String args[]) {

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

            switch (userInput) {
                case 1:
                    System.out.println("Encryption");
                    System.out.println("Enter plain text");
                    String pstr = checkPlainString();
                    System.out.print("Enter key: ");
                    long pkey = CheckIntegerInput();
                    Encryption(pstr, pkey);
                    break;
                case 2:
                    System.out.println("Decryption");
                    System.out.println("Enter cypher text");
                    String cText = checkCypherString();
                    System.out.println("Enter key: ");
                    long ckey = CheckIntegerInput();
                    Decryption(cText, ckey);
                    break;
                case 3:
                    System.out.println("BruteForce");
                    System.out.println("Enter plain text");
                    String bText = checkPlainString();
                    System.out.println("Enter cypher text");
                    String ctext = checkCypherString();
                    // we need to print key
                    robustway(bText, ctext);
                    break;

                default:
                    System.out.println("press a valid key between 1-4");
                    break;
            }

        }
    }
}
