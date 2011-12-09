package euler.level2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import euler.Problem;
import euler.path.AStar;
import euler.path.AStar.Guide;
import euler.path.Node;
import euler.path.NodeReader;

public class Problem082 extends Problem<Integer> {

    @Override
    public Integer solve() {
        try {
            final List<List<Node>> nodes = new NodeReader(new FileReader("Problem082.txt"),
                                                          NodeReader.CONNECT_RIGHT | NodeReader.CONNECT_DOWN | NodeReader.CONNECT_UP).readNodes();

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

            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            List<Future<Integer>> sums = new ArrayList<Future<Integer>>();
            for (int y = 0; y < nodes.size(); y++) {
                final int line = y;
                sums.add(executorService.submit(new Callable<Integer>() {
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
