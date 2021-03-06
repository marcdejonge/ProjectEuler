package euler.level2;

import java.io.BufferedReader;
import java.io.IOException;

import euler.IntegerProblem;
import euler.input.FileUtils;
import euler.numberic.RomanNumeral;

public class Problem089 extends IntegerProblem {
    @Override
    public long solve() {
        int charactersSaved = 0;
        try (BufferedReader reader = FileUtils.readInput(this)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                charactersSaved += line.length() - RomanNumeral.parse(line).toString().length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return charactersSaved;
    }
}
