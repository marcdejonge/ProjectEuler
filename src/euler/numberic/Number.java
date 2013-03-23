package euler.numberic;

import java.math.BigInteger;
import java.util.Arrays;

@SuppressWarnings("serial")
public class Number extends java.lang.Number implements Comparable<Number> {
    public static final Number ONE = Number.valueOf(1);

    public static final Number TEN = Number.valueOf(10);

    public static final Number ZERO = Number.valueOf(0);

    public static Number factorial(int value) {
        Number res = Number.ONE;
        for (int i = 2; i < value; i++) {
            res = res.multiply(Number.valueOf(i));
        }
        return res;
    }

    public static Number valueOf(int value) {
        int length = 0;
        int powerTen = 1;
        while (value >= powerTen) {
            powerTen *= 10;
            length++;
        }
        final Number n = new Number(length);
        for (int i = 0; i < length; i++) {
            n.values[i] = (byte) (value % 10);
            value /= 10;
        }
        return n;
    }

    public static Number valueOf(long value) {
        int length = 0;
        long powerTen = 1;
        while (value >= powerTen) {
            powerTen *= 10;
            length++;
        }
        final Number n = new Number(length);
        for (int i = 0; i < length; i++) {
            n.values[i] = (byte) (value % 10);
            value /= 10;
        }
        return n;
    }

    public static Number valueOf(long value, int length) {
        final Number n = new Number(length);
        for (int i = 0; i < length; i++) {
            n.values[i] = (byte) (value % 10);
            value /= 10;
        }
        return n;
    }

    private final byte[] values;

    public Number(byte[] bytes) {
        values = new byte[bytes.length];
        for (int i = 0, j = bytes.length - 1; j >= 0; i++, j--) {
            values[i] = (byte) (bytes[j] % 10);
        }
        validate();
    }

    private Number(int length) {
        values = new byte[length];
    }

    public Number add(Number other) {
        final Number res = new Number(values.length);

        int carry = 0;
        for (int i = 0; i < values.length; i++) {
            if (i < other.values.length) {
                carry += values[i] + other.values[i];
            } else {
                carry += values[i];
            }
            res.values[i] = (byte) (carry % 10);
            carry /= 10;
        }

        return res;
    }

    public Number addInc(Number other) {
        Number res = new Number(Math.max(values.length, other.values.length));

        int carry = 0;
        for (int i = 0; i < values.length; i++) {
            if (i < other.values.length) {
                carry += values[i] + other.values[i];
            } else {
                carry += values[i];
            }
            res.values[i] = (byte) (carry % 10);
            carry /= 10;
        }

        if (carry > 0) {
            final Number r = new Number(values.length + 1);
            System.arraycopy(res.values, 0, r.values, 0, values.length);
            r.values[values.length] = (byte) carry;
            res = r;
        }

        return res;
    }

    @Override
    public byte byteValue() {
        return (byte) longValue();
    }

    @Override
    public int compareTo(Number o) {
        if (getFilledLength() != o.getFilledLength()) {
            return getFilledLength() - o.getFilledLength();
        }
        for (int i = getFilledLength() - 1; i >= 0; i--) {
            if (o.values[i] != values[i]) {
                return values[i] - o.values[i];
            }
        }
        return 0;
    }

    public Number concat(Number nr) {
        final Number res = new Number(values.length + nr.values.length);
        System.arraycopy(nr.values, 0, res.values, 0, nr.values.length);
        System.arraycopy(values, 0, res.values, nr.values.length, values.length);
        return res;
    }

    public boolean contains(byte digit) {
        for (final byte b : values) {
            if (b == digit) {
                return true;
            }
        }
        return false;
    }

    public int digitalSum() {
        int nr = 0;
        for (final byte b : values) {
            nr += b;
        }
        return nr;
    }

    @Override
    public double doubleValue() {
        return longValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Number.class) {
            final Number n = (Number) obj;
            final int length = getFilledLength();
            if (n.getFilledLength() != length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (values[i] != n.values[i]) {
                    return false;
                }
            }
            return true;
        } else if (obj.getClass() == Long.class) {
            return longValue() == ((Long) obj).longValue();
        } else if (obj.getClass() == Integer.class) {
            return intValue() == ((Integer) obj).intValue();
        } else if (obj.getClass() == BigInteger.class) {
            return toString().equals(obj.toString());
        } else {
            return false;
        }
    }

    @Override
    public float floatValue() {
        return longValue();
    }

    public int getDigitAt(int index) {
        return values[getFilledLength() - index - 1];

    }

    public int getFilledLength() {
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] != 0) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public int hashCode() {
        return (int) hashCodeLong();
    }

    public long hashCodeLong() {
        long x = 0;
        for (final byte value : values) {
            x += 1 << 5 * value;
        }
        return x;
    }

    public boolean hasOnlyOddDigits() {
        for (byte value : values) {
            if ((value & 1) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean hasZeros(int nr) {
        for (int i = 0; i < nr && i < values.length; i++) {
            if (values[i] != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int intValue() {
        return (int) longValue();
    }

    public boolean isPalindrome() {
        final int length = getFilledLength();
        for (int i = 0; i < length / 2; i++) {
            if (values[i] != values[length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPandigital9() {
        if (getFilledLength() != 9) {
            return false;
        }
        int x = 0;
        for (final byte b : values) {
            x |= 1 << b;
        }
        return x == (1 << 10) - 2;
    }

    @Override
    public long longValue() {
        long value = 0;
        for (int i = values.length - 1; i >= 0; i--) {
            value = (value << 3) + (value << 1) + values[i];
        }
        return value;
    }

    public Number multiply(Number other) {
        final int length = getFilledLength() + other.getFilledLength();
        return multiply(other, length);
    }

    public Number multiply(Number other, int length) {
        final Number res = new Number(length);

        int i = 0;
        long carry = 0;
        while (i < length) {
            for (int j = i; j >= 0; j--) {
                final int ij = i - j;
                if (j < values.length && ij >= 0 && ij < other.values.length) {
                    carry += values[j] * other.values[ij];
                }
            }
            res.values[i] = (byte) (carry % 10);
            carry /= 10;
            i++;
        }
        return res;
    }

    public Number multiplyRemoveZero(Number other, int length) {
        final Number res = new Number(length);
        if (getFilledLength() == 0 || other.getFilledLength() == 0) {
            return res;
        }

        int i = 0;
        long carry = 0;
        int diff = 0;
        while (i - diff < length) {
            for (int j = i; j >= 0; j--) {
                final int ij = i - j;
                if (j < values.length && ij >= 0 && ij < other.values.length) {
                    carry += values[j] * other.values[ij];
                }
            }
            res.values[i - diff] = (byte) (carry % 10);
            carry /= 10;

            if (i == diff && res.values[0] == 0) {
                diff++;
            }
            i++;
        }
        return res;
    }

    public Number pow(int x) {
        if (x == 0) {
            return Number.ONE;
        }
        if (x == 1) {
            return this;
        }
        Number res = pow(x / 2);
        res = res.multiply(res);
        if ((x & 1) == 1) {
            return res.multiply(this);
        } else {
            return res;
        }
    }

    public Number pow(long x, int length) {
        if (x == 0) {
            return Number.ONE;
        }
        if (x == 1) {
            return this;
        }
        Number res = pow(x / 2, length);
        res = res.multiply(res, length);
        if ((x & 1) == 1) {
            return res.multiply(this, length);
        } else {
            return res;
        }
    }

    public Number replace(byte digit, byte replacement) {
        final Number number = new Number(values.length);
        for (int i = 0; i < values.length; i++) {
            if (values[i] == digit) {
                number.values[i] = replacement;
            } else {
                number.values[i] = values[i];
            }
        }
        return number;
    }

    public Number reverse() {
        final Number number = new Number(values.length);
        for (int i = 0; i < values.length; i++) {
            number.values[i] = values[values.length - 1 - i];
        }
        return number;
    }

    public Number sort() {
        final Number res = new Number(values.length);
        System.arraycopy(values, 0, res.values, 0, values.length);
        Arrays.sort(res.values);
        return res;
    }

    public Number subtract(Number other) {
        final Number res = new Number(values.length);

        for (int i = 0; i < values.length; i++) {
            if (i < other.values.length) {
                res.values[i] = (byte) (values[i] - other.values[i]);
            } else {
                res.values[i] = values[i];
            }
        }

        int needed = 0;
        for (int i = 0; i < values.length; i++) {
            res.values[i] -= needed;
            needed = 0;
            if (res.values[i] < 0) {
                res.values[i] += 10;
                needed++;
            }
        }

        return res;
    }

    @Override
    public String toString() {
        final int length = getFilledLength();
        if (length == 0) {
            return "0";
        }
        final char[] chars = new char[getFilledLength()];
        for (int i = length - 1; i >= 0; i--) {
            chars[length - 1 - i] = (char) (values[i] + '0');
        }
        return new String(chars);
    }

    private void validate() {
        for (final byte value : values) {
            if (value < 0 || value > 9) {
                throw new IllegalArgumentException("The values are not all in range of [0-9]");
            }
        }
    }
}
