package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import euler.Problem;
import euler.numberic.RomanNumeral;

public class Problem089 extends Problem<Integer> {
    @Override
    public Integer solve() {
        int charactersSaved = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Problem089.txt"));
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
