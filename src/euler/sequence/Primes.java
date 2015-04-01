package euler.sequence;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class Primes extends AbstractSequence {
    public static class FactorCalculator {
        private static long[] factors = new long[128];

        private static int filled = 0;

        public final static long[] calc(long value, boolean distinct) {
            final Primes primes = new Primes();
            FactorCalculator.filled = 0;

            for (long prime = primes.next(); value > 1; prime = primes.next()) {
                if (value % prime == 0) {
                    value /= prime;
                    FactorCalculator.factors[FactorCalculator.filled++] = prime;
                    while (value % prime == 0) {
                        value /= prime;
                        if (!distinct) {
                            FactorCalculator.factors[FactorCalculator.filled++] = prime;
                        }
                    }
                }
            }

            final long[] result = new long[FactorCalculator.filled];
            System.arraycopy(FactorCalculator.factors, 0, FactorCalculator.factors, 0, FactorCalculator.filled);
            return result;
        }
    }

    /**
     * A block of primenumbers that is stored as a set of bits between a lowerBound and upperBound.
     *
     * @author Marc de Jonge
     */
    private static class PrimeNumbers {
        private final long[] bits;

        private PrimeNumbers next;

        private final long lowerBound, upperBound;

        /**
         * Initialized the first set of prime numbers, which is pre-calculated
         */
        private PrimeNumbers() {
            bits = new long[] { 0x816d129a64b4cb6eL,
                                0x2196820d864a4c32L,
                                0xa48961205a0434c9L,
                                0x4a2882d129861144L,
                                0x834992132424030L,
                                0x148a48844225064bL,
                                0xb40b4086c304205L,
                                0x65048928125108a0L };
            lowerBound = 1;
            upperBound = lowerBound + bits.length * 128;
        }

        /**
         * Calculates the next set of prime numbers, based on the previous set.
         *
         * @param last
         *            The previous set of prime numbers to base the next block on
         */
        private PrimeNumbers(PrimeNumbers last) {
            // We can store the primenumbers starting from the end of the last block
            lowerBound = last.upperBound;
            // Calculate the size of the next block
            bits = new long[Math.min(last.bits.length * 2, 131072) * NR_OF_PROCESSORS];
            final long blocksize = bits.length / NR_OF_PROCESSORS;
            // Start out with everything as 'prime'
            for (int i = 0; i < bits.length; i++) {
                bits[i] = 0xffffffffffffffffl;
            }
            // And we can store until this number
            upperBound = lowerBound + bits.length * 128;

            // System.out.print("Calculating primes until " + endNr + "...");

            // Use the ForkJoinPool to do the calculation parallel
            final List<ForkJoinTask<Void>> tasks = new ArrayList<ForkJoinTask<Void>>(NR_OF_PROCESSORS);
            for (int tix = 0; tix < NR_OF_PROCESSORS; tix++) {
                final long startNr = lowerBound;
                final long[] bits = this.bits;
                final long start = startNr + blocksize * 128 * tix;
                final long end = start + blocksize * 128;
                final ForkJoinTask<Void> task = new RecursiveAction() {
                    private static final long serialVersionUID = 554787616622609351L;

                    @Override
                    protected void compute() {
                        // Use the Primes sequence class to read the real prime numbers
                        final Primes primes = new Primes();
                        long prime = primes.next(); // Skip the 2
                        // Check for each prime
                        while ((prime = primes.next()) * prime < end) {
                            final long add = prime * 2;
                            long mult = start / add * add + prime;
                            while (mult < end) {
                                if (mult >= start) {
                                    // Use bitmasking to disable the composite numbers
                                    int ix = (int) (mult - startNr);
                                    ix >>>= 1;
            bits[ix >>> 6] &= 0xffffffffffffffffL ^ 1L << ix;
                                }
                                mult += add;
                            }
                        }
                    }
                };
                FORK_JOIN_POOL.execute(task);
                tasks.add(task);
            }

            for (final ForkJoinTask<Void> task : tasks) {
                task.join();
            }
            // System.out.println("done");

            // Link this new instance to the previous
            last.next = this;
            currentMax = upperBound;

            // Already start on the next block
            if (upperBound < 1000000l) {
                new PrimeNumbers(this);
            }
        }

        /**
         * @return The next PrimeNumbers block. This will calculate the next block, when it has not been asked before.
         */
        PrimeNumbers getNext() {
            while (next == null) {
                synchronized (this) {
                    if (next == null) {
                        new PrimeNumbers(this);
                    }
                }
            }
            return next;
        }

        /**
         * Checks if the given number is prime using the sieve. This should only be called on the first block and will
         * be called recursively on the next linked blocks when this block doesn't have the number.
         *
         * @param nr
         *            The number that needs to be checked for primeness.
         * @return true when the given number is prime, false otherwise.
         */
        boolean isPrime(long nr) {
            if ((nr & 1) == 0) {
                return nr == 2;
            } else if (nr < lowerBound) {
                return false;
            } else if (nr >= upperBound) {
                return getNext().isPrime(nr);
            }

            int ix = (int) (nr - lowerBound);
            ix >>>= 1;

            return (bits[ix >>> 6] >>> ix & 1L) == 1L;
        }

        /**
         * Finds the next prime number, given the previous one.
         *
         * @param lastPrime
         *            The previous number from which the search should start.
         * @return The first prime that is bigger that the lastPrime.
         */
        long nextPrime(long lastPrime) {
            int ix = lastPrime < lowerBound ? 0 : (int) (lastPrime - lowerBound + 2);
            ix >>>= 1;

            int wordIx = ix >>> 6;
            if (wordIx >= bits.length) {
                return getNext().nextPrime(lastPrime);
            }
            long word = bits[wordIx] & -1L << ix;

            while (word == 0) {
                if (++wordIx >= bits.length) {
                    return getNext().nextPrime(lastPrime);
                }

                word = bits[wordIx];
            }

            return lowerBound + ((wordIx << 6) + Long.numberOfTrailingZeros(word)) * 2;
        }
    }

    public static final int NR_OF_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    private final static PrimeNumbers primeNumbers = new PrimeNumbers();

    static long currentMax = primeNumbers.upperBound;

    private static long calculateMemoryUsage() {
        for (int i = 0; i < 16; i++) {
            System.gc();
        }
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public final static boolean isPrime(long nr) {
        if (nr < currentMax) {
            return primeNumbers.isPrime(nr);
        } else {
            final Primes ps = new Primes();
            for (long p = ps.next(); p * p <= nr; p = ps.next()) {
                if (nr % p == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        long mem0 = calculateMemoryUsage();

        long start = System.nanoTime();
        int ix = 0;

        for (long prime : new Primes()) {
            if (prime > 1000000000) {
                break;
            }
            ix++;
        }
        long duration = System.nanoTime() - start;
        System.out.println(ix);
        System.out.println(duration / 1e9);
        System.out.println(calculateMemoryUsage() - mem0);
    }

    public final static int nrOfDivisors(long nr) {
        final Primes p = new Primes();

        int cnt = 1;
        for (long prime = p.next(); nr > 1; prime = p.next()) {
            int exp = 1;
            while (nr % prime == 0) {
                exp++;
                nr /= prime;
            }
            cnt *= exp;
        }

        return cnt;
    }

    public final static int nrOfPrimeFactors(long nr, boolean distinct) {
        final Primes p = new Primes();

        int cnt = 0;
        for (long prime = p.next(); prime * prime < nr; prime = p.next()) {
            if (nr % prime == 0) {
                cnt++;
                nr /= prime;

                while (nr % prime == 0) {
                    if (!distinct) {
                        cnt++;
                    }
                    nr /= prime;
                }
            }
        }

        if (nr > 1) {
            cnt++;
        }

        return cnt;
    }

    public final static long phi(final long nr) {
        final Primes primes = new Primes();

        long left = nr;
        long result = nr;
        for (long prime = primes.next(); prime * prime <= left; prime = primes.next()) {
            if (left % prime == 0) {
                result -= result / prime;
                left /= prime;

                while (left % prime == 0) {
                    left /= prime;
                }
            }
        }

        if (left > 1) {
            result -= result / left;
        }

        return result;
    }

    /*
     * public final static int phi(final int nr) { Primes p = new Primes();
     *
     * int left = nr; int result = nr; for (int prime = (int) p.next(); prime * prime < left; prime = (int) p.next()) {
     * if (left % prime == 0) { result = (result - (result / prime)); left /= prime;
     *
     * while (left % prime == 0) { left /= prime; } } }
     *
     * if (left > 1) { result = result - (result / left); }
     *
     * return result; }
     */

    public static long smallestDivisor(long i) {
        final Primes ps = new Primes();
        for (long p : ps) {
            if (i % p == 0) {
                return p;
            } else if (p * p > i) {
                return i;
            }
        }
        throw new AssertionError("There should always be a smallest divisor");
    }

    public final static long sumOfDivisors(long nr) {
        final Primes p = new Primes();

        long sum = 1;
        for (long prime = p.next(); nr > 1 && prime * prime <= nr; prime = p.next()) {
            if (nr % prime == 0) {
                long j = prime;
                while (nr % prime == 0) {
                    j *= prime;
                    nr /= prime;
                }
                sum *= j - 1;
                sum /= prime - 1;
            }
        }
        if (nr > 1) {
            sum *= nr + 1;
        }
        return sum;
    }

    public final static long sumOfProperDivisors(long nr) {
        return Primes.sumOfDivisors(nr) - nr;
    }

    private int add, pos;

    private PrimeNumbers current;

    private long nr;

    public Primes() {
        reset();
    }

    public void copyFrom(Primes other) {
        current = other.current;
        nr = other.nr;
        pos = other.pos;
        add = other.add;
    }

    @Override
    public long current() {
        return nr;
    }

    @Override
    public long next() {
        if (nr == 1 || nr == 2) {
            nr++;
        } else if (nr == 3) {
            nr = 5;
        } else {
            if (nr > current.upperBound) {
                current = current.getNext();
            }
            nr = current.nextPrime(nr);
        }

        pos++;
        return nr;
    }

    @Override
    public long position() {
        return pos;
    }

    @Override
    public Primes reset() {
        current = Primes.primeNumbers;
        nr = current.lowerBound;
        pos = 0;
        add = 2;
        return this;
    }

    @Override
    public String toString() {
        return "Prime Sequence (now at " + current() + ")";
    }
}
