import java.util.Scanner;

public class multiplicativeCipher {
    static Scanner scanner = new Scanner(System.in);
    static char ARR[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    static char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

        static int index[] ={1,3,5,7,9,11,15,17,19,21,23,25};
    public static String checkPlainString() {
        boolean flag = true;
        String str = "";
        while (flag) {

            String ans = scanner.next();
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
        return str.matches("[a-z]+");
    }

    public static String checkCypherString() {
        boolean flag = true;
        String str = "";
        while (flag) {

            String ans = scanner.next();
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
        return str.matches("^[A-Z]+$");
    }

    public static long CheckIntegerInput() {
        boolean flag = true;
        long ans = 0;
        while (flag) {

            String userInput = scanner.next();
            if (isKey(userInput)) {
                ans = Integer.parseInt(userInput) % 26;
                flag = false;
                System.out.println("The input is a valid integer: " + userInput);
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
            if (isKey(userInput)) {
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
            long ch = (long) ((((int) text.charAt(i) - 97) * key) % 26);
            // System.out.println((int)text.charAt(i) - 97);
            result.append(ARR[(int) ch]);
            ch = 0;
        }
        System.out.println("your plain text: " + text);
        System.out.println("your Cipher text: " + result);
    }

    public static void Decryption(String text, long key) {
        StringBuffer result = new StringBuffer();


        long kInverse=0;
        //only these values have an actuall inverse in multiplicative inverse
//        int inverse[]=[1,9,21,15,3,19,7,23,11,5,17,25];

        for(int z=0;z<index.length;z++){
            //p * n = 1 mod 26
            //1==p*nmod 26
            long CHACHA = (key * index[z])%26;
//            System.out.println(kInverse+"U");

            if(CHACHA==1){
                kInverse=index[z];
                break;
            }
        }

        for (int i = 0; i < text.length(); i++) {
            int c = (int) text.charAt(i) - 65;
//            System.out.println(kInverse+"U");

            long ch = (long) ((c * kInverse ) % 26);
            result.append(arr[(int) ch]);
            ch = 0;
        }
        System.out.println("your Cipher text " + text); // JCTUJ
        System.out.println("your plain text: " + result);
    }

    public static void robustway(String pText, String cText) {

        if(pText.length()==cText.length()){
            System.out.println("You have Entered the wrong Input");
            return;
        }

            int pos =0;
            for(;pos<pText.length();pos++){
                if(pText.charAt(pos)!='a'){
                    break;
                }
            }


        for (int i = 0; i < index.length; i++) {
        long kInverse=0;
        for(int z=0;z<index.length;z++){
            long CHACHA = ( i * index[z])%26;
            if(CHACHA==1){
                kInverse=index[z];
                break;
            }
        }
            int c = (int) cText.charAt(pos) - 65;

            int ch = ((c * (int)kInverse) % 26);

            if (arr[ch] == pText.charAt(pos)) {
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
