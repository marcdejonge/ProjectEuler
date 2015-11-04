package euler.level3;

import java.util.function.ToLongBiFunction;

import euler.IntegerProblem;
import euler.sequence.GoldenRatioSequence;

public class Problem140 extends IntegerProblem {

    // S = ∑(G_i x^i), G_i=G_(i-1)+G_(i-2), G_1=1, G_2=4
    // S = x + 4x^2 + ∑_(i=3)^∞ (G_i x^i)
    // S = x + 4x^2 + ∑_(i=3)^∞ (x^i G_(i-1) + x^i G_(i-2))
    // S = x + 4x^2 + x ∑_(i=2)^∞ (x^i G_i) + x^2 ∑_(i=1)^∞ (x^i G_i)
    // S = x + 4x^2 + x(S - x) + x^2(S) = 4x^2 + (S + 1)x - x^2 + Sx^2
    // S = (3x^2 + x) / (1 - (x + x^2))
    // S - Sx - Sx^2 = 3x^2 + x
    // (3 + S)x^2 + (S + 1)x - S = 0
    // x = (-(S+1) ± √((S+1)^2 - 4(3+S)(-S))) / (6+2S)
    // x = (√(5S^2 + 14S + 1) - S - 1) / (6 + 2S)
    // x is rational when 5S^2 + 14S + 1 is a perfect square
    // 5S^2 + 14S + 1 = y^2
    // S = 0 --> y = 1
    // S = 2 --> y = 7
    // S = 5 --> y = 14
    // S = 21 --> y = 50

    // S = (3x^2 + x) / (1 - (x + x^2))
    // x = a/b --> S = (3a^2 + ab) / (a^2 + ab - b^2)
    // we can iterate for x_n+1 = 1 / (x_n + 1)
    // This is the same if the first add b to a, then add a to b
    // Primary solutions are 2/5 and 1/2

    @Override
    public long solve() throws Exception {
        ToLongBiFunction<Long, Long> function = (Long a, Long b) -> -(3 * a * a + a * b) / (a * a + a * b - b * b);
        return new GoldenRatioSequence(function, 2, 5).stream().limit(15).sum()
               + new GoldenRatioSequence(function, 1, 2).stream().limit(15).sum();
    }
}
