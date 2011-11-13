package euler.cards;

public class Card implements Comparable<Card> {
	public static enum Suite {
		Spades, Hearts, Diamonds, Clubs
	}

	public static enum Rank {
		Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King, Ace
	}

	private final Suite suite;

	private final Rank rank;

	public Card(char rank, char suite) {
		switch (rank) {
			case '2':
				this.rank = Rank.Two;
				break;
			case '3':
				this.rank = Rank.Three;
				break;
			case '4':
				this.rank = Rank.Four;
				break;
			case '5':
				this.rank = Rank.Five;
				break;
			case '6':
				this.rank = Rank.Six;
				break;
			case '7':
				this.rank = Rank.Seven;
				break;
			case '8':
				this.rank = Rank.Eight;
				break;
			case '9':
				this.rank = Rank.Nine;
				break;
			case 'T':
				this.rank = Rank.Ten;
				break;
			case 'J':
				this.rank = Rank.Jack;
				break;
			case 'Q':
				this.rank = Rank.Queen;
				break;
			case 'K':
				this.rank = Rank.King;
				break;
			case 'A':
				this.rank = Rank.Ace;
				break;
			default:
				throw new IllegalArgumentException("No correct rank given");
		}
		switch (suite) {
			case 'S':
				this.suite = Suite.Spades;
				break;
			case 'H':
				this.suite = Suite.Hearts;
				break;
			case 'D':
				this.suite = Suite.Diamonds;
				break;
			case 'C':
				this.suite = Suite.Clubs;
				break;
			default:
				throw new IllegalArgumentException("No correct suite given");
		}
	}

	public Suite getSuite() {
		return suite;
	}

	public Rank getRank() {
		return rank;
	}

	@Override
	public int compareTo(Card other) {
		if (this.rank != other.rank) {
			return other.rank.compareTo(this.rank);
		} else {
			return other.suite.compareTo(this.suite);
		}
	}

	@Override
	public int hashCode() {
		return 31 * rank.hashCode() + suite.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null || getClass() != obj.getClass()) {
			return false;
		} else {
			Card other = (Card) obj;
			return rank.equals(other.rank) && suite.equals(other.suite);
		}
	}
	
	@Override
	public String toString() {
		return rank + " " + suite;
	}
}
