package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import euler.Problem;
import euler.numberic.BitSet;

public class Problem079 extends Problem<Number> {
    @Override
    public Number solve() {
        final Byte[] bytes = new Byte[256];
        for (char c = '0'; c <= '9'; c++) {
            bytes[c] = Byte.valueOf((byte) (c - '0'));
        }

        final Map<Byte, BitSet> map = new HashMap<Byte, BitSet>();

        try (final BufferedReader reader = new BufferedReader(new FileReader("Problem79.txt"))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                final int max = line.length();
                for (int fIx = 0; fIx < max; fIx++) {
                    final Byte first = bytes[line.charAt(fIx)];
                    if (!map.containsKey(first)) {
                        map.put(first, new BitSet(10));
                    }
                    if (first != null) {
                        for (int sIx = fIx + 1; sIx < max; sIx++) {
                            final Byte second = bytes[line.charAt(sIx)];
                            if (!map.containsKey(second)) {
                                map.put(second, new BitSet(10));
                            }
                            map.get(second).set(first.intValue());
                        }
                    }
                }
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
            return null;
        }

        int result = 0;

        while (!map.isEmpty()) {
            for (Byte key : map.keySet()) {
                if (map.get(key).cardinality() == 0) {
                    result *= 10;
                    result += key.intValue();
                    map.remove(key);

                    for (BitSet bs : map.values()) {
                        bs.reset(key.intValue());
                    }
                    break;
                }
            }
        }

        return result;
    }
}
