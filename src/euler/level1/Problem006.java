package euler.level1;

import euler.Problem;

public class Problem006 extends Problem<Integer> {

    @Override
    public Integer solve() {
        int sumOfSquares = 0, squareOfSums = 0;
        for (int i = 1; i <= 100; i++) {
            sumOfSquares += i * i;
            squareOfSums += i;
        }
        squareOfSums *= squareOfSums;

        return squareOfSums - sumOfSquares;
    }

}
