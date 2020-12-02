package euler.level2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import euler.IntegerProblem;
import euler.JavaPair;
import euler.cards.Card;
import euler.cards.Hand;
import euler.input.FileUtils;

public class Problem054 extends IntegerProblem {
    private List<JavaPair<Hand, Hand>> readHands() throws IOException {
        final List<JavaPair<Hand, Hand>> result = new ArrayList<JavaPair<Hand, Hand>>();

        try (final BufferedReader reader = FileUtils.readInput(this)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 29) {
                    final Hand left = new Hand(new Card(line.charAt(0), line.charAt(1)),
                                               new Card(line.charAt(3), line.charAt(4)),
                                               new Card(line.charAt(6), line.charAt(7)),
                                               new Card(line.charAt(9), line.charAt(10)),
                                               new Card(line.charAt(12), line.charAt(13)));
                    final Hand right = new Hand(new Card(line.charAt(15), line.charAt(16)),
                                                new Card(line.charAt(18), line.charAt(19)),
                                                new Card(line.charAt(21), line.charAt(22)),
                                                new Card(line.charAt(24), line.charAt(25)),
                                                new Card(line.charAt(27), line.charAt(28)));
                    result.add(JavaPair.from(left, right));
                }
            }
        }

        return result;
    }

    @Override
    public long solve() throws IOException {
        final List<JavaPair<Hand, Hand>> hands = readHands();

        int total = 0;
        for (final JavaPair<Hand, Hand> hand : hands) {
            if (hand.getFirst().compareTo(hand.getSecond()) > 0) {
                total++;
            }
        }

        return total;
    }
}
