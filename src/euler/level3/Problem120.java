package euler.level3;

import euler.Problem;

public class Problem120 extends Problem<Long> {

    // (a-1)^2 = -2a + 1 % a^2
    // (a-1)^3 = 3a - 1 % a^2
    // (a-1)^4 = -4a + 1 % a^2
    //
    // (a+1)^2 = 2a + 1 % a^2
    // (a+1)^3 = 3a + 1 % a^2
    //
    // (a-1)^n = an - 1 | 1 - an
    // (a+1)^n = an + 1
    //
    // (a-1)^n + (a+1)^n = 2an | 2 % a^2
    //
    // max = 2na, 2n < a
    // a is odd => 2n = a - 1
    // a is even => 2n = a - 2
    private long calcRmax(int a) {
        long n2 = a - (2 - (a & 1));
        return n2 * a;
    }

    @Override
    public Long solve() {
        long sum = 0;
        for (int a = 3; a <= 1000; a++) {
            sum += calcRmax(a);
        }
        return sum;
    }

}
