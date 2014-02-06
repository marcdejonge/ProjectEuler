package euler;

public abstract class FloatingPointProblem extends Problem {
    @Override
    public Number execute(Number expected) {
        try {
            double solution = solve();
            if (Math.abs(expected.doubleValue() - solution) < 1e-20 * solution) {
                return expected;
            } else {
                return solution;
            }
        } catch (Exception ex) {
            // No solution found!
            if (ex.getMessage() != null) {
                System.out.println("Could not solve: " + ex.getMessage());
            }
            return null;
        }
    }

    public abstract double solve();
}
