package Playfair;

import java.util.*;

public class playFair {
    static Scanner scanner = new Scanner(System.in);
    static Character[][] table = new Character[5][5];

    public static void inputKey() {
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
                System.out.println("The string contains UpperCase letters or symbols or spaces.");
            }
        }
        str=str.toUpperCase();
        if (str.contains("I")) {
            if (str.contains("J")) {
                if (str.contains("K")) {
                }else{
                    str += "ABCDEFGHIJLMNOPQRSTUVWXYZ";
                    System.out.println("Replacing K cause we can have 25 charcters");
                }
            } else {
                str += "ABCDEFGHIKLMNOPQRSTUVWXYZ";
                System.out.println("Replacing J cause we can have 25 charcters");
            }
        } else {
            str += "ABCDEFGHJKLMNOPQRSTUVWXYZ";
            System.out.println("Replacing I cause we can have 25 charcters");
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

    public static void printTable(){
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isPlainText(String str) {
        return str.matches("^[a-z]*$");
    }

    public static String inputPlainText() {
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
                System.out.println("The string contains UpperCase letters or symbols or spaces.");
            }
        }
        return bogusChar(str);
        
    }

    public static String bogusChar(String str){
        StringBuilder modifiedStringBuilder = new StringBuilder();
            
            for (int i = 0; i < str.length(); ) {
                char firstChar = str.charAt(i);
                char secondChar = (i + 1 < str.length()) ? str.charAt(i + 1) : '\0'; 
    
                modifiedStringBuilder.append(firstChar);
    
                if (secondChar != '\0' && secondChar == firstChar) {
                    modifiedStringBuilder.append('x');
                    i++;
                    continue;
                }
                
                if (secondChar != '\0') {
                    modifiedStringBuilder.append(secondChar);
                    i+=2;
                    continue;
                }
                i++;
            }
    
            // If the string length is odd, append 'x' at the end
            if (modifiedStringBuilder.toString().length() % 2 != 0) {
                // System.out.println("Added");
                modifiedStringBuilder.append('x');
            }
            // System.out.println(modifiedStringBuilder.toString().toUpperCase());
            return modifiedStringBuilder.toString().toUpperCase();
       
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
        return str.matches("[0-2]");
    }
    private static int[] search(char c) {
        int[] pt= new int[2];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == table[i][j]){
                    pt[0]=i;
                    pt[1]=j;}
        return pt;
    }

    public static void encryption(String plainText){
        String res="";
        for( int i=0;i<plainText.length();i+=2){
            char a= plainText.charAt(i);
            char b= plainText.charAt(i+1);
            int r1= search(a)[0];
            int c1= search(a)[1];
            int r2= search(b)[0];
            int c2= search(b)[1];

            if(r1== r2){
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            }else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            }else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
                // we can also interchange the value of row
            }
            res+=table[r1][c1]+""+table[r2][c2];
        }
        System.out.println(res);
    }

    public static void decryption(String cipherText){
        String res="";
        
        for( int i=0;i<cipherText.length();i+=2){
        char a= cipherText.charAt(i);
        char b= cipherText.charAt(i+1);
        int r1= search(a)[0];
        int c1= search(a)[1];
        int r2= search(b)[0];
        int c2= search(b)[1];

        if(r1== r2){
            c1 = (c1 + 4) % 5;
            c2 = (c2 + 4) % 5;
        }else if (c1 == c2) {
            r1 = (r1 + 4) % 5;
            r2 = (r2 + 4) % 5;
        }else {
            int temp = c1;
            c1 = c2;
            c2 = temp;
            // we can also interchange the value of row
        }
        res+=table[r1][c1]+""+table[r2][c2];
    }
    System.out.println(res);
    }

    public static void main(String[] args) {
        System.out.println("Welcome to PLayFair Cipher");
        System.out.println("In this we are using X as a bogus character");
        System.out.println("0 to Exit");
        System.out.println("1 to Encryption");
        System.out.println("2 to Decryption");
        int userInput;
        do {
            System.out.print("\nEnter a command: ");
            userInput = checkInitalInput();
            switch (userInput) {
                case 1:
                    System.out.print("Enter the key for playfair cipher: ");
                    inputKey();
                    System.out.print("Enter the plain text: ");
                    String eStr = inputPlainText();
                    encryption(eStr);
                    break;
                case 2:
                    System.out.print("Enter the key for playfair cipher: ");
                    inputKey();
                    System.out.print("Enter the cipher text: ");
                    String cStr = inputPlainText();
                    decryption(cStr);
                    break;
                case 0:
                    System.out.println("Exiting program....");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (userInput != 0);
    }
}
