package euler.level2;

import euler.IntegerProblem;
import euler.numberic.Number;
import euler.sequence.Primes;

public class Problem070 extends IntegerProblem {

    @Override
    public long solve() {
        double min = Double.MAX_VALUE;
        int solution = 0;

        final Primes ps1 = new Primes();
        ps1.get(10);
        final Primes ps2 = new Primes();
        boolean active = true;
        for (int p1 = (int) ps1.next(); active; p1 = (int) ps1.next()) {
            ps2.copyFrom(ps1);
            active = false;
            for (int p2 = (int) ps2.next(), nr = p1 * p2; nr < 10000000; p2 = (int) ps2.next(), nr = p1 * p2) {
                active = true;
                final int phi = (p1 - 1) * (p2 - 1);

                if (Number.valueOf(phi).sort().equals(Number.valueOf(nr).sort())) {
                    final double factor = (double) nr / phi;
                    if (factor < min) {
                        min = factor;
                        solution = nr;
                    }
                }
            }
        }

        return solution;
    }

}
