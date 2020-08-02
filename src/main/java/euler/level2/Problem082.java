package euler.level2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import euler.IntegerProblem;
import euler.input.FileUtils;
import euler.path.AStar;
import euler.path.AStar.Guide;
import euler.path.Node;
import euler.path.NodeReader;

public class Problem082 extends IntegerProblem {

    @Override
    public long solve() throws InterruptedException, ExecutionException, IOException {
        final List<List<Node>> nodes = new NodeReader(FileUtils.readInput(this), NodeReader.CONNECT_RIGHT | NodeReader.CONNECT_DOWN
                                                                                 | NodeReader.CONNECT_UP).readNodes();

        final int maxX = nodes.get(0).size() - 1;
        final Guide guide = new Guide() {
            @Override
            public long estimateDistanceToGoal(Node node) {
                return Math.abs(node.getX() - maxX) * 100;
            }

            @Override
            public boolean isGoal(Node node) {
                return node.getX() == maxX;
            }
        };

        ForkJoinPool pool = new ForkJoinPool();
        List<Future<Integer>> sums = new ArrayList<Future<Integer>>();
        for (int y = 0; y < nodes.size(); y++) {
            final int line = y;
            sums.add(pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return Node.sumValues(new AStar(nodes.get(line).get(0), guide).findShortestPath());
                }
            }));
        }

        int minSum = Integer.MAX_VALUE;
        for (Future<Integer> sum : sums) {
            int s = sum.get();
            if (s < minSum) {
                minSum = s;
            }
        }

        return minSum;
    }
}
