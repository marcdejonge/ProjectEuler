package euler.level1;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import euler.FileUtil;
import euler.Problem;

public class Problem022 extends Problem<Long> {

    @Override
    public Long solve() {
        try {
            final List<String> names = FileUtil.readNames(new File("Problem22.txt"));
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
