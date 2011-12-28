package euler.level2;

import euler.Problem;
import euler.combination.CombinationGenerator;
import euler.combination.PermutationGenerator;
import euler.numberic.BitSet;

public class Problem093 extends Problem<Integer> {

    private void calc(BitSet bs, double a) {
        if (Math.round(a) == a) {
            bs.set((int) a);
        }
    }

    private void calc(BitSet bs, double a, double b) {
        calc(bs, a + b);
        calc(bs, a * b);
        calc(bs, a - b);
        if (b != 0) {
            calc(bs, a / b);
        }

        calc(bs, b - a);
        if (a != 0) {
            calc(bs, b / a);
        }
    }

    private void calc(BitSet bs, double a, double b, double c) {
        calc(bs, a + b, c);
        calc(bs, a * b, c);
        calc(bs, a - b, c);
        if (b != 0) {
            calc(bs, a / b, c);
        }
        calc(bs, b - a, c);
        if (a != 0) {
            calc(bs, b / a, c);
        }
    }

    private void calc(BitSet bs, double a, double b, double c, double d) {
        calc(bs, a + b, c, d);
        calc(bs, a * b, c, d);
        calc(bs, a - b, c, d);
        if (b != 0) {
            calc(bs, a / b, c, d);
        }
        calc(bs, b - a, c, d);
        if (a != 0) {
            calc(bs, b / a, c, d);
        }
    }

    @Override
    public Integer solve() {
        int max = 0;
        Integer[] maxArr = null;

        BitSet bs = new BitSet(64);
        for (Integer[] digits : new CombinationGenerator<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 4)) {
            bs.reset();
            bs.set(0);

            for (Integer[] digitPerm : new PermutationGenerator<Integer>(digits)) {
                calc(bs, digitPerm[0], digitPerm[1], digitPerm[2], digitPerm[3]);
            }

            // System.out.println(Arrays.toString(digits) + " => " + bs);

            bs.flip();
            int count = bs.nextSetBit(0) - 1;
            if (count > max) {
                max = count;
                maxArr = digits;
            }
        }
        // System.out.println(max);
        return Integer.valueOf("" + maxArr[0] + maxArr[1] + maxArr[2] + maxArr[3]);
    }
}
