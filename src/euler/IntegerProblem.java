package euler;

public abstract class IntegerProblem extends Problem {
    @Override
    public Number execute(Number expected) {
        try {
            long solution = solve();
            if (expected == null) {
                return solution;
            } else if (expected.longValue() == solution) {
                return expected;
            } else {
                return solution;
            }
        } catch (Exception ex) {
            // No solution found!
            if (ex.getMessage() != null) {
                System.out.println("Could not solve: " + ex.getMessage());
            }
            ex.printStackTrace();
            return null;
        }
    }

    public abstract long solve() throws Exception;
}
