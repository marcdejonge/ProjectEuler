package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import euler.Pair;
import euler.Problem;
import euler.cards.Card;
import euler.cards.Hand;

public class Problem054 extends Problem<Integer> {

	@Override
	public Integer solve() {
		try {
			List<Pair<Hand, Hand>> hands = readHands("Problem054.txt");

			int total = 0;
			for (Pair<Hand, Hand> hand : hands) {
				if (hand.getFirst().compareTo(hand.getSecond()) > 0) {
					total++;
				}
			}

			return total;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<Pair<Hand, Hand>> readHands(String file)
			throws IOException {
		List<Pair<Hand, Hand>> result = new ArrayList<Pair<Hand, Hand>>();

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.length() == 29) {
				Hand left = new Hand(new Card(line.charAt(0), line.charAt(1)),
						new Card(line.charAt(3), line.charAt(4)), new Card(
								line.charAt(6), line.charAt(7)), new Card(
								line.charAt(9), line.charAt(10)), new Card(
								line.charAt(12), line.charAt(13)));
				Hand right = new Hand(
						new Card(line.charAt(15), line.charAt(16)), new Card(
								line.charAt(18), line.charAt(19)), new Card(
								line.charAt(21), line.charAt(22)), new Card(
								line.charAt(24), line.charAt(25)), new Card(
								line.charAt(27), line.charAt(28)));
				result.add(Pair.from(left, right));
			}
		}

		return result;
	}

}
