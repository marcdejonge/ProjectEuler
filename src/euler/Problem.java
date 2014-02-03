package euler;

import java.util.HashMap;
import java.util.Map;

public abstract class Problem<T> {
    private static final Map<Integer, java.lang.Number> knownSolutions = new HashMap<Integer, java.lang.Number>();
    static {
        knownSolutions.put(1, 233168);
        knownSolutions.put(2, 4613732);
        knownSolutions.put(3, 6857);
        knownSolutions.put(4, 906609);
        knownSolutions.put(5, 232792560);
        knownSolutions.put(6, 25164150);
        knownSolutions.put(7, 104743);
        knownSolutions.put(8, 40824);
        knownSolutions.put(9, 31875000);
        knownSolutions.put(10, 142913828922l);
        knownSolutions.put(11, 70600674);
        knownSolutions.put(12, 76576500);
        knownSolutions.put(13, 5537376230l);
        knownSolutions.put(14, 837799);
        knownSolutions.put(15, 137846528820l);
        knownSolutions.put(16, 1366);
        knownSolutions.put(17, 21124);
        knownSolutions.put(18, 1074);
        knownSolutions.put(19, 171);
        knownSolutions.put(20, 648);
        knownSolutions.put(21, 31626);
        knownSolutions.put(22, 871198282);
        knownSolutions.put(23, 4179871);
        knownSolutions.put(24, 2783915460l);
        knownSolutions.put(25, 4782);
        knownSolutions.put(26, 983);
        knownSolutions.put(27, -59231);
        knownSolutions.put(28, 669171001);
        knownSolutions.put(29, 9183);
        knownSolutions.put(30, 443839);
        knownSolutions.put(31, 73682);
        knownSolutions.put(32, 45228);
        knownSolutions.put(33, 100);
        knownSolutions.put(34, 40730);
        knownSolutions.put(35, 55);
        knownSolutions.put(36, 872187);
        knownSolutions.put(37, 748317);
        knownSolutions.put(38, 932718654);
        knownSolutions.put(39, 840);
        knownSolutions.put(40, 210);
        knownSolutions.put(41, 7652413);
        knownSolutions.put(42, 162);
        knownSolutions.put(43, 16695334890l);
        knownSolutions.put(44, 5482660);
        knownSolutions.put(45, 1533776805);
        knownSolutions.put(46, 5777);
        knownSolutions.put(47, 134043);
        knownSolutions.put(48, 9110846700l);
        knownSolutions.put(49, 296962999629l);
        knownSolutions.put(50, 997651);
        knownSolutions.put(51, 121313);
        knownSolutions.put(52, 142857);
        knownSolutions.put(53, 4075);
        knownSolutions.put(54, 376);
        knownSolutions.put(55, 249);
        knownSolutions.put(56, 972);
        knownSolutions.put(57, 153);
        knownSolutions.put(58, 26241);
        knownSolutions.put(59, 107359);
        knownSolutions.put(60, 26033);
        knownSolutions.put(61, 28684);
        knownSolutions.put(62, 127035954683l);
        knownSolutions.put(63, 49);
        knownSolutions.put(64, 1322);
        knownSolutions.put(65, 272);
        knownSolutions.put(66, 661);
        knownSolutions.put(67, 7273);
        knownSolutions.put(68, 6531031914842725l);
        knownSolutions.put(69, 510510);
        knownSolutions.put(70, 8319823);
        knownSolutions.put(71, 428570);
        knownSolutions.put(72, 303963552391l);
        knownSolutions.put(73, 7295372);
        knownSolutions.put(74, 402);
        knownSolutions.put(75, 161667);
        knownSolutions.put(76, 190569291);
        knownSolutions.put(77, 71);
        knownSolutions.put(78, 55374);
        knownSolutions.put(79, 73162890);
        knownSolutions.put(80, 40886);
        knownSolutions.put(81, 427337);
        knownSolutions.put(82, 260324);
        knownSolutions.put(83, 425185);
        knownSolutions.put(84, 101524);
        knownSolutions.put(85, 2772);
        knownSolutions.put(86, 1818);
        knownSolutions.put(87, 1097343);
        knownSolutions.put(88, 7587457);
        knownSolutions.put(89, 743);
        knownSolutions.put(90, 1217);
        knownSolutions.put(91, 14234);
        knownSolutions.put(92, 8581146);
        knownSolutions.put(93, 1258);
        knownSolutions.put(94, 518408346);
        knownSolutions.put(95, 14316);
        knownSolutions.put(96, 24702);
        knownSolutions.put(97, 8739992577l);
        knownSolutions.put(98, 18769);
        knownSolutions.put(99, 709);
        knownSolutions.put(100, 756872327473l);
        knownSolutions.put(101, 37076114526l);
        knownSolutions.put(102, 228);
        knownSolutions.put(103, 20313839404245l);
        knownSolutions.put(104, 329468);
        knownSolutions.put(105, 73702);
        knownSolutions.put(106, 21384);
        knownSolutions.put(107, 259679);
        knownSolutions.put(108, 180180);
        knownSolutions.put(109, 38182);
        knownSolutions.put(110, 9350130049860600l);
        knownSolutions.put(111, 612407567715l);
        knownSolutions.put(112, 1587000);
        knownSolutions.put(113, 51161058134250l);
        knownSolutions.put(114, 16475640049l);
        knownSolutions.put(115, 168);
        knownSolutions.put(116, 20492570929l);
        knownSolutions.put(117, 100808458960497l);
        knownSolutions.put(118, 44680);
        knownSolutions.put(119, 248155780267521l);
        knownSolutions.put(120, 333082500);
        knownSolutions.put(121, 2269);
        knownSolutions.put(122, 1582);
        knownSolutions.put(123, 21035);
        knownSolutions.put(124, 21417);
        knownSolutions.put(125, 2906969179l);
        knownSolutions.put(126, 18522);
        knownSolutions.put(127, 18407904);
        knownSolutions.put(128, 14516824220L);
        knownSolutions.put(129, 1000023);
        knownSolutions.put(130, 149253);
        knownSolutions.put(131, 173);
        knownSolutions.put(142, 1006193);
        knownSolutions.put(145, 608720);
        knownSolutions.put(160, 16576);
        knownSolutions.put(162, 0x3D58725572C62302L);
        knownSolutions.put(164, 378158756814587l);
        knownSolutions.put(165, 2868868);
        knownSolutions.put(179, 986262);
        knownSolutions.put(187, 17427258);
        knownSolutions.put(204, 2944730);
        knownSolutions.put(205, 0.5731441);
        knownSolutions.put(206, 1389019170);
        knownSolutions.put(357, 1739023853137l);
    }

    private static final long execute(int nr) {
        Problem<?> problem = null;
        final int level = (nr - 1) / 50 + 1;
        try {
            final Class<?> clazz = Class.forName(String.format("euler.level%d.Problem%03d", level, nr));
            problem = (Problem<?>) clazz.newInstance();
        } catch (final ClassNotFoundException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be found", nr));
        } catch (final InstantiationException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be instantiated", nr));
        } catch (final IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("The given problem number (%d) could not be accessed", nr));
        }

        return execute(problem, knownSolutions.get(nr));
    }

    private static final <T> long execute(Problem<T> problem, java.lang.Number knownSolution) {
        final long start = System.nanoTime();
        final T result = problem.solve();
        final long time = System.nanoTime() - start;

        if (result == null) {
            System.out.printf("          Result not found for %-20s Calculated in %5.2f seconds%n",
                              problem.getClass().getSimpleName(),
                              time / 1e9);
            return -1;
        } else {
            final String checked = knownSolution == null ? "Unchecked" : knownSolution.equals(result) ? "Correct" : "Incorrect";
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
                nr = Integer.parseInt(args[0]);
            } catch (final NumberFormatException ex) {
                System.out.println("Please give the number of the problem that you want to solve!");
                return;
            }

            Problem.execute(nr);
        }
    }

    public abstract T solve();
}
