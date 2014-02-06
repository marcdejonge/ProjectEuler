package euler.level1;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import euler.IntegerProblem;
import euler.input.FileUtils;
import euler.sequence.TriangleNumbers;

public class Problem042 extends IntegerProblem {

    @Override
    public long solve() {
        try {
            final List<String> words = FileUtils.readNames(this);

            final int[] triangleNrs = new TriangleNumbers().head(1000);

            int total = 0;
            for (final String word : words) {
                int nr = 0;
                for (int i = 0; i < word.length(); i++) {
                    nr += word.charAt(i) - 'a' + 1;
                }

                if (Arrays.binarySearch(triangleNrs, nr) >= 0) {
                    total++;
                }
            }
            return total;
        } catch (final IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
