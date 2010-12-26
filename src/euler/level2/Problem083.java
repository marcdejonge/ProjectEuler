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

public class Problem083 extends Problem<Long> {
	@Override
	public Long solve() {
		List<List<Node>> nodes = new ArrayList<List<Node>>(80);
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Problem083.txt"));
			String line = null;
			int y = 0;
			while ((line = reader.readLine()) != null) {
				// Parse the line!
				String[] numbers = line.split(",");

				List<Node> currentNodes = new ArrayList<Node>(80);

				for (int x = 0; x < numbers.length; x++) {
					int number = Integer.parseInt(numbers[x]);
					currentNodes.add(new Node(number, x, y));
				}
				nodes.add(currentNodes);

				y++;
			}
			// Link all the nodes, first horizontal
			for (List<Node> list : nodes) {
				Node curr = null;
				for (Node next : list) {
					if (curr != null) {
						curr.addNeighbor(next);
						next.addNeighbor(curr);
					}
					curr = next;
				}
			}
			// Now vertical
			List<Node> curr = null;
			for (List<Node> next : nodes) {
				if (curr != null) {
					for (int x = 0; x < Math.min(next.size(), curr.size()); x++) {
						curr.get(x).addNeighbor(next.get(x));
						next.get(x).addNeighbor(curr.get(x));
					}
				}
				curr = next;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}

		Node start = nodes.get(0).get(0);
		List<Node> goalList = nodes.get(nodes.size() - 1);
		Node goal = goalList.get(goalList.size() - 1);

		List<Node> shortestPath = new AStar(start, goal, new ManhattanHeuristic()).findShortestPath();

		long totalLength = 0;
		for (Node n : shortestPath) {
			totalLength += n.getValue();
		}

		return totalLength;
	}
}
