package euler.sequence;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import euler.numberic.BitSet;

public class AmicableChain {
    public static class AmicableChainBuilder {
        private final int max;

        private final Set<Integer> usedNrs;

        private final BitSet bsNoSolution;

        private final int[] arrTemp;

        private final int[] sums;

        public AmicableChainBuilder(final int max) {
            this.max = max;
            usedNrs = new HashSet<Integer>(16);
            bsNoSolution = new BitSet(max);
            arrTemp = new int[max];
            sums = new int[max];

            // long startTime = System.nanoTime();
            for (int mult = 1; mult < max / 2; mult++) {
                for (int ix = mult * 2; ix < max; ix += mult) {
                    sums[ix] += mult;
                }
            }
            // System.out.printf("Time spend finding divisors: %1.3f seconds%n", (System.nanoTime() - startTime) / 1e9);
            // System.out.println(Arrays.toString(Arrays.copyOf(sums, 100)));
        }

        public AmicableChain create(int nr) {
            if (nr >= max || bsNoSolution.isSet(nr)) {
                return null;
            }

            // Start the loop to detect any possible chain
            usedNrs.clear();
            int endIx = 0;
            for (endIx = 0; !usedNrs.contains(nr); endIx++) {
                usedNrs.add(nr);
                arrTemp[endIx] = nr;

                nr = sums[nr];

                // Return null when the number is above the maximum
                if (nr >= max) {
                    for (int ix = 0; ix < endIx; ix++) {
                        bsNoSolution.set(arrTemp[ix]);
                    }
                    return null;
                }
            }

            // A cycle of 1 is just ignored
            if (nr == arrTemp[endIx - 1]) {
                for (int ix = 0; ix < endIx; ix++) {
                    bsNoSolution.set(arrTemp[ix]);
                }
                return null;
            }

            // Find the first value of the real chain
            int startIx = 0;
            while (arrTemp[startIx] != nr) {
                startIx++;
            }

            // Now find the minimal value
            int minIx = 0;
            int minValue = Integer.MAX_VALUE;
            for (int ix = startIx; ix < endIx; ix++) {
                if (arrTemp[ix] < minValue) {
                    minValue = arrTemp[ix];
                    minIx = ix;
                }
            }

            // Copy the cycle out
            int[] cycle = new int[endIx - startIx];
            System.arraycopy(arrTemp, minIx, cycle, 0, endIx - minIx);
            System.arraycopy(arrTemp, startIx, cycle, endIx - minIx, minIx - startIx);
            return new AmicableChain(cycle, minValue);
        }
    }

    private final int[] cycle;
    private final int minimum;

    AmicableChain(int[] cycle, int minimum) {
        this.cycle = cycle;
        this.minimum = minimum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            AmicableChain other = (AmicableChain) obj;
            if (minimum != other.minimum) {
                return false;
            } else if (!Arrays.equals(cycle, other.cycle)) {
                return false;
            }
            return true;
        }
    }

    public Iterable<Integer> getCycle() {
        return new IntArrayIterable(cycle);
    }

    public int getMinimum() {
        return minimum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(cycle);
        result = prime * result + minimum;
        return result;
    }

    public int length() {
        return cycle.length;
    }

    @Override
    public String toString() {
        return "Amicable Chain " + Arrays.toString(cycle);
    }

}
