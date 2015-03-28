package euler;

import java.math.BigInteger;

public abstract class BigIntegerProblem extends Problem {
    @Override
    public Number execute(Number expected) {
        try {
            BigInteger solution = solve();
            if (expected == null) {
                return solution;
            } else if (expected instanceof BigInteger && solution.equals(expected)) {
                return expected;
            } else if (solution.equals(BigInteger.valueOf(expected.longValue()))) {
                return expected;
            } else {
                return solution;
            }
        } catch (Exception ex) {
            // No solution found!
            if (ex.getMessage() != null) {
                System.out.println("Could not solve: " + ex.getMessage());
            } else {
                System.out.println("Could not solve problem because of exception");
            }
            ex.printStackTrace();
            return null;
        }
    }

    public abstract BigInteger solve();
}
