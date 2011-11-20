package euler.level1;

import java.io.File;
import java.io.IOException;

import euler.Problem;
import euler.field.Node;
import euler.field.TriangleNumbers;

public class Problem018 extends Problem<Integer> {
    @Override
    public Integer solve() {
        try {
            final TriangleNumbers tn = new TriangleNumbers(new File("Problem18.txt"));
            // System.out.println(tn);
            final Node[] route = tn.findHeaviestRoute();
            // System.out.println(Arrays.toString(route));
            int sum = 0;
            for (final Node r : route) {
                sum += r.getNr();
            }
            return sum;
        } catch (final IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
