package euler.level3;

import java.io.File;
import java.io.IOException;
import java.util.List;

import euler.Problem;
import euler.twoD.Triangle;
import euler.twoD.Vector;

public class Problem102 extends Problem<Integer> {
	@Override
	public Integer solve() {
		List<Triangle> ts;
		try {
			ts = Triangle.read(new File("Problem102.txt"));
		} catch (IOException e) {
			System.err.println("Could not read input file: Problem102.txt");
			return null;
		}
		
		int total = 0;
		Vector orig = new Vector(0, 0);

		for (Triangle t : ts) {
			if (t.containsPoint(orig)) {
				total++;
			}
		}
		return total;
	}
}
