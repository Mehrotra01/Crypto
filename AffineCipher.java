import java.util.Scanner;

public class AffineCipher {
    static Scanner scanner = new Scanner(System.in);
    static char ARR[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    static char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    static int index[] = { 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };

    public static String checkPlainString() {
        boolean flag = true;
        String str = "";
        String ans = "";
        while (flag) {

            do {
                ans = scanner.nextLine().trim();
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

    public static long checkK1() { // multiplication key input
        boolean flag = true;
        long ans = 0;
        while (flag) {

            String userInput = scanner.next();
            if (isKey(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
                System.out.println("Your key1 is " + ans);
            } else {
                System.out.println("Enter a valid key which isn't a multiple of 2 and 13");
            }
        }
        return ans;
    }

    public static boolean isKey(String str) {
        boolean fl = false;
        if (str.matches("^[0-9]+$")) {
            int intValue = Integer.parseInt(str) % 26;
            fl = isValidKey(intValue);
        }
        return fl;
    }

    public static boolean isValidKey(int x) {
        boolean found = false;
        for (int number : index) {
            if (number == x) {
                found = true;
                break;
            }
        }
        return found;
    }

    public static long checkK2() {
        boolean flag = true;
        long ans = 0;
        while (flag) {

            String userInput = scanner.next();
            if (isKey2(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
                System.out.println("Your key is " + ans);
            } else {
                System.out.println("Enter a valid key");
            }
        }
        return ans;
    }

    public static boolean isKey2(String str) {
        return str.matches("^[0-9]+$");
    }

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

    private static boolean isSame(String str1, String str2) {
        // System.out.println("running");
        if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    public static void Encryption(String text, long key1, long key2) {

        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) == 32) {
                result.append(" ");
                continue;
            }

            int c = (int) text.charAt(i) - 97;

            long ch = (long) (((c * key1) + key2) % 26);
            result.append(ARR[(int) ch]);
            ch = 0;
        }
        System.out.println("your plain text: " + text);
        System.out.println("your Cipher text: " + result);
    }

    public static void Decryption(String text, long key1, long key2) {
        StringBuffer result = new StringBuffer();

        long kInverse = 0;

        for (int z = 0; z < index.length; z++) {
            long CHACHA = (key1 * index[z]) % 26;

            if (CHACHA == 1) {
                kInverse = index[z];
                break;
            }
        }

        for (int i = 0; i < text.length(); i++) {

            if ((int) text.charAt(i) == 32) {
                result.append(" ");
                continue;
            }

            int c = (int) text.charAt(i) - 65;
            while (c < key2) {
                c += 26;
            }
            long ch = (long) (((c - key2) * kInverse) % 26);
            result.append(arr[(int) ch]);
            ch = 0;
        }
        System.out.println("your Cipher text " + text);
        System.out.println("your plain text: " + result);
    }

    public static void robustway(String pText, String cText) {

        if (pText.length() != cText.length()) {
            System.out.println("You have Entered the wrong Input");
            return;
        }

        for (int i : index) {
            boolean match=false;
            long kInverse = 0;
            for (int z = 0; z < index.length; z++) {
                long CHACHA = (i * index[z]) % 26;
                if (CHACHA == 1) {
                    kInverse = index[z];
                    break;
                }
            }

            for (int z = 0; z < 26; z++) {
                String check = "";
                for (int x = 0; x < pText.length(); x++) {

                    if ((int) pText.charAt(x) == 32) {
                        check += " ";
                        continue;
                    }

                    int c = (int) cText.charAt(x) - 65;
                    // System.out.println("c before " + c);
                    while (c < z) {
                        c += 26;
                    }

                    int ch = (((c - z) * (int) kInverse) % 26);
                    // System.out.println("k1 " + i + " k2 " + z + " ch " + ch + " c " + c);
                    check += arr[ch];

                    if (arr[ch] != pText.charAt(x)) {
                        break;
                    }
                }
                if (check.length() == pText.length()) {

                    if (isSame(check, pText)) {
                        System.out.println("You hacked the cipher");
                        System.out.println("Your key are k1: " + i + " k2: " + z);
                        break;
                        // break;
                    }

                }
            }
            if(match){
                break;
            }
        }
    }

    public static void main(String args[]) {

        while (true) {
            System.out.println("Enter a command: ");
            System.out.println("1 to Encryption");
            System.out.println("2 to Decryption");
            System.out.println("3 to Rowbustway ");
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
                    System.out.print("Enter key1: ");
                    long mK1 = checkK1();
                    System.out.print("Enter key2 : ");
                    long aK2 = checkK2();
                    Encryption(pstr, mK1, aK2);
                    break;
                case 2:
                    System.out.println("Decryption");
                    System.out.println("Enter cypher text");
                    String cText = checkCypherString();
                    System.out.println("Enter key1 : ");
                    long ckey1 = checkK1();
                    System.out.print("Enter key2 : ");
                    long ckey2 = checkK2();
                    Decryption(cText, ckey1, ckey2);
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
