public class Power {

    // the program will break if the integer passing to the Calculate function is overFlowing the "Long" maxValue

    private long recursiveFastExp(long base, long exp, long mod)
    {
        if (exp == 0)
            return 1;


        long x = recursiveFastExp(base, exp / 2, mod) % mod;
        x *= x;
        x %= mod;

        // if the exp is odd
        if (exp % 2 == 1)
            x *= base;

        return x % mod;
    }

    private long iterativeFastExp(long base, long exp, long mod)
    {
        long x = 1;
        long power = base % mod;

        int onBit = 1;

        while (exp > 0)
        {
            if ((exp & onBit) == 1)
                x = (power * x) % mod;

            power = (power * power) % mod;
            exp = exp >> 1;
        }
        return x;
    }

    private long naiveOne(long base, long exp, long mod)
    {
        long c = 1;

        for (int i = 1; i <= exp; i++)
        {
            c = c * base;
            c %= mod;
        }

        return c;
    }

    private long naiveTwo(long base, long exp, long mod)
    {
        long c = 1;
        for (int i = 1; i <= exp; i++)
            c = (c * base) % mod;

        return c;
    }

    public void Calculate(long base, long exp, long mod)
    {
        System.out.println("Using Recursive fast Exponentiation");
        final long recursiveFirstTime = System.nanoTime();
        long res = recursiveFastExp(base, exp, mod);
        final long recursiveLastTime = System.nanoTime();

        System.out.println(base + "^" + exp + " mod " + mod + " = " + res + "\n Execution Time : " + (recursiveLastTime - recursiveFirstTime)/1000000 + "ms\n\n");

        System.out.println("Using Iterative fast Exponentiation");
        final long IterativeFirst = System.nanoTime();
        res = iterativeFastExp(base, exp, mod);
        final long IterativeLast = System.nanoTime();

        System.out.println(base + "^" + exp + " mod " + mod + " = " + res + "\n Execution Time : " + (IterativeLast - IterativeFirst)/1000000 + "ms\n\n");

        System.out.println("Using Naive1 Method");
        final long naive1First = System.nanoTime();
        res = naiveOne(base, exp, mod);
        final long naive1last = System.nanoTime();

        System.out.println(base + "^" + exp + " mod " + mod + " = " + res + "\n Execution Time : " + (naive1last - naive1First)/1000000 + "ms\n\n");

        System.out.println("Using Recursive fast Exponentiation");
        final long naive2first = System.nanoTime();
        res = naiveTwo(base, exp, mod);
        final long naive2last = System.nanoTime();

        System.out.println(base + "^" + exp + " mod " + mod + " = " + res + "\n Execution Time : " + (naive2last - naive2first)/1000000 + "ms\n\n");



    }




}
