package euler.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import euler.NumberProblem;
import euler.numberic.Number;
import euler.sequence.LongSequence;
import euler.sequence.SimpleSequence;

public class Problem062 extends NumberProblem {
    @Override
    public Number solve() {
        final LongSequence cubes = new SimpleSequence() {
            @Override
            public long current() {
                final long n = getN();
                return n * n * n;
            }
        };

        final HashMap<String, List<Number>> mapping = new HashMap<String, List<Number>>();
        for (long cube = cubes.next();; cube = cubes.next()) {
            final Number nr = Number.valueOf(cube);

            final String key = nr.sort().toString();
            if (mapping.containsKey(key)) {
                final List<Number> list = mapping.get(key);
                list.add(nr);
                if (list.size() == 5) {
                    return list.get(0);
                }
            } else {
                final ArrayList<Number> list = new ArrayList<Number>(5);
                list.add(nr);
                mapping.put(key, list);
            }
        }
    }
}
