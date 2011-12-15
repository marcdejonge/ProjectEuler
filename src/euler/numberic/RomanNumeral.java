package euler.numberic;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {
    private static final Map<Character, Integer> values = new HashMap<Character, Integer>();

    static {
        values.put('M', 1000);
        values.put('D', 500);
        values.put('C', 100);
        values.put('L', 50);
        values.put('X', 10);
        values.put('V', 5);
        values.put('I', 1);
    }

    public static RomanNumeral parse(String value) {
        int nr = 0;
        for (int ix = 0; ix < value.length(); ix++) {
            char c = value.charAt(ix);
            char nextC = ix < value.length() - 1 ? value.charAt(ix + 1) : 0;

            int curr = values.get(c) == null ? 0 : values.get(c);
            int next = values.get(nextC) == null ? 0 : values.get(nextC);

            if (curr > 0 && next > curr) {
                nr += next - curr;
                ix++;
            } else {
                nr += curr;
            }
        }
        return new RomanNumeral(nr);
    }

    private final int nr;

    public RomanNumeral(int nr) {
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int nr = this.nr;
        while (nr > 0) {
            if (nr >= 1000) {
                sb.append('M');
                nr -= 1000;
            } else if (nr >= 900) {
                sb.append("CM");
                nr -= 900;
            } else if (nr >= 500) {
                sb.append('D');
                nr -= 500;
            } else if (nr >= 400) {
                sb.append("CD");
                nr -= 400;
            } else if (nr >= 100) {
                sb.append('C');
                nr -= 100;
            } else if (nr >= 90) {
                sb.append("XC");
                nr -= 90;
            } else if (nr >= 50) {
                sb.append('L');
                nr -= 50;
            } else if (nr >= 40) {
                sb.append("XL");
                nr -= 40;
            } else if (nr >= 10) {
                sb.append('X');
                nr -= 10;
            } else if (nr >= 9) {
                sb.append("IX");
                nr -= 9;
            } else if (nr >= 5) {
                sb.append('V');
                nr -= 5;
            } else if (nr >= 4) {
                sb.append("IV");
                nr -= 4;
            } else { // nr > 0
                sb.append('I');
                nr--;
            }
        }
        return sb.toString();
    }
}
