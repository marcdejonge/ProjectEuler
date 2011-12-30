package euler.level3;

import java.util.Arrays;

import euler.Problem;
import euler.formulas.PolynomialFormula;
import euler.numberic.NumericUtils;

public class Problem101 extends Problem<Long> {

    @Override
    public Long solve() {
        PolynomialFormula un = new PolynomialFormula();
        int factor = 1;
        for (int power = 0; power <= 10; power++) {
            un.setFactor(factor, power);
            factor *= -1;
        }

        long sum = 0;
        for (int length = 1; length <= un.getFilled(); length++) {
            PolynomialFormula formula = new PolynomialFormula();
            solve(un.getValues(length), formula, length - 1);
            sum += formula.getValue(length + 1);
        }

        return sum;
    }

    private void solve(long[] input, PolynomialFormula formula, int power) {
        if (input.length == 1) {
            formula.setFactor(input[0], power);
        } else {
            long[] diffs = Arrays.copyOf(input, input.length);
            for (int length = input.length - 1; length > 0; length--) {
                for (int ix = 0; ix < length; ix++) {
                    diffs[ix] = diffs[ix + 1] - diffs[ix];
                }
            }

            int pow = input.length - 1;
            long factor = diffs[0] / NumericUtils.fact(pow);
            formula.setFactor(factor, power);
            long[] newInput = new long[pow];
            for (int ix = 0; ix < newInput.length; ix++) {
                newInput[ix] = input[ix] - factor * NumericUtils.pow(ix + 1, pow);
            }

            solve(newInput, formula, power - 1);
        }
    }
}
