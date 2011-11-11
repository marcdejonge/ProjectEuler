package euler.level1;

import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import euler.Problem;
import euler.numberic.Number;
import euler.sequence.Primes;
import euler.sequence.AbstractSequence.Test;

public class Problem049 extends Problem<Number> {

	@Override
	public Number solve() {
		Primes primes = new Primes();
		primes.dropWhile(new Test() {
			@Override
			public boolean test(long value) {
				return value < 1000;
			}
		});

		SortedMap<Number, SortedSet<Number>> map = new TreeMap<Number, SortedSet<Number>>();

		for (long prime = primes.next(); prime < 10000; prime = primes.next()) {
			Number nr = Number.valueOf(prime);
			Number key = nr.sort();
			if (map.containsKey(key)) {
				map.get(key).add(nr);
			} else {
				SortedSet<Number> nrs = new TreeSet<Number>();
				nrs.add(nr);
				map.put(key, nrs);
			}
		}

		for (SortedSet<Number> nrs : map.values()) {
			if (nrs.size() >= 4) {
				Iterator<Number> it2 = nrs.iterator();
				it2.next();
				Number first = it2.next(), second = it2.next(), third = it2.next();
				if(second.subtract(first).equals(third.subtract(second))) {
					return first.concat(second).concat(third);
				}
			}
		}

		return null;
	}
}
