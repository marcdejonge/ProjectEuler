package euler.level1;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import euler.IntegerProblem;
import euler.input.FileUtils;

public class Problem022 extends IntegerProblem {

    @Override
    public long solve() throws IOException {
        final List<String> names = FileUtils.readNames(this);
        Collections.sort(names);
        long sum = 0;
        for (int i = 0; i < names.size(); i++) {
            final String name = names.get(i);
            int score = 0;
            for (int j = 0; j < name.length(); j++) {
                score += name.charAt(j) - 'a' + 1;
            }
            sum += score * (i + 1);
        }
        return sum;
    }
}
