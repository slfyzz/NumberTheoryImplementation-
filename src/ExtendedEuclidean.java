
public class ExtendedEuclidean {

    // there are two different implementation to the same Algorithm  :)
    private long s;
    private long t;
    private long gcd;
    ExtendedEuclidean()
    {
        s = t = gcd = 0;
    }

    public void Calculate(long a, long b)
    {

        long r1, r2, s1, s2 = 0, t1 = 0, t2,q;
        s1 = t2 = 1;
        r1 = Math.abs(Math.max(a, b));
        r2 = Math.abs(Math.min(a, b));

        while (r2 != 0)
        {
            q = (int) Math.floor(r1 / r2);
            gcd = r1 % r2;

            s = s1 - (s2 * q);
            t = t1 - (t2 * q);

            s1 = s2; s2 = s;

            t1 = t2; t2 = t;

            r1 = r2; r2 = gcd;
        }
        gcd = r1;
        s = s1;
        t = t1;
        handleTheNegative(a, b);

        if (b == Math.max(a, b))
        {
            long temp = t;
            t = s;
            s = temp;
        }
    }
    public long gcd () throws RuntimeException {
        if (gcd == 0) {
            throw new RuntimeException();
        }

        return gcd;
    }

    public long getS() throws RuntimeException {
        if (gcd == 0) {
            throw new RuntimeException();
        }
        return s;
    }

    public long getT() throws RuntimeException {
        if (gcd == 0) {
            throw new RuntimeException();
        }
        return t;
    }

    private void handleTheNegative(long a, long
            b)
    {
        if (a > b)
        {
            if (a < 0)
                this.s = -1 * this.s;

            if (b < 0)
                this.t = -1 * this.t;
        }
        else
        {
            if (a < 0)
                this.t = -1 * this.t;

            if (b < 0)
                this.s = -1 * this.s;
        }
    }

    public void  ExtendedGCD(long a, long b) {

        final long first = System.nanoTime();
        gcd = extendedGCD(a, b);
        final long last = System.nanoTime();

        System.out.println("s*a + t*b = gcd(a, b)");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("s = " + s);
        System.out.println("t = " + t);
        System.out.println("gcd(a, b) = " + gcd);
        System.out.println("Execution time : " + (last - first)/1000000 + "ms");
    }

    private long extendedGCD(long a, long b) {

        long[] aa = {1, 0};
        long[] bb = {0, 1};
        long q;
        while(true) {
            q = a / b;
            a = a % b;
            aa[0] = aa[0] - q*aa[1];
            bb[0] = bb[0] - q*bb[1];

            if (a == 0) {
                s = aa[1];
                t = bb[1];
                return b;
            }
            q = b / a;
            b = b % a;
            aa[1] = aa[1] - q*aa[0];
            bb[1] = bb[1] - q*bb[0];

            if (b == 0) {
                s = aa[0];
                t = bb[0];
                return a;
            }
        }
    }

}
