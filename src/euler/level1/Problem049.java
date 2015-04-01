package euler.level1;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import euler.NumberProblem;
import euler.numberic.Number;
import euler.sequence.LongSequence;
import euler.sequence.Primes;

public class Problem049 extends NumberProblem {
    @Override
    public Number solve() {
        final LongSequence primes = new Primes().dropWhile(x -> x < 1000);

        final SortedMap<Number, SortedSet<Number>> map = new TreeMap<Number, SortedSet<Number>>();

        for (long prime = primes.next(); prime < 10000; prime = primes.next()) {
            final Number nr = Number.valueOf(prime);
            final Number key = nr.sort();
            if (map.containsKey(key)) {
                map.get(key).add(nr);
            } else {
                final SortedSet<Number> nrs = new TreeSet<Number>();
                nrs.add(nr);
                map.put(key, nrs);
            }
        }

        for (final SortedSet<Number> nrs : map.values()) {
            if (nrs.size() >= 4) {
                final Iterator<Number> it2 = nrs.iterator();
                it2.next();
                final Number first = it2.next(), second = it2.next(), third = it2.next();
                if (second.subtract(first).equals(third.subtract(second))) {
                    return first.concat(second).concat(third);
                }
            }
        }

        return null;
    }
}
