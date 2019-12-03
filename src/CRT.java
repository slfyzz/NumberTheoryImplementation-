import java.math.BigInteger;


// we are using BigInteger to avoid overflow in the domain of Zm

public class CRT {

    // the inner class is a representation of the Integer as [A mod m1, A mod m2, .... ]
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

        // turn all the integers into BIGINTEGERS
        BigInteger AA = BigInteger.valueOf(A);
        BigInteger BB = BigInteger.valueOf(B);
        BigInteger MM = BigInteger.valueOf(1);

        //Calculate the value of M (the multiplication of all moduli)
        for (long i1 : m) {
            MM = MM.multiply(BigInteger.valueOf(i1));
        }

        // calculate the addition and the multiplication using the BigInteger to avoid overflow

        BigInteger add = AA.add(BB).mod(MM);
        BigInteger multi = AA.multiply(BB).mod(MM);

        final long endTime = System.nanoTime();
        System.out.println("in the Zm domain");
        System.out.println("A : " + A + "\nB : " + B +"\nA + B = " + add.toString() + "\nA * B = " + multi.toString() + "\nExecution Time : " + (endTime - startTime)/1 + "ns");

        final long chineseStartTime = System.nanoTime();

        // using the inner class to represent the integer in terms of A mod mi
        nTuple nA = new nTuple(m, A);
        nTuple nB = new nTuple(m, B);

        // doing the operations
        nTuple addition = nA.add(nB);
        nTuple multiplication = nA.multiply(nB);

        // getting the unique Integer Using Chinese Remainder Theorem
        long addresult = solveCongruences(addition);
        long multiresult = solveCongruences(multiplication);

        final long chineseEndTime = System.nanoTime();

        System.out.println("\nin the Z1 * Z2 * .... * Zn domain");
        System.out.println("A : " + A + "\nB : " + B +"\nA + B = " + addresult + "\nA * B = " + multiresult + "\nExecution Time : " + (chineseEndTime - chineseStartTime)/1 + "ns");
    }

    private long solveCongruences(nTuple nTuple)
    {
        long ans = 0;
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();

        long [] arr = new long[(int) nTuple.getSize()];
        long m = 1;

        //Calculate the value of M (the multiplication of all moduli)
        for (int i = 0; i < nTuple.getSize(); i++) {
            m *= nTuple.moduli[i];
            arr[i] = nTuple.nums[i];
        }

        // calculate the inverse of Mi mod mi and getting the X (ans) where X = a1 * M1 * y1 + a2 * M2 * y2 .....
        // y is the inverse
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
