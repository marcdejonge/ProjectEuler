package euler;

import java.util.HashMap;
import java.util.Map;

import euler.numberic.Number;

public abstract class Problem<T> {
	private static final Map<Integer, java.lang.Number> knownSolutions = new HashMap<Integer, java.lang.Number>();
	static {
		knownSolutions.put(1, Number.valueOf(233168));
		knownSolutions.put(2, Number.valueOf(4613732));
		knownSolutions.put(3, Number.valueOf(6857));
		knownSolutions.put(4, Number.valueOf(906609));
		knownSolutions.put(5, Number.valueOf(232792560));
		knownSolutions.put(6, Number.valueOf(25164150));
		knownSolutions.put(7, Number.valueOf(104743));
		knownSolutions.put(8, Number.valueOf(40824));
		knownSolutions.put(9, Number.valueOf(31875000));
		knownSolutions.put(10, Number.valueOf(142913828922L));
		knownSolutions.put(11, Number.valueOf(70600674));
		knownSolutions.put(12, Number.valueOf(76576500));
		knownSolutions.put(13, Number.valueOf(5537376230L));
		knownSolutions.put(14, Number.valueOf(837799));
		knownSolutions.put(15, Number.valueOf(137846528820L));
		knownSolutions.put(16, Number.valueOf(1366));
		knownSolutions.put(17, Number.valueOf(21124));
		knownSolutions.put(18, Number.valueOf(1074));
		knownSolutions.put(19, Number.valueOf(171));
		knownSolutions.put(20, Number.valueOf(648));
		knownSolutions.put(21, Number.valueOf(31626l));
		knownSolutions.put(22, Number.valueOf(871198282l));
		knownSolutions.put(23, Number.valueOf(4179871));
		knownSolutions.put(24, Number.valueOf(2783915460l));
		knownSolutions.put(25, Number.valueOf(4782));
		knownSolutions.put(26, Number.valueOf(983));
		knownSolutions.put(27, -59231);
		knownSolutions.put(28, Number.valueOf(669171001));
		knownSolutions.put(29, Number.valueOf(9183));
		knownSolutions.put(30, Number.valueOf(443839));
		knownSolutions.put(31, Number.valueOf(73682));
		knownSolutions.put(32, Number.valueOf(45228));
		knownSolutions.put(33, Number.valueOf(100));
		knownSolutions.put(34, Number.valueOf(40730));
		knownSolutions.put(35, Number.valueOf(55));
		knownSolutions.put(36, Number.valueOf(872187));
		knownSolutions.put(37, Number.valueOf(748317));
		knownSolutions.put(38, Number.valueOf(932718654));
		knownSolutions.put(39, Number.valueOf(840));
		knownSolutions.put(40, Number.valueOf(210));
	}
	
	private static final <T> long execute(Problem<T> problem, java.lang.Number knownSolution) {
		final long start = System.nanoTime();
		T result = problem.solve();
		final long time = System.nanoTime() - start;

		if (result == null) {
			System.out.printf(
				"           Result not found for %-20s Calculated in %5.2f seconds%n",
				problem.getClass().getSimpleName(), time / 1e9);
			return -1;
		} else {
			String checked = knownSolution == null	? "Unchecked"
													: (knownSolution.equals(result)) ? "Correct"
																					: "Incorrect";
			System.out.printf("%9s result for %s: %-18s Calculated in %5.2f seconds%n", checked,
				problem.getClass().getSimpleName(), result.toString(), time / 1e9);
			return time;
		}
	}

	@SuppressWarnings("unchecked")
	private static final long execute(int nr) {
		Problem<?> problem = null;
		int level = ((nr - 1) / 50) + 1;
		try {
			Class<?> clazz = Class.forName(String.format("euler.level%d.Problem%03d", level, nr));
			problem = (Problem<? extends java.lang.Number>) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(String.format(
				"The given problem number (%d) could not be found", nr));
		} catch (InstantiationException e) {
			throw new IllegalArgumentException(String.format(
				"The given problem number (%d) could not be instantiated", nr));
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(String.format(
				"The given problem number (%d) could not be accessed", nr));
		}

		return execute(problem, knownSolutions.get(nr));
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			final int MAX = 252;

			System.out.println("Executing all problems...");
			int found = 0, executed = 0;
			long totalTime = 0;
			for (int i = 1; i < MAX; i++) {
				try {
					long time = Problem.execute(i);
					if (time > 0) {
						found++;
						totalTime += time;
					}
					executed++;
				} catch (IllegalArgumentException ex) {
					// Ignore this
				}
			}
			System.out.printf("Problems solved: %d out of %d\tTotal duration: %5.2f seconds%n",
				found, executed, totalTime / 1e9);
		} else {
			int nr = 1;
			try {
				nr = Integer.parseInt(args[0]);
			} catch (NumberFormatException ex) {
				System.out.println("Please give the number of the problem that you want to solve!");
				return;
			}

			Problem.execute(nr);
		}
	}

	public abstract T solve();
}
