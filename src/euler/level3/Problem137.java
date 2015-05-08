package euler.level3;

import euler.IntegerProblem;
import euler.sequence.GoldenRatioSequence;

public class Problem137 extends IntegerProblem {

    // S = ∑(F_i x^i), F_i=F_(i-1) + F_(i-2), F_1=1, F_2=1
    // S = x + x^2 + ∑_(i=3)^∞ (F_i x^i)
    // S = x + x^2 + ∑_(i=3)^∞ (x^i F_(i-1) + x^i F_(i-2))
    // S = x + x^2 + x ∑_(i=2)^∞ (x^i F_i) + x^2 ∑_(i=1)^∞ (x^i F_i)
    // S = x + x^2 + x(S - x) + x^2(S) = (S)x^2 + (S + 1)x
    // 0 = Sx^2 + (S+1)x - S
    // S = x / (1 - x^2 - x)
    // x = (-(S+1) ± √((S+1)^2 + 4(S^2))) / (2S)
    // x = (√(5S^2 + 2S + 1) - S - 1) / (2S)
    // x is rational iff 5S^2 + 2S + 1 is a perfect square
    // 5S^2 + 2S + 1 = y^2
    // y^2 - 5S^2 - 2S = 1 --> y = (+-(x^2 - 1))/(x^2 + x - 1)
    // x = (y - S - 1) / 2S
    // Solutions: (S,y) = (0,1)?, (2,5), (15,34), (104,233)
    // Notice, for each S = F(2n) * F(2n+1), for n >= 1 (but why????)
    // For S, this is the series: https://oeis.org/A081018

    // Also, it seems that x tends towards the golden ratio
    // x = a/b --> S = -(ab) / (a^2 + ab - b^2)
    // We can iterate over all solutions of x
    @Override
    public long solve() throws Exception {
        return new GoldenRatioSequence((Long a, Long b) -> -(a * b) / (a * a + a * b - b * b), 1, 2).get(15);
    }
}
