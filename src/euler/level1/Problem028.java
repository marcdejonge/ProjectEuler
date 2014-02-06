package euler.level1;

import euler.IntegerProblem;

public class Problem028 extends IntegerProblem {

    @Override
    public long solve() {
        int total = 1;
        int cnt = 1;
        for (int i = 1; i <= 500; i++) {
            cnt += i * 2;
            total += cnt;
            cnt += i * 2;
            total += cnt;
            cnt += i * 2;
            total += cnt;
            cnt += i * 2;
            total += cnt;
        }
        return total;
    }

}
