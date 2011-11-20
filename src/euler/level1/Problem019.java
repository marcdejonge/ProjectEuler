package euler.level1;

import java.util.Calendar;

import euler.Problem;

public class Problem019 extends Problem<Integer> {

    @Override
    public Integer solve() {
        final Calendar calendar = Calendar.getInstance();
        int sundays = 0;
        for (int year = 1901; year < 2001; year++) {
            for (int month = Calendar.JANUARY; month <= Calendar.DECEMBER; month++) {
                calendar.clear();
                calendar.set(year, month, 1);
                if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    sundays++;
                }
            }
        }
        return sundays;
    }

}
