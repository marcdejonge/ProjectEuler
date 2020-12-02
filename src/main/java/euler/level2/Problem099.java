package euler.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import euler.IntegerProblem;
import euler.JavaPair;
import euler.input.FileUtils;

public class Problem099 extends IntegerProblem {
    private List<JavaPair<Integer, Integer>> readNumberPairs() throws IOException {
        final List<JavaPair<Integer, Integer>> result = new ArrayList<JavaPair<Integer, Integer>>();

        try (final BufferedReader reader = FileUtils.readInput(this)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                final String[] nrs = line.split(",");
                if (nrs.length != 2) {
                    throw new IOException("More than two parts on one line!");
                }
                try {
                    result.add(JavaPair.from(Integer.parseInt(nrs[0]), Integer.parseInt(nrs[1])));
                } catch (final NumberFormatException e) {
                    throw new IOException("Not a number that was found!");
                }
            }
        }

        return result;
    }

    @Override
    public long solve() throws IOException {
        final List<JavaPair<Integer, Integer>> pairs = readNumberPairs();
        BigDecimal max = BigDecimal.ZERO;
        final MathContext mc = new MathContext(8);
        int maxLine = 0;
        int line = 1;
        for (final JavaPair<Integer, Integer> pair : pairs) {
            final BigDecimal x = BigDecimal.valueOf(pair.getFirst()).pow(pair.getSecond(), mc);
            if (x.compareTo(max) > 0) {
                maxLine = line;
                max = x;
            }
            line++;
        }
        return maxLine;
    }
}
