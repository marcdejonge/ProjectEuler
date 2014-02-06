package euler.level1;

import euler.IntegerProblem;

public class Problem006 extends IntegerProblem {

    @Override
    public long solve() {
        int sumOfSquares = 0, squareOfSums = 0;
        for (int i = 1; i <= 100; i++) {
            sumOfSquares += i * i;
            squareOfSums += i;
        }
        squareOfSums *= squareOfSums;

        return squareOfSums - sumOfSquares;
    }

}
