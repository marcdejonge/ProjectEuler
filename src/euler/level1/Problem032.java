package euler.level1;

import java.util.HashSet;
import java.util.Set;

import euler.IntegerProblem;
import euler.sequence.LineSequence;

public class Problem032 extends IntegerProblem {

    @Override
    public long solve() {
        final LineSequence seq = new LineSequence(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        final byte[] line = seq.current();
        final Set<Integer> numbers = new HashSet<Integer>();
        while (seq.next()) {
            final int sum = line[5] * 1000 + line[6] * 100 + line[7] * 10 + line[8];
            if ((line[0] * 10 + line[1]) * (line[2] * 100 + line[3] * 10 + line[4]) == sum) {
                numbers.add(sum);
            }
            if (line[0] * (line[1] * 1000 + line[2] * 100 + line[3] * 10 + line[4]) == sum) {
                numbers.add(sum);
            }
        }

        int total = 0;
        for (final int x : numbers) {
            total += x;
        }
        return total;
    }
}
