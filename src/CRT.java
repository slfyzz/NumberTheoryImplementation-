public class CRT {

    private class nTuple{
        private int size;
        private int[] moduli;
        private int[] nums;

        nTuple(int[] moduli, int A)
        {
            this.moduli = moduli;
            this.size = moduli.length;
            this.nums = initialize(A);
        }
        nTuple(int[] moduli, int[] tuples)
        {
            this.moduli = moduli;
            this.size = moduli.length;
            this.nums = tuples;
        }

        private int[] initialize(int A) {

            nums = new int[size];
            for (int i = 0; i < size; i++) {
                nums[i] = A % moduli[i];
            }
            return nums;
        }

        public int getSize() {
            return size;
        }

        public nTuple add (nTuple B)
        {
            if (this.size != B.getSize())
            {
                throw new RuntimeException();
            }
            int[] arr = new int[size];

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
            int[] arr = new int[size];

            for (int i = 0; i < size; i++)
            {
                arr[i] = (this.nums[i] * B.nums[i]) % this.moduli[i];
            }
            return new nTuple(this.moduli, arr);
        }
    }

    public void Calculate(int A, int B, int[]m)
    {
        nTuple nA = new nTuple(m, A);
        nTuple nB = new nTuple(m, B);

        nTuple addition = nA.add(nB);
        nTuple multiplication = nA.multiply(nB);

        System.out.println("A : " + A + "\n B : " + B +"\nA + B = " + solveCongruences(addition) + "\nA * B = " + solveCongruences(multiplication));
    }

    private long solveCongruences(nTuple nTuple)
    {
        long ans = 0;
        ExtendedEuclidean extendedEuclidean = new ExtendedEuclidean();

        long [] arr = new long[nTuple.getSize()];
        long m = 1;

        for (int i = 0; i < nTuple.getSize(); i++) {
            m *= nTuple.moduli[i];
            arr[i] = nTuple.nums[i];
        }

        for (int i = 0; i < nTuple.getSize(); i++) {
            extendedEuclidean.Calculate((int)(m / arr[i]),(int) arr[i]);
            arr[i] *= (long) Math.floor(m / arr[i]) * extendedEuclidean.getS();
            ans += arr[i];
        }

    return ans;
    }
}
