package euler.cards;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import euler.cards.Card.Rank;

public class Hand implements Comparable<Hand> {
    public static class Flush extends Type {
        public Flush(Card... cards) {
            super(5, cards);
        }
    }

    public static class FourOfAKind extends Type {
        public FourOfAKind(Card first, Card second, Card third, Card fourth) {
            super(7, first, second, third, fourth);
        }
    }

    public static class FullHouse extends Type {
        public FullHouse(ThreeOfAKind tok, Pair pair) {
            super(6, tok, pair);
        }
    }

    public static class HighCard extends Type {
        public HighCard(Card card) {
            super(0, card);
        }
    }

    public static class Pair extends Type {
        public Pair(Card first, Card second) {
            super(1, first, second);
        }
    }

    public static class RoyalFlush extends Type {
        public RoyalFlush(Straight straight, Flush flush) {
            super(9, straight, flush);
        }
    }

    public static class Straight extends Type {
        public Straight(Card... cards) {
            super(4, cards);
        }
    }

    public static class StraightFlush extends Type {
        public StraightFlush(Straight straight, Flush flush) {
            super(8, straight, flush);
        }
    }

    public static class ThreeOfAKind extends Type {
        public ThreeOfAKind(Card first, Card second, Card third) {
            super(3, first, second, third);
        }
    }

    public static class TwoPair extends Type {
        public TwoPair(Pair first, Pair second) {
            super(2, first, second);
        }
    }

    public abstract static class Type implements Comparable<Type> {
        private final int ranking;

        private final SortedSet<Card> cards;

        public Type(int ranking) {
            this.ranking = ranking;
            cards = new TreeSet<Card>();
        }

        public Type(int ranking, Card... cards) {
            this(ranking);
            for (final Card card : cards) {
                this.cards.add(card);
            }
        }

        public Type(int ranking, Type... types) {
            this(ranking);
            for (final Type type : types) {
                cards.addAll(type.cards);
            }
        }

        @Override
        public int compareTo(Type other) {
            int cmp = other.ranking - ranking;
            final Iterator<Card> thisIt = cards.iterator();
            final Iterator<Card> otherIt = other.cards.iterator();
            while (cmp == 0 && thisIt.hasNext() && otherIt.hasNext()) {
                cmp = thisIt.next().compareTo(otherIt.next());
            }
            return cmp;
        }

        public SortedSet<Card> getCards() {
            return cards;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + cards;
        }
    }

    private final Card[] cards;

    private final SortedSet<Type> types;

    public Hand(Card... cards) {
        this.cards = cards;
        types = determineType();
    }

    @Override
    public int compareTo(Hand other) {
        int cmp = 0;
        final Iterator<Type> thisIt = types.iterator();
        final Iterator<Type> otherIt = other.types.iterator();
        while (cmp == 0 && thisIt.hasNext() && otherIt.hasNext()) {
            cmp = otherIt.next().compareTo(thisIt.next());
        }
        return cmp / Math.abs(cmp);
    }

    private SortedSet<Type> determineType() {
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

        final SortedSet<HighCard> highCards = new TreeSet<Hand.HighCard>();
        final SortedSet<Pair> pairs = new TreeSet<Pair>();
        final SortedSet<ThreeOfAKind> threeOaKs = new TreeSet<ThreeOfAKind>();
        final SortedSet<FourOfAKind> fourOaKs = new TreeSet<FourOfAKind>();
        {
            Rank current = cards[0].getRank();
            int count = 1;
            for (int ix = 1; ix < cards.length; ix++) {
                if (cards[ix].getRank() == current) {
                    count++;
                } else {
                    if (count == 1) {
                        highCards.add(new HighCard(cards[ix - 1]));
                    } else if (count == 2) {
                        pairs.add(new Pair(cards[ix - 2], cards[ix - 1]));
                    } else if (count == 3) {
                        threeOaKs.add(new ThreeOfAKind(cards[ix - 3], cards[ix - 2], cards[ix - 1]));
                    } else if (count == 4) {
                        fourOaKs.add(new FourOfAKind(cards[ix - 4], cards[ix - 3], cards[ix - 2], cards[ix - 1]));
                    }
                    count = 1;
                    current = cards[ix].getRank();
                }
            }
            if (count == 1) {
                highCards.add(new HighCard(cards[cards.length - 1]));
            } else if (count == 2) {
                pairs.add(new Pair(cards[cards.length - 2], cards[cards.length - 1]));
            } else if (count == 3) {
                threeOaKs.add(new ThreeOfAKind(cards[cards.length - 3], cards[cards.length - 2], cards[cards.length - 1]));
            } else if (count == 4) {
                fourOaKs.add(new FourOfAKind(cards[cards.length - 4],
                                             cards[cards.length - 3],
                                             cards[cards.length - 2],
                                             cards[cards.length - 1]));
            }
        }

        Arrays.sort(cards);

        boolean isFlush = true;
        for (int ix = 1; ix < cards.length; ix++) {
            isFlush &= cards[ix - 1].getSuite() == cards[ix].getSuite();
        }
        Flush flush = isFlush ? new Flush(cards) : null;

        boolean isStraight = true;
        for (int ix = 0; isStraight && ix < cards.length - 1; ix++) {
            if (cards[ix].getRank().ordinal() - cards[ix + 1].getRank().ordinal() != 1) {
                isStraight = false;
            }
        }

        Straight straight = isStraight ? new Straight(cards) : null;

        final SortedSet<Type> result = new TreeSet<Hand.Type>();

        if (straight != null && flush != null) {
            if (straight.getCards().first().getRank() == Rank.Ace) {
                result.add(new RoyalFlush(straight, flush));
            } else {
                result.add(new StraightFlush(straight, flush));
            }
            straight = null;
            flush = null;
            highCards.clear();
        }
        if (fourOaKs.size() > 0) {
            result.addAll(fourOaKs);
        }
        if (threeOaKs.size() == 1 && pairs.size() == 1) {
            result.add(new FullHouse(threeOaKs.first(), pairs.first()));
            threeOaKs.clear();
            pairs.clear();
        }
        if (flush != null) {
            result.add(flush);
        }
        if (straight != null) {
            result.add(straight);
        }
        if (threeOaKs.size() > 0) {
            result.addAll(threeOaKs);
        }
        if (pairs.size() == 2) {
            result.add(new TwoPair(pairs.first(), pairs.last()));
            pairs.clear();
        }
        if (pairs.size() > 0) {
            result.addAll(pairs);
        }
        result.addAll(highCards);

        return result;
    }

    public Card[] getCards() {
        return cards.clone();
    }

    public SortedSet<Type> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        return "Hand " + Arrays.toString(cards) + " => " + getTypes();
    }
}
