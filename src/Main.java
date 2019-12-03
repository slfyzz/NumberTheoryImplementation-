import java.util.Scanner;

public class Main {

    public static void main(String []args)
    {
        CRT crt = new CRT();
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();
        PrimeGeneration primeGeneration = new PrimeGeneration();
        Power power = new Power();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- Mod Exponentiation");
            System.out.println("2- Extended Euclid Algorithm");
            System.out.println("3- Chinese Remainder Theorem");
            System.out.println("4- Prime Generator!");
            System.out.println("0 to Quit");

            System.out.print("Enter the number of what Algorithm you want to operate: ");
            int a = scanner.nextInt();

            if (a > 4 || a < 0)
                System.out.println("you have to choose between 4 choices only!!");
            else if (a == 0)
                break;
            else {
                if (a == 1) {
                    System.out.println("Enter Base");
                    int Base = scanner.nextInt();
                    System.out.println("Enter Exp");
                    int Exp = scanner.nextInt();
                    System.out.println("Enter mod");
                    int mod = scanner.nextInt();
                    power.Calculate(Base, Exp, mod);
                } else if (a == 2) {
                    System.out.println("Enter a");
                    int b = scanner.nextInt();
                    System.out.println("Enter b");
                    int m = scanner.nextInt();
                    extendedEuclidean.ExtendedGCD(b, m);
                } else if (a == 3) {
                    System.out.println("Enter A");
                    long A = scanner.nextLong();
                    System.out.println("Enter B");
                    long B = scanner.nextLong();
                    System.out.println("Enter number of moduli");
                    int M = scanner.nextInt();
                    System.out.println("Enter the sequence of moduli");
                    long[] arr = new long[M];
                    for (int i = 0; i < M; i++) {
                        arr[i] = scanner.nextLong();
                    }
                    crt.Calculate(A, B, arr);
                } else {
                    System.out.println("Enter n");
                    int b = scanner.nextInt();
                    primeGeneration.Calculate(b);
                }
            }

        }
    }
}
