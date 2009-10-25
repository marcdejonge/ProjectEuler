package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import euler.Problem;
import euler.path.AStar;
import euler.path.ManhattanHeuristic;
import euler.path.Node;
import euler.path.ShortestPathSolver;

public class Problem081 extends Problem<Integer> {
	@Override
	public Integer solve() {
		Node start = null, goal = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Problem081.txt"));
			List<Node> last = null;
			String line = null;
			int y= 0;
			while((line = reader.readLine()) != null) {
				List<Node> current = new ArrayList<Node>();
				String[] nrs = line.split(",");
				for(int x = 0; x < nrs.length; x++) {
					Node node = new Node(Integer.parseInt(nrs[x]), x, y);
					current.add(node);
					if(last != null) {
						last.get(x).addNeighbor(node);
					}
					if(x > 0) {
						current.get(x-1).addNeighbor(node);
					}
				}
				if(start == null) {
					start = current.get(0);
				}
				goal = current.get(current.size() - 1);
				y++;
				last = current;
			}
		} catch(IOException ex) {
			ex.printStackTrace();
			return null;
		}
		
		ShortestPathSolver solver = new AStar(start, goal, new ManhattanHeuristic());
		return Node.sumValues(solver.findShortestPath());
	}
}
