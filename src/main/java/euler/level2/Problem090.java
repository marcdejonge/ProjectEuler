package euler.level2;

import java.util.Arrays;
import java.util.List;

import euler.IntegerProblem;
import euler.combination.CombinationGenerator;

public class Problem090 extends IntegerProblem {

    class Dices {
        private final Integer[] dice1;
        private final Integer[] dice2;

        Dices(Integer[] integers, Integer[] integers2) {
            dice1 = integers;
            dice2 = integers2;
        }

        boolean canMake(int nr1, int nr2) {
            return contains1(nr1) && contains2(nr2) || contains1(nr2) && contains2(nr1);
        }

        boolean contains1(int nr) {
            if (nr == 6 || nr == 9) {
                return Arrays.binarySearch(dice1, 6) >= 0 || Arrays.binarySearch(dice1, 9) >= 0;
            }
            return Arrays.binarySearch(dice1, nr) >= 0;
        }

        boolean contains2(int nr) {
            if (nr == 6 || nr == 9) {
                return Arrays.binarySearch(dice2, 6) >= 0 || Arrays.binarySearch(dice2, 9) >= 0;
            }
            return Arrays.binarySearch(dice2, nr) >= 0;
        }

        boolean isValid() {
            return dice1.length <= 6 && dice2.length <= 6
                   && canMake(0, 1)
                   && canMake(0, 4)
                   && canMake(0, 9)
                   && canMake(1, 6)
                   && canMake(2, 5)
                   && canMake(3, 6)
                   && canMake(4, 9)
                   && canMake(6, 4)
                   && canMake(8, 1);
        }

        @Override
        public String toString() {
            return Arrays.toString(dice1) + " & " + Arrays.toString(dice2);
        }
    }

    @Override
    public long solve() {
        int count = 0;
        List<Integer[]> possibleDices = new CombinationGenerator<Integer>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 6).generateAll();
        for (int ix = 0; ix < possibleDices.size(); ix++) {
            for (int jx = ix; jx < possibleDices.size(); jx++) {
                Dices dices = new Dices(possibleDices.get(ix), possibleDices.get(jx));
                if (dices.isValid()) {
                    count++;
                }
            }
        }
        return count;
    }

}
