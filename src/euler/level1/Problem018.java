package euler.level1;

import java.io.IOException;
import java.util.Arrays;

import euler.IntegerProblem;
import euler.field.Node;
import euler.field.TriangleNumbers;

public class Problem018 extends IntegerProblem {
    @Override
    public long solve() throws IOException {
        TriangleNumbers tn = new TriangleNumbers(this);
        printf("Input: %n%s%n", tn);

        Node[] route = tn.findHeaviestRoute();
        printf("Heaviest route: %s%n", Arrays.toString(route));

        return Arrays.stream(route).mapToInt(x -> x.getNr()).sum();
    }
}
