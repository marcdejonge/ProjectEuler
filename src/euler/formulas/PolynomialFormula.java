package euler.formulas;

import java.util.Arrays;

import euler.numberic.NumericUtils;

public class PolynomialFormula {
    private long[] factors;
    private int filled;

    public PolynomialFormula() {
        factors = new long[1];
    }

    public long getFactor(int power) {
        if (power < factors.length) {
            return factors[power];
        } else {
            return 0;
        }
    }

    public int getFilled() {
        return filled;
    }

    public long getValue(int x) {
        long result = 0;
        for (int pow = 0; pow < factors.length; pow++) {
            result += factors[pow] * NumericUtils.pow(x, pow);
        }
        return result;
    }

    public long[] getValues(int length) {
        long[] result = new long[length];
        for (int ix = 0; ix < length; ix++) {
            result[ix] = getValue(ix + 1);
        }
        return result;
    }

    public void setFactor(long factor, int power) {
        if (power >= factors.length) {
            factors = Arrays.copyOf(factors, Math.max(power + 1, factors.length * 2));
        }
        factors[power] = factor;
        filled = Math.max(filled, power);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int ix = 0; ix <= filled; ix++) {
            long factor = factors[ix];
            if (factor != 0) {
                if (factor < 0) {
                    sb.append(" - ");
                } else if (!first) {
                    sb.append(" + ");
                }

                first = false;

                if (factor != 1 || ix == 0) {
                    sb.append(Math.abs(factor));
                }
                if (ix > 0) {
                    sb.append('x');
                    if (ix > 1) {
                        sb.append('^').append(ix);
                    }
                }
            }
        }
        return sb.toString();
    }
}
