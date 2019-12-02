import java.util.Arrays;
import java.util.Vector;

public class PrimeGeneration {

    private void simpleSieve(int segmentLength, Vector<Integer> firstPrimes) {

        // Boolean array to mark non primes as false
        boolean[] isPrime = new boolean[segmentLength + 1];
        Arrays.fill(isPrime, true);

        // Update all multiples of the first number that is not changed
        for (int p = 2; p*p < segmentLength; p++) {
            if (isPrime[p]) {
                for (int i = p * 2; i < segmentLength; i += p)
                    isPrime[i] = false;
            }
        }

        // Store primes in firstPrimes
        for (int p = 2; p < segmentLength; p++) {
            if (isPrime[p]) {
                firstPrimes.add(p);
                System.out.print(p + "  ");
            }
        }
    }

    public void segmentedSieve(int n) {

        // Find all primes in the first segment
        // Assumption: Length of segment equals square root of n
        int segmentLength = (int) (Math.floor(Math.sqrt(n)) + 1);

        // Vector to store primes of first segment
        Vector<Integer> firstPrimes = new Vector<>();
        simpleSieve(segmentLength, firstPrimes);

        // Looping through segments from 0 to n-1
        int start = segmentLength, end = 2 * segmentLength;
        while (start < n) {
            if (end >= n)
                end = n;

            // Boolean array to mark non primes as false
            boolean[] isPrime = new boolean[segmentLength + 1];
            Arrays.fill(isPrime, true);

            // Boolean to prevent overflow
            boolean flag = false;

            // Use the primes found by simpleSieve() to find the primes in the segment
            for (Integer i : firstPrimes) {

                // Find the minimum number in [start..end] that is divisble by i in firstPrimes
                int minDiv = (int) (Math.floor(start / i) * i);
                if (minDiv < start)
                    minDiv += i;

                // Marking all numbers that are multiples of i in the segment as false
                for (int j = minDiv; j < end; j += i) {
                    if (j - start < isPrime.length) {
                        isPrime[j - start] = false;
                    }
                    else {
                        flag = true;
                        break;
                    }
                }
            }

            // Get out of loop in case of overflow
            if (flag)
                break;

            // Rest of numbers marked as true are primes
            for (int i = start; i < end; i++)
                if (isPrime[i - start])
                    System.out.println(i + " ");

            // Update start and end for next segment
            start = start + segmentLength;
            end = end + segmentLength;
        }
    }
}