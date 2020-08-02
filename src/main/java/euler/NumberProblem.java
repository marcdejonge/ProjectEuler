package euler;


public abstract class NumberProblem extends Problem {
    @Override
    public Number execute(Number expected) {
        try {
            euler.numberic.Number solution = solve();
            if (solution.equals(expected)) {
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

    public abstract euler.numberic.Number solve();
}
