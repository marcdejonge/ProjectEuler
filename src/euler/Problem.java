package euler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Problem {
    private static final Map<Integer, java.lang.Number> knownSolutions = new HashMap<Integer, java.lang.Number>();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader("solutions.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        String[] parts = line.split("\t");
                        int problemNumber = Integer.valueOf(parts[0]);
                        Number solution = null;
                        if (parts[1].contains(".")) {
                            solution = Double.valueOf(parts[1]);
                        } else if (parts[1].length() > 19) {
                            solution = new BigInteger(parts[1]);
                        } else {
                            solution = Long.valueOf(parts[1]);
                        }
                        knownSolutions.put(problemNumber, solution);
                    } catch (Exception ex) {
                        System.err.println("Error while parsing line [" + line + "], not a valid solution description");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean debug = false;

    protected static final void println(String line) {
        if (debug) {
            System.out.println(line);
        }
    }

    protected static final void print(String format, Object... objects) {
        if (debug) {
            System.out.printf(format, objects);
        }
    }

    protected static final void print(String description, int[] nrs) {
        if (debug) {
            System.out.println(description + ": " + Arrays.toString(nrs));
        }
    }

    private static final long execute(int nr) {
        Problem problem = null;
        final int level = (nr - 1) / 50 + 1;
        try {
            final Class<?> clazz = Class.forName(String.format("euler.level%d.Problem%03d", level, nr));
            problem = (Problem) clazz.newInstance();
        } catch (final ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be found", nr));
        } catch (final InstantiationException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be instantiated", nr));
        } catch (final IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be accessed", nr));
        }

        return execute(problem, knownSolutions.get(nr));
    }

    private static final long execute(Problem problem, java.lang.Number knownSolution) {
        final long start = System.nanoTime();
        final Number result = problem.execute(knownSolution);
        final long time = System.nanoTime() - start;

        if (result == null) {
            System.out.printf("          Result not found for %-20s Calculated in %5.2f seconds%n",
                              problem.getClass().getSimpleName(),
                              time / 1e9);
            return -1;
        } else {
            final String checked = knownSolution == null ? "Unchecked" : result == knownSolution ? "Correct" : "Incorrect";
            System.out.printf("%9s result for %s: %20s Calculated in %5.3f seconds%n",
                              checked,
                              problem.getClass().getSimpleName(),
                              result.toString(),
                              time / 1e9);
            return time;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            final int MAX = 400;

            System.out.println("Executing all problems...");
            int found = 0, executed = 0, missing = 0;
            long totalTime = 0;
            for (int i = 1; i < MAX; i++) {
                try {
                    final long time = Problem.execute(i);
                    if (time >= 0) {
                        found++;
                        totalTime += time;
                    }
                    executed++;
                } catch (final IllegalArgumentException ex) {
                    if (knownSolutions.containsKey(i)) {
                        System.out.println(ex.getMessage());
                        missing++;
                    }
                }
            }
            System.out.printf("Problems solved: %d out of %d (+%d missing)\tTotal duration: %5.2f seconds%n",
                              found,
                              executed,
                              missing,
                              totalTime / 1e9);
        } else {
            int nr = 1;
            try {
                debug = true; // Print out any debug info only when running in single mode
                nr = Integer.parseInt(args[0]);
            } catch (final NumberFormatException ex) {
                System.out.println("Please give the number of the problem that you want to solve!");
                return;
            }

            Problem.execute(nr);
        }
    }

    /**
     * @param expected
     *            The expected output of the solution.
     * @return When the solution found is the same as the expected, we want the same object to be returned. Otherwise,
     *         return the calculated value. When no solution has been found, this method should return null.
     */
    public abstract Number execute(Number expected);
}
