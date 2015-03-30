package euler.level3;

import euler.IntegerProblem;
import euler.sequence.Fibionacci;

public class Problem137 extends IntegerProblem {
    /*
     * From: http://en.wikipedia.org/wiki/Fibonacci_number (Power Series)
     * 
     * s = x / (1 - x - x^2) (1 - x - x^2) s = x
     * 
     * s - sx - sx^2 = x
     * 
     * s - (s + 1)x - sx^2 = 0 --> -sx^2 - (s-1)x + s = 0
     * 
     * x = (s + 1 +- sqrt(5s^2 + 2s + 1)) / (-2s)
     * 
     * x is rational iff 5s^2 + 2s + 1 is a square of a integer
     * 
     * --> 5s^2 + 2s + 1 = y^2
     * 
     * This is the series: https://oeis.org/A081018
     */
    @Override
    public long solve() throws Exception {
        int ix = 15;
        Fibionacci fibionacci = new Fibionacci();
        return fibionacci.get(2 * ix) * fibionacci.next();
    }
}
