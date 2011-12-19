package euler.numberic;

import java.util.Arrays;

public class BitSet implements Cloneable {
    private final static long ONE_LEFT = 1l << 63, ALL_SET = -1l, SHIFT = 6, MASK = 63;

    private int length;

    private long[] words;

    public BitSet(int length) {
        final int nrInts = (length >>> BitSet.SHIFT) + ((length & BitSet.MASK) == 0 ? 0 : 1);
        words = new long[nrInts];
        this.length = length;
    }

    public BitSet and(BitSet other) {
        final BitSet result = new BitSet(Math.min(length, other.length));

        for (int i = 0; i < result.words.length; i++) {
            result.words[i] = words[i] & other.words[i];
        }

        return result;
    }

    public int cardinality() {
        int sum = 0;
        for (final long value : words) {
            sum += Long.bitCount(value);
        }
        return sum;
    }

    @Override
    public BitSet clone() {
        final BitSet bs = new BitSet(length);
        System.arraycopy(words, 0, bs.words, 0, words.length);
        return bs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        final BitSet other = (BitSet) obj;
        return length == other.length && Arrays.equals(words, other.words);
    }

    public void flip(int index) {
        words[index >>> BitSet.SHIFT] ^= BitSet.ONE_LEFT >>> index;
    }

    public void flip(int from, int to) {
        if (to <= from) {
            return;
        }
        final int fromWordIndex = from >>> BitSet.SHIFT, toWordIndex = to >>> BitSet.SHIFT;
        if (fromWordIndex == toWordIndex) {
            words[fromWordIndex] = (1 << to - from) - 1;
            words[fromWordIndex] <<= 32 - (to & BitSet.MASK);
        } else {
            words[fromWordIndex] ^= BitSet.ALL_SET >>> from;
            if ((to & BitSet.MASK) != 0) {
                words[toWordIndex] ^= BitSet.ALL_SET ^ BitSet.ALL_SET >>> to;
            }
            for (int i = fromWordIndex + 1; i < toWordIndex; i++) {
                words[i] ^= BitSet.ALL_SET;
            }
        }
    }

    public int getLength() {
        return length;
    }

    @Override
    public int hashCode() {
        return length * 31 ^ Arrays.hashCode(words);
    }

    public boolean intersects(BitSet other) {
        for (int i = Math.min(words.length, other.words.length) - 1; i >= 0; i--) {
            if ((words[i] & other.words[i]) != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isSet(int index) {
        if (index < 0 || index >= length) {
            return false;
        }
        return (words[index >>> BitSet.SHIFT] & BitSet.ONE_LEFT >>> index) != 0;
    }

    public int nextSetBit(int index) {
        int wordIndex = index >>> BitSet.SHIFT;

        if (wordIndex >= words.length) {
            return -1;
        }

        long word = words[wordIndex] & BitSet.ALL_SET >>> index;

        while (true) {
            if (word != 0) {
                return (wordIndex << BitSet.SHIFT) + Long.numberOfLeadingZeros(word);
            }
            if (++wordIndex == words.length) {
                return -1;
            }
            word = words[wordIndex];
        }
    }

    public BitSet or(BitSet other) {
        final BitSet result = new BitSet(Math.min(length, other.length));

        for (int i = 0; i < result.words.length; i++) {
            result.words[i] = words[i] | other.words[i];
        }

        return result;
    }

    public void orSet(BitSet other) {
        for (int i = 0; i < Math.min(words.length, other.words.length); i++) {
            words[i] |= other.words[i];
        }
    }

    public void reset() {
        for (int i = 0; i < words.length; i++) {
            words[i] = 0;
        }
    }

    public void reset(int index) {
        final int wordIndex = index >>> BitSet.SHIFT;
        if (wordIndex >= words.length) {
            return;
        }

        words[wordIndex] &= BitSet.ALL_SET ^ BitSet.ONE_LEFT >>> index;
    }

    public BitSet set(int index) {
        final int wordIndex = index >>> BitSet.SHIFT;
        if (wordIndex >= words.length) {
            return this;
        }

        words[wordIndex] |= BitSet.ONE_LEFT >>> index;
        return this;
    }

    public void setAll() {
        for (int i = 0; i < words.length; i++) {
            words[i] = -1;
        }
        if ((length & BitSet.MASK) != 0) {
            // The last one should only set the 'length' bits
            words[words.length - 1] ^= BitSet.ALL_SET >>> length;
        }
    }

    public void setLength(int length) {
        length++;
        final int newWordLength = (length >>> BitSet.SHIFT) + ((length & BitSet.MASK) == 0 ? 0 : 1);
        if (newWordLength > words.length) {
            words = new long[newWordLength];
        }
        this.length = length;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int x = nextSetBit(0); x >= 0; x = nextSetBit(x + 1)) {
            sb.append(x);
            sb.append(',');
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }
}
