package euler.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import euler.Problem;
import euler.sequence.HeptagonalNumbers;
import euler.sequence.HexagonalNumbers;
import euler.sequence.OctagonelNumbers;
import euler.sequence.PentagonalNumbers;
import euler.sequence.SimpleSequence;
import euler.sequence.SquareNumbers;
import euler.sequence.TriangleNumbers;

public class Problem061 extends Problem<Long> {
	@Override
	public Long solve() {
		Map<Byte, Map<Byte, List<Integer>>> mapping = new HashMap<Byte, Map<Byte, List<Integer>>>();
		for (byte type = 3; type <= 8; type++) {
			mapping.put(type, new HashMap<Byte, List<Integer>>());
		}

		SimpleSequence[] seqs = {
		                         new TriangleNumbers(), new SquareNumbers(), new PentagonalNumbers(),
		                         new HexagonalNumbers(), new HeptagonalNumbers(), new OctagonelNumbers()
		};
		for (int i = 0; i < seqs.length; i++) {
			SimpleSequence seq = seqs[i];
			seq.head(1000);
			for (int value = (int) seq.next(); value < 10000; value = (int) seq.next()) {
				byte start = (byte) (value / 100);

				Map<Byte, List<Integer>> map = mapping.get(Byte.valueOf((byte) (i + 3)));
				if (map.containsKey(start)) {
					map.get(start).add(value);
				} else {
					List<Integer> list = new ArrayList<Integer>(3);
					map.put(start, list);
					list.add(value);
				}
			}
		}

		return 0l;
	}
}
