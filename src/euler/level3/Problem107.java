package euler.level3;

import java.io.IOException;

import euler.Problem;
import euler.graph.PrimsAlgorithm;
import euler.graph.WeightedGraph;
import euler.graph.WeightedGraph.Edge;
import euler.graph.WeightedGraph.Vertex;
import euler.input.FileUtils;

public class Problem107 extends Problem<Integer> {

    private int getTotalWeight(WeightedGraph g) {
        int total = 0;
        for (Vertex v : g) {
            for (Edge e : v) {
                total += e.getWeight();
            }
        }
        return total;
    }

    @Override
    public Integer solve() {
        WeightedGraph input;
        try {
            input = FileUtils.readWeightedGraph(this);
            WeightedGraph output = new PrimsAlgorithm(input).run();
            return getTotalWeight(input) / 2 - getTotalWeight(output);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
