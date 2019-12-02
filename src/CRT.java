import java.math.BigInteger;

public class CRT {

    private class nTuple{
        private long size;
        private long[] moduli;
        private long[] nums;

        nTuple(long[] moduli, long A)
        {
            this.moduli = moduli;
            this.size = moduli.length;
            this.nums = initialize(A);
        }
        nTuple(long[] moduli, long[] tuples)
        {
            this.moduli = moduli;
            this.size = moduli.length;
            this.nums = tuples;
        }

        private long[] initialize(long A) {

            nums = new long[(int) size];
            for (int i = 0; i < size; i++) {
                nums[i] = A % moduli[i];
            }
            return nums;
        }

        public long getSize() {
            return size;
        }

        public nTuple add (nTuple B)
        {
            if (this.size != B.getSize())
            {
                throw new RuntimeException();
            }
            long[] arr = new long[(int) size];

            for (int i = 0; i < size; i++)
            {
                arr[i] = (this.nums[i] + B.nums[i]) % this.moduli[i];
            }
            return new nTuple(this.moduli, arr);
        }

        public nTuple multiply(nTuple B)
        {
            if (this.size != B.getSize())
            {
                throw new RuntimeException();
            }
            long[] arr = new long[(int) size];

            for (int i = 0; i < size; i++)
            {
                arr[i] = (this.nums[i] * B.nums[i]) % this.moduli[i];
            }
            return new nTuple(this.moduli, arr);
        }
    }

    public void Calculate(long A, long B, long[]m)
    {
        final long startTime = System.nanoTime();

        BigInteger AA = BigInteger.valueOf(A);
        BigInteger BB = BigInteger.valueOf(B);
        BigInteger MM = BigInteger.valueOf(1);

        for (long i1 : m) {
            MM = MM.multiply(BigInteger.valueOf(i1));
        }

        BigInteger add = AA.add(BB).mod(MM);
        BigInteger multi = AA.multiply(BB).mod(MM);

        final long endTime = System.nanoTime();
        System.out.println("in the Zm domain");
        System.out.println("A : " + A + "\nB : " + B +"\nA + B = " + add.toString() + "\nA * B = " + multi.toString() + "\nExecution Time : " + (endTime - startTime)/1000000 + "ms");

        final long chineseStartTime = System.nanoTime();
        nTuple nA = new nTuple(m, A);
        nTuple nB = new nTuple(m, B);

        nTuple addition = nA.add(nB);
        nTuple multiplication = nA.multiply(nB);

        final long chineseEndTime = System.nanoTime();
        System.out.println("\nin the Z1 * Z2 * .... * Zn domain");
        System.out.println("A : " + A + "\nB : " + B +"\nA + B = " + solveCongruences(addition) + "\nA * B = " + solveCongruences(multiplication)+ "\nExecution Time : " + (chineseEndTime - chineseStartTime)/1000000 + "ms");
    }

    private long solveCongruences(nTuple nTuple)
    {
        long ans = 0;
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();

        long [] arr = new long[(int) nTuple.getSize()];
        long m = 1;

        for (int i = 0; i < nTuple.getSize(); i++) {
            m *= nTuple.moduli[i];
            arr[i] = nTuple.nums[i];
        }

        for (int i = 0; i < nTuple.getSize(); i++) {
            extendedEuclidean.Calculate((int)(m / nTuple.moduli[i]), nTuple.moduli[i]);

            long  s = extendedEuclidean.getS();
            if (s < 0) s = nTuple.moduli[i] + s;

            arr[i] *= (long) Math.floor(m / nTuple.moduli[i]) * s;
            ans += arr[i];
            ans %= m;
        }

    return ans;
    }
}
