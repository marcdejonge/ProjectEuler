package euler.level1;

import euler.Problem;

public class Problem028 extends Problem<Integer> {

    @Override
    public Integer solve() {
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
