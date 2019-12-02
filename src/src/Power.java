public class Power {

    public long recursiveFastExp(long base, long exp, long mod)
    {
        if (exp == 0)
            return 1;

        long x = recursiveFastExp(base, exp / 2, mod) % mod;
        x *= x;
        x %= mod;

        if (exp % 2 == 1)
            x *= base;

        return x % mod;
    }

    public long iterativeFastExp(long base, long exp, long mod)
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

    public long naiveOne(long base, long exp, long mod)
    {
        long c = 1;

        for (int i = 1; i < exp; i++)
        {
            c = c * base;
            c %= mod;
        }

        return c;
    }

    public long naiveTwo(long base, long exp, long mod)
    {
        long c = 1;
        for (int i = 1; i < exp; i++)
            c = (c * base) % mod;

        return c;
    }




}
