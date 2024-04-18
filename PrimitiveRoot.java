import java.util.*;

public class PrimitiveRootsFinder {

    // Function to calculate gcd (Euclidean algorithm)
    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Function to calculate phi(n) using Euler's Totient function
    private static int calculatePhi(int n) {
        int result = 1;
        for (int i = 2; i < n; i++) {
            if (gcd(i, n) == 1) {
                result++;
            }
        }
        return result;
    }

    // Function to check if a number is a prime number
    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;
        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    // Function to find all primitive roots modulo p
    private static List<Integer> findPrimitiveRoots(int p) {
        List<Integer> primitiveRoots = new ArrayList<>();
        int phi = calculatePhi(p);

        for (int g = 2; g < p; g++) {
            if (isPrimitiveRoot(g, p)) {
                primitiveRoots.add(g);
            }
        }

        return primitiveRoots;
    }

    // Function to check if g is a primitive root modulo p
    private static boolean isPrimitiveRoot(int g, int p) {
        Set<Integer> residues = new HashSet<>();
        int phi = calculatePhi(p);

        for (int i = 1; i < p; i++) {
            int modResult = (int) Math.pow(g, i) % p;
            residues.add(modResult);
            if (residues.size() == phi) {
                break;
            }
        }

        return residues.size() == phi;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int p;
        do {
            System.out.print("Enter a prime number (p): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // consume the invalid input
                System.out.print("Enter a prime number (p): ");
            }
            p = scanner.nextInt();
            if (!isPrime(p)) {
                System.out.println(p + " is not a prime number. Please enter a prime number.");
            }
        } while (!isPrime(p));

        List<Integer> primitiveRoots = findPrimitiveRoots(p);

        if (primitiveRoots.isEmpty()) {
            System.out.println("No primitive roots found modulo " + p);
        } else {
            System.out.println("Primitive roots modulo " + p + ": " + primitiveRoots);
        }

        scanner.close();
    }
}
