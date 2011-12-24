package euler.sequence;

import java.util.SortedSet;
import java.util.TreeSet;

public class SumDigitsPowerSequence implements Sequence<Long> {
    private static class PowerPair implements Comparable<PowerPair> {
        private final int base, pow;
        private final long result;

        public PowerPair(int base, int pow, long result) {
            super();
            this.base = base;
            this.pow = pow;
            this.result = result;
        }

        @Override
        public int compareTo(PowerPair o) {
            if (result == o.result) {
                return base - o.base;
            } else {
                return result < o.result ? -1 : 1;
            }
        }

        public int getBase() {
            return base;
        }

        public int getPow() {
            return pow;
        }
    }

    public static void main(String[] args) {
        SumDigitsPowerSequence seq = new SumDigitsPowerSequence();
        for (int ix = 0; ix < 30; ix++) {
            System.out.println(seq.next());
        }
    }

    private final static long pow(long base, int pow) {
        if (pow == 0) {
            return 1;
        } else if (pow == 1) {
            return base;
        } else {
            long res = pow(base, pow / 2);
            res = res * res;
            if ((pow & 1) == 1) {
                return res * base;
            } else {
                return res;
            }
        }
    }

    private final SortedSet<PowerPair> powers;

    private int position;

    public SumDigitsPowerSequence() {
        powers = new TreeSet<SumDigitsPowerSequence.PowerPair>();
        for (int base = 2; base < 100; base++) {
            powers.add(new PowerPair(base, 1, base));
        }
        position = 0;
    }

    private long calcNext() {
        while (true) {
            PowerPair first = powers.first();
            powers.remove(first);

            int base = first.getBase();
            int pow = first.getPow() + 1;
            long result = pow(base, pow);

            if (result > 0) {
                powers.add(new PowerPair(base, pow, result));

                if (sumDigits(result) == base) {
                    return result;
                }
            }
        }
    }

    @Override
    public Long next() {
        long current = calcNext();
        position++;
        return current;
    }

    @Override
    public int position() {
        return position;
    }

    private int sumDigits(long nr) {
        int sum = 0;
        while (nr > 0) {
            sum += nr % 10;
            nr /= 10;
        }
        return sum;
    }

}
