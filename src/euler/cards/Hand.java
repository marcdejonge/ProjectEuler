package euler.cards;

import java.util.Arrays;
import java.util.Comparator;

import euler.cards.Card.Rank;

public class Hand {
	public enum Type {
		HighCard,
		OnePair,
		TwoPair,
		ThreeOfAKind,
		Straight,
		Flush,
		FullHouse,
		FourOfAKind,
		StraightFlush,
		RoyalFlush
	}

	private Card[] cards;

	private Type type;

	public Hand(Card... cards) {
		this.cards = cards;
		this.type = determineType();
	}

	public Card[] getCards() {
		return cards.clone();
	}
	
	public Type getType() {
		return type;
	}

	private Type determineType() {
		Arrays.sort(cards, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				if (!c1.getRank().equals(c2.getRank())) {
					return c1.getRank().compareTo(c2.getRank());
				} else {
					return c1.getSuite().compareTo(c2.getSuite());
				}
			}
		});

		int pair = 0, threeOaK = 0, fourOaK = 0;
		{
			Rank current = cards[0].getRank();
			int count = 1;
			for (int ix = 1; ix < cards.length; ix++) {
				if (cards[ix].getRank() == current) {
					count++;
				} else {
					if (count == 2) {
						pair++;
					} else if (count == 3) {
						threeOaK++;
					} else if (count == 4) {
						fourOaK++;
					}
					count = 1;
					current = cards[ix].getRank();
				}
			}
		}
		
		Rank highest = cards[cards.length - 1].getRank();

		Arrays.sort(cards);
		boolean flush = cards[0].getSuite() == cards[cards.length - 1].getSuite();

		boolean straight = true;
		for (int ix = 0; straight && ix < cards.length - 1; ix++) {
			if (cards[ix].getRank().ordinal() - cards[ix + 1].getRank().ordinal() != 1) {
				straight = false;
			}
		}
		
		if(flush && highest == Rank.Ace) {
			return Type.RoyalFlush;
		} else if(straight && flush) {
			return Type.StraightFlush;
		} else if(fourOaK >= 1) {
			return Type.FourOfAKind;
		} else if(threeOaK >=1 && pair>=1) {
			return Type.FullHouse;
		} else if(flush) {
			return Type.Flush;
		} else if(straight) {
			return Type.Straight;
		} else if(threeOaK>=1) {
			return Type.ThreeOfAKind;
		} else if(pair >=2) {
			return Type.TwoPair;
		} else if(pair >= 1) {
			return Type.OnePair;
		} else {
			return Type.HighCard;
		}
	}
}
