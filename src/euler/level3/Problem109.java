package euler.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import euler.Problem;

public class Problem109 extends Problem<Integer> {
    static class Throw implements Comparable<Throw> {
        private final int nr, multiplier;

        public Throw(int nr, int multiplier) {
            this.nr = nr;
            this.multiplier = multiplier;
        }

        @Override
        public int compareTo(Throw other) {
            if (other.getTotal() != getTotal()) {
                return other.getTotal() - getTotal();
            } else {
                return other.nr - nr;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null || getClass() != obj.getClass()) {
                return false;
            } else {
                Throw other = (Throw) obj;
                return nr == other.nr && multiplier == other.multiplier;
            }
        }

        public int getMultiplier() {
            return multiplier;
        }

        public int getNr() {
            return nr;
        }

        public int getTotal() {
            return multiplier * nr;
        }

        @Override
        public int hashCode() {
            return 31 * multiplier + nr;
        }

        @Override
        public String toString() {
            switch (multiplier) {
            case 0:
                return "MISS";
            case 1:
                return "S" + nr;
            case 2:
                return "D" + nr;
            case 3:
                return "T" + nr;
            default:
                throw new IllegalStateException();
            }
        }
    }

    private static final int MAX = 100;

    @Override
    public Integer solve() {
        List<Throw> possibleThrows = new ArrayList<>();
        for (int nr = 1; nr <= 20; nr++) {
            possibleThrows.add(new Throw(nr, 1));
            possibleThrows.add(new Throw(nr, 2));
            possibleThrows.add(new Throw(nr, 3));
        }
        possibleThrows.add(new Throw(25, 1));
        possibleThrows.add(new Throw(25, 2));
        possibleThrows.add(new Throw(0, 0));
        Collections.sort(possibleThrows);

        System.out.println(possibleThrows);

        int count = 0;
        for (int ix1 = 0; ix1 < possibleThrows.size(); ix1++) {
            Throw t1 = possibleThrows.get(ix1);
            for (int ix2 = possibleThrows.size() - 1; ix2 >= ix1; ix2--) {
                Throw t2 = possibleThrows.get(ix2);
                for (int ix3 = possibleThrows.size() - 1; ix3 >= 0; ix3--) {
                    Throw t3 = possibleThrows.get(ix3);
                    if (t1.getTotal() + t2.getTotal() + t3.getTotal() >= MAX) {
                        break;
                    } else if (t3.getMultiplier() == 2) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
