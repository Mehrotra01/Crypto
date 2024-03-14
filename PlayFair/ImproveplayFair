package Playfair;

import java.util.*;

public class playFair {
    static Scanner scanner = new Scanner(System.in);
    static Character[][] table = new Character[5][5];
    static ArrayList<Integer> bogusIndex;

    public static void inputKey() {
        String str = "";
        String ans = "";
        while (ans.isEmpty()) {

            ans = scanner.nextLine().trim();

            if (isPlainText(ans)) {
                str = ans;
            } else {
                ans = "";
                System.out.println("The string contains UpperCase letters or symbols or spaces.");
            }
        }
        str = str.toUpperCase();
        if (str.contains("J")) {
            if (str.contains("I")) {
                if (str.contains("K")) {
                } else {
                    str += "ABCDEFGHIJLMNOPQRSTUVWXYZ";
                    System.out.println("Replacing K cause we can have 25 charcters");
                }
            } else {
                str += "ABCDEFGHJKLMNOPQRSTUVWXYZ";
                System.out.println("Replacing I cause we can have 25 charcters");
            }
        } else {
            str += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
            System.out.println("Replacing J cause we can have 25 charcters");
        }
        Matrix(str);
        // return str;
    }

    public static void Matrix(String str) {

        HashSet<Character> set = new HashSet<>();
        int row = 0;
        int col = 0;

        for (char ch : str.toCharArray()) {
            if (!set.contains(ch)) {
                table[row][col] = ch;
                set.add(ch);
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                    if (row == 5) {
                        break; // Matrix is filled, no need to process further characters
                    }
                }
            }
        }
        printTable();
    }

    public static boolean isPlainText(String str) {
        return str.matches("^[a-z]*$");
    }

    public static String inputPlainText() {
        String str = "";
        String ans = "";
        while (ans.isEmpty()) {

            ans = scanner.nextLine().trim();

            if (isPlainText(ans)) {
                str = ans;
            } else {
                ans = "";
                System.out.println("The string contains UpperCase letters or symbols or spaces: ");
                System.out.print("Enter the key for playfair cipher: ");
            }
        }
        return bogusChar(str);

    }

    public static boolean isCipherText(String str) {
        return str.matches("^[A-Z]*$");
    }

    public static String inputCipherText() {
        String str = "";
        String ans = "";
        while (ans.isEmpty()) {

            ans = scanner.nextLine().trim();

            if (isCipherText(ans)) {
                str = ans;
            } else {
                ans = "";
                System.out.println("The string contains lowerCase letters or symbols or spaces.");
            }
        }
        return bogusChar(str);

    }

    public static String bogusChar(String str) {
        StringBuilder modifiedStringBuilder = new StringBuilder();

        for (int i = 0; i < str.length();) {
            char firstChar = str.charAt(i);
            char secondChar = (i + 1 < str.length()) ? str.charAt(i + 1) : '\0';

            modifiedStringBuilder.append(firstChar);

            if (secondChar != '\0' && secondChar == firstChar) {
                modifiedStringBuilder.append('x');
                i++;
                bogusIndex.add(i);
                continue;
            }

            if (secondChar != '\0') {
                modifiedStringBuilder.append(secondChar);
                i += 2;
                continue;
            }
            i++;
        }

        // If the string length is odd, append 'x' at the end
        if (modifiedStringBuilder.toString().length() % 2 != 0) {
            // System.out.println("Added");
            modifiedStringBuilder.append('x');
            bogusIndex.add(modifiedStringBuilder.length());
        }
        System.out.println("Your update text string " + modifiedStringBuilder.toString().toUpperCase());
        // System.out.println(bogusIndex);
        return modifiedStringBuilder.toString().toUpperCase();

    }

    private static int[] search(char c) {
        int[] pt = new int[2];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == table[i][j]) {
                    pt[0] = i;
                    pt[1] = j;
                }
        return pt;
    }

    public static void encryption(String plainText) {
        String res = "";
        for (int i = 0; i < plainText.length(); i += 2) {
            char a = plainText.charAt(i);
            char b = plainText.charAt(i + 1);
            int r1 = search(a)[0];
            int c1 = search(a)[1];
            int r2 = search(b)[0];
            int c2 = search(b)[1];

            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            res += table[r1][c1] + "" + table[r2][c2];
        }
        System.out.println("Your cipher Text is " + res);
    }

    public static void decryption(String cipherText) {
        String res = "";

        for (int i = 0; i < cipherText.length(); i += 2) {
            char a = cipherText.charAt(i);
            char b = cipherText.charAt(i + 1);
            int r1 = search(a)[0];
            int c1 = search(a)[1];
            int r2 = search(b)[0];
            int c2 = search(b)[1];

            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
                // we can also interchange the value of row
            }
            res += table[r1][c1] + "" + table[r2][c2];
        }
        int in = 0;
        if (bogusIndex.contains(res.length())) {
            res = res.substring(0, res.length() - 1);
        }
        while (in < res.length()) {
            if (bogusIndex.contains(in)) {
                res = res.substring(0, in) + res.substring(in + 1, res.length());
            }
            in++;
        }

        System.out.println("Your plain text is " + res);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to PLayFair Cipher");
        System.out.println("In this we are using 'X' as a bogus character");
        System.out.println("0 to Exit");
        System.out.println("1 to Encryption");
        System.out.println("2 to Decryption");
        int userInput;
        boolean hasRun = false;
        do {
            System.out.print("\nEnter a command: ");
            userInput = checkInitalInput();
            if (!hasRun) {
                System.out.print("Enter the key for playfair cipher: ");
                inputKey();
                bogusIndex = new ArrayList<Integer>();
                hasRun = true;
            }
            switch (userInput) {
                case 1:
                    System.out.print("Enter the plain text: ");
                    String eStr = inputPlainText();
                    encryption(eStr);
                    break;
                case 2:
                    System.out.print("Enter the cipher text: ");
                    String cStr = inputCipherText();
                    decryption(cStr);
                    break;
                case 3:
                    System.out.print("Enter the key for playfair cipher: ");
                    inputKey();
                    bogusIndex = new ArrayList<Integer>();
                    break;
                case 0:
                    System.out.println("Exiting program....");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userInput != 0);
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
        return str.matches("[0-3]");
    }

    public static void printTable() {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
