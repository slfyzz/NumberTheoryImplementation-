import java.util.Scanner;

public class Main {

    public static void main(String []args)
    {
        Power power = new Power();
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a:");
            int a = scanner.nextInt();
            System.out.println("Enter b");
            int b = scanner.nextInt();
            System.out.println("Enter m");
            int m = scanner.nextInt();

            System.out.println(power.iterativeFastExp(a, b, m));
         //   System.out.println("GCD is " + extendedEuclidean.gcd() + "\n s = " + extendedEuclidean.getS() + "\n t = " + extendedEuclidean.getT());

        }
    }
}
