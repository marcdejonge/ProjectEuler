package euler.level2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import euler.Problem;
import euler.numberic.BitSet;
import euler.sequence.Primes;

public class Problem060 extends Problem<Integer> {
    private boolean check(final int v1, final int v2) {
        long mult1 = 10;
        while (v2 > mult1) {
            mult1 *= 10;
        }

        long mult2 = 10;
        while (v1 > mult2) {
            mult2 *= 10;
        }

        final long p1 = v1 * mult1 + v2;
        final long p2 = v1 + v2 * mult2;
        return Primes.isPrime(p1) && Primes.isPrime(p2);
    }

    @Override
    public Integer solve() {
        final int MAX = 16384;

        final SortedMap<Integer, BitSet> map = new TreeMap<Integer, BitSet>();
        final Set<BitSet> sets = new HashSet<BitSet>();
        final List<BitSet> setsToAdd = new ArrayList<BitSet>();
        final Primes primes = new Primes();
        int minSum = Integer.MAX_VALUE;

        for (int prime = (int) primes.next(); prime < minSum; prime = (int) primes.next()) {
            final BitSet bs = new BitSet(MAX);

            // First find all 2nd degree combinations, between 2 primes
            for (final Entry<Integer, BitSet> entry : map.entrySet()) {
                final int prevPrime = entry.getKey();
                if (check(prime, prevPrime)) {
                    bs.set(prevPrime);
                    entry.getValue().set(prime);
                }
            }

            // Now try to find any bigger combination that 2nd degree, that also are contained in
            // this one
            setsToAdd.clear();
            for (final BitSet set : sets) {
                if (set.intersects(bs)) {
                    final BitSet biggerSet = set.and(bs);
                    if (set.equals(biggerSet)) {
                        biggerSet.set(prime);
                        setsToAdd.add(biggerSet);
                        if (biggerSet.cardinality() >= 5) {
                            final int sum = sum(biggerSet);
                            if (sum < minSum) {
                                minSum = sum;
                            }

                            // Found a possible combo!
                            return sum;
                        }
                    }
                }
            }
            sets.addAll(setsToAdd);

            // Now find all 3rd degree combinations from the new 2nd degree combinations
            for (int prevPrime = bs.nextSetBit(0); prevPrime >= 0; prevPrime = bs.nextSetBit(prevPrime + 1)) {
                final BitSet prevSet = map.get(prevPrime);
                if (prevSet.intersects(bs)) {
                    final BitSet newSet = prevSet.and(bs);
                    for (int x = newSet.nextSetBit(0); x >= 0; x = newSet.nextSetBit(x + 1)) {
                        sets.add(new BitSet(MAX).set(x).set(prevPrime).set(prime));
                    }
                }
            }

            // Now finally add the prime with its new set of matchings
            map.put(prime, bs);
        }

        return minSum;
    }

    private int sum(BitSet bs) {
        int total = 0;
        for (int x = bs.nextSetBit(0); x >= 0; x = bs.nextSetBit(x + 1)) {
            total += x;
        }
        return total;
    }
}
