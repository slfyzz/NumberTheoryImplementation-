import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.Fast Exponentiation\n2.Extended Euclidean Algorithm");
            System.out.println("3.Chinese Remainder Theorem\n4.Prime Number Generation");
            System.out.print("5.Exit\nChoose the operation number: ");
            int op = scanner.nextInt();

            if (op == 1) {
                Power power = new Power();
                System.out.println("Enter a:");
                int a = scanner.nextInt();
                System.out.println("Enter b");
                int b = scanner.nextInt();
                System.out.println("Enter m");
                int m = scanner.nextInt();
                System.out.println(power.iterativeFastExp(a, b, m));
            } else if (op == 2) {
                ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
                System.out.println("Enter a:");
                int a = scanner.nextInt();
                System.out.println("Enter b");
                int b = scanner.nextInt();
                System.out.println("GCD is " + extendedEuclidean.Calculate(a, b) + "\n s = " + extendedEuclidean.getS() + "\n t = " + extendedEuclidean.getT());
            } else if (op == 3) {

            } else if (op == 4) {
                PrimeGeneration primeGeneration = new PrimeGeneration();
                System.out.println("Enter n:");
                int n = scanner.nextInt();
                System.out.println("All primes till n are: ");
                primeGeneration.segmentedSieve(n);
            }
            else if (op == 5) {
                break;
            }
            else {
                System.out.println("Enter valid operation");
            }
        }
    }
}
