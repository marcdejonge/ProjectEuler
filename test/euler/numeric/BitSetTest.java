package euler.numeric;

import org.junit.Assert;
import org.junit.Test;

import euler.numberic.BitSet;

public class BitSetTest {
    private static final int MAX_LENGTH = 1024;

    @Test
    public void andOrTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet empty = new BitSet(length);
            final BitSet even = generateEven(length), odd = generateOdd(length);
            for (int i = 0; i < length; i++) {
                ((i & 1) == 0 ? even : odd).set(i);
            }
            final BitSet full = even.or(odd);
            final BitSet empty2 = even.and(odd);

            Assert.assertEquals(0, empty.cardinality());
            Assert.assertEquals(length / 2 + ((length & 1) == 0 ? 0 : 1), even.cardinality());
            Assert.assertEquals(length / 2, odd.cardinality());
            Assert.assertEquals(length, full.cardinality());
            Assert.assertEquals(empty, empty2);
        }
    }

    @Test
    public void cloneTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet[] bss = generateArray(length);
            for (final BitSet bs : bss) {
                Assert.assertEquals(bs, bs.clone());
            }
        }
    }

    @Test
    public void flipTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet set = new BitSet(length);
            Assert.assertEquals(0, set.cardinality());
            Assert.assertEquals(length, set.getLength());

            set.setAll();
            Assert.assertEquals(length, set.cardinality());
            Assert.assertEquals(length, set.getLength());

            for (int i = 0; i < length; i++) {
                set.flip(i);
                Assert.assertEquals(length - i - 1, set.cardinality());
            }
        }
    }

    private BitSet[] generateArray(int length) {
        return new BitSet[] { new BitSet(length), generateEven(length), generateFull(length), generateOdd(length) };
    }

    private BitSet generateEven(int length) {
        final BitSet bs = new BitSet(length);
        for (int i = 0; i < length; i += 2) {
            bs.set(i);
        }
        return bs;
    }

    private BitSet generateFull(int length) {
        final BitSet bs = new BitSet(length);
        bs.setAll();
        return bs;
    }

    private BitSet generateOdd(int length) {
        final BitSet bs = new BitSet(length);
        for (int i = 1; i < length; i += 2) {
            bs.set(i);
        }
        return bs;
    }

    @Test
    public void intersectTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet even = generateEven(length), odd = generateOdd(length), full = generateFull(length), empty = new BitSet(length);
            Assert.assertEquals(false, even.intersects(odd));
            Assert.assertEquals(length >= 1, even.intersects(full));
            Assert.assertEquals(length >= 2, odd.intersects(full));
            Assert.assertEquals(false, even.intersects(empty));
            Assert.assertEquals(false, odd.intersects(empty));
        }
    }

    @Test
    public void isSetTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet even = generateEven(length);
            for (int i = 0; i < length; i++) {
                Assert.assertEquals((i & 1) == 0, even.isSet(i));
            }

            final BitSet odd = generateOdd(length);
            for (int i = 0; i < length; i++) {
                Assert.assertEquals((i & 1) == 1, odd.isSet(i));
            }
        }
    }

    @Test
    public void resetTest() {
        for (int length = 0; length < BitSetTest.MAX_LENGTH; length++) {
            final BitSet even = generateEven(length);
            even.reset();

        }
    }
}
