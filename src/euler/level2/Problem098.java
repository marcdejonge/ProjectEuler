package euler.level2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import euler.IntegerProblem;
import euler.combination.CombinationGenerator;
import euler.input.FileUtils;

public class Problem098 extends IntegerProblem {
    private int decode(char[] word, char[] encoding) {
        int nr = 0;
        for (char c : word) {
            for (int ix = 0; ix < encoding.length; ix++) {
                if (encoding[ix] == c) {
                    nr = nr * 10 + ix;
                    break;
                }
            }
        }
        return nr;
    }

    private char[] getEncoding(char[] word, int nr) {
        char[] encoding = new char[10];
        int rest = nr;
        for (int ix = word.length - 1; ix >= 0; ix--) {
            int jx = rest % 10;
            if (encoding[jx] != 0 && encoding[jx] != word[ix]) {
                return null; // No encoding possible
            }
            encoding[jx] = word[ix];
            rest /= 10;
        }
        return encoding;
    }

    private boolean isSquare(int n) {
        double d = Math.sqrt(n);
        return d == (long) d;
    }

    private Collection<List<String>> readAnagrams() {
        try {
            final List<String> words = FileUtils.readNames(this);
            Map<String, List<String>> anagrams = new HashMap<String, List<String>>();
            for (String word : words) {
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                String key = new String(letters);
                if (anagrams.containsKey(key)) {
                    anagrams.get(key).add(word);
                } else {
                    ArrayList<String> list = new ArrayList<String>();
                    anagrams.put(key, list);
                    list.add(word);
                }
            }
            Iterator<Entry<String, List<String>>> it = anagrams.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue().size() == 1) {
                    it.remove();
                }
            }
            return anagrams.values();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public long solve() {
        Collection<List<String>> anagrams = readAnagrams();

        int maxFound = 0;

        for (List<String> list : anagrams) {
            int maxSquared = 1;
            for (int x = 0; x < list.get(0).length(); x++) {
                maxSquared *= 10;
            }
            int minSquared = maxSquared / 10;

            for (String[] pair : new CombinationGenerator<String>(list.toArray(new String[list.size()]), 2)) {
                char[] left = pair[0].toCharArray();
                char[] right = pair[1].toCharArray();

                for (int nr = 1, nrSquared = nr * nr; nrSquared < maxSquared; nr++, nrSquared = nr * nr) {
                    if (nrSquared >= minSquared) {
                        char[] encoding = getEncoding(left, nrSquared);

                        if (encoding != null) {
                            int constructudSquare = decode(right, encoding);

                            if (constructudSquare > minSquared && isSquare(constructudSquare)) {
                                maxFound = Math.max(maxFound, Math.max(constructudSquare, nrSquared));
                            }
                        }
                    }
                }
            }
        }

        return maxFound;
    }
}
