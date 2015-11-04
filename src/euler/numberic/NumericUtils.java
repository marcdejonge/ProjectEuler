package euler.numberic;

import java.util.Arrays;

public class NumericUtils {
    public static int fact(int nr) {
        if (nr == 1) {
            return 1;
        } else {
            return nr * fact(nr - 1);
        }
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int[] initArray(int length, int... values) {
        int[] array = new int[length];
        Arrays.fill(array, values[values.length - 1]);
        System.arraycopy(values, 0, array, 0, Math.min(length, values.length));
        return array;
    }

    public static long inverseMod(long a, long mod) {
        long inverse = 0, newInverse = 1;
        long gcd = mod, newGcd = a;

        while (newGcd != 0) {
            long q = gcd / newGcd;

            long tempInverse = newInverse;
            newInverse = inverse - q * newInverse;
            inverse = tempInverse;

            long tempGcd = newGcd;
            newGcd = gcd - q * newGcd;
            gcd = tempGcd;
        }

        if (gcd > 1) {
            throw new IllegalArgumentException(a + " is not invertable, not coprime with " + mod);
        }
        if (inverse < 0) {
            inverse += mod;
        }
        return inverse;
    }

    public static boolean isSquare(long nr) {
        long sq = (long) Math.sqrt(nr);
        return sq * sq == nr;
    }

    public static long pow(int x, int n) {
        long res;
        if (n == 0) {
            return 1;
        } else if ((n & 1) == 1) {
            res = NumericUtils.pow(x, n >>> 1);
            return x * res * res;
        } else {
            res = NumericUtils.pow(x, n >>> 1);
            return res * res;
        }
    }

    public static int[] recurringCycle(final int value) {
        int[] digits = new int[128];
        int[] remainders = new int[128];
        int filled = 0;

        int divider = 10;
        while (true) {
            final int div = divider / value;
            final int rem = divider - div * value;

            int ix = -1;
            for (int i = 0; i < filled; i++) {
                if (remainders[i] == rem) {
                    ix = i;
                    break;
                }
            }

            if (ix >= 0) {
                final int[] result = new int[filled - ix];
                System.arraycopy(digits, ix, result, 0, filled - ix);
                return result;
            }

            if (filled >= digits.length) {
                int[] temp = new int[filled * 2];
                System.arraycopy(digits, 0, temp, 0, filled);
                digits = temp;

                temp = new int[filled * 2];
                System.arraycopy(remainders, 0, temp, 0, filled);
                remainders = temp;
            }

            digits[filled] = div;
            remainders[filled] = rem;
            filled++;
            divider = rem * 10;
        }
    }

    public static int sum(int[] array) {
        int sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

    // Can never be created an instance of
    private NumericUtils() {
    }
}
