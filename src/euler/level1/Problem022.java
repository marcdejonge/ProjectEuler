package euler.level1;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import euler.Problem;
import euler.input.FileUtils;

public class Problem022 extends Problem<Long> {

    @Override
    public Long solve() {
        try {
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
        } catch (final IOException e) {
            e.printStackTrace();
            return -1l;
        }
    }

}
