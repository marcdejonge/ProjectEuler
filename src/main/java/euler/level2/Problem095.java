package euler.level2;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import euler.IntegerProblem;
import euler.sequence.AmicableChain;
import euler.sequence.AmicableChain.AmicableChainBuilder;

public class Problem095 extends IntegerProblem {
    private static final int MAX = 1000000;

    @Override
    public long solve() {
        AmicableChainBuilder acb = new AmicableChainBuilder(MAX);
        SortedSet<AmicableChain> chains = new TreeSet<AmicableChain>(new Comparator<AmicableChain>() {
            @Override
            public int compare(AmicableChain o1, AmicableChain o2) {
                if (o2.length() == o1.length()) {
                    return o1.getMinimum() - o2.getMinimum();
                } else {
                    return o2.length() - o1.length();
                }
            }
        });

        for (int nr = 1; nr < MAX; nr++) {
            AmicableChain amicableChain = acb.create(nr);
            if (amicableChain != null) {
                chains.add(amicableChain);
            }
        }

        // System.out.println(chains);
        return chains.first().getMinimum();
    }
}
