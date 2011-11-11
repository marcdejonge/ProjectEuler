package euler.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import euler.Problem;
import euler.numberic.Number;
import euler.sequence.AbstractSequence;
import euler.sequence.SimpleSequence;

public class Problem062 extends Problem<Number> {
	@Override
	public Number solve() {
		AbstractSequence cubes = new SimpleSequence() {
			@Override
			public long current() {
				long n = getN();
				return n * n * n;
			}
		};

		HashMap<String, List<Number>> mapping = new HashMap<String, List<Number>>();
		for (long cube = cubes.next();; cube = cubes.next()) {
			Number nr = Number.valueOf(cube);

			String key = nr.sort().toString();
			if (mapping.containsKey(key)) {
				List<Number> list = mapping.get(key);
				list.add(nr);
				if (list.size() == 5) {
					return list.get(0);
				}
			} else {
				ArrayList<Number> list = new ArrayList<Number>(5);
				list.add(nr);
				mapping.put(key, list);
			}
		}
	}
}
