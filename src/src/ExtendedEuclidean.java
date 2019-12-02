public class ExtendedEuclidean {

    private int s, t, gcd;

    ExtendedEuclidean() {
        s = t = gcd = 0;
    }

    public int Calculate(int a, int b) {

        int r1, r2, s1, s2 = 0, t1 = 0, t2, q;
        s1 = t2 = 1;
        r1 = Math.abs(Math.max(a, b));
        r2 = Math.abs(Math.min(a, b));

        while (r2 != 0) {
            q = (int) Math.floor(r1 / r2);
            gcd = r1 % r2;

            s = s1 - (s2 * q);
            t = t1 - (t2 * q);

            s1 = s2;
            s2 = s;

            t1 = t2;
            t2 = t;

            r1 = r2;
            r2 = gcd;
        }
        s = s1;
        t = t1;
        handleTheNegative(a, b);
        // Return gcd
        return r1;
    }

    private void handleTheNegative(int a, int b) {
        if (a > b) {
            if (a < 0)
                this.s = -1 * this.s;

            if (b < 0)
                this.t = -1 * this.t;
        } else {
            if (a < 0)
                this.t = -1 * this.t;

            if (b < 0)
                this.s = -1 * this.s;
        }
    }

    public int getS() {
        return this.s;
    }

    public int getT() {
        return t;
    }
}
