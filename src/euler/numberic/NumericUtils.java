package euler.numberic;

public class NumericUtils {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    public static int pow(int x, int n) {
        int res;
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

    // Can never be created an instance of
    private NumericUtils() {
    }
}
