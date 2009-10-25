package euler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import euler.numberic.BitSet;

public class Util {
	private final static List<Integer> digits = new ArrayList<Integer>();

	private static int[] factorials = new int[] {
	                                             1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880
	};

	private static int[] factors = null;

	private static BitSet primes = null;

	private static BitSet relativePrimes = new BitSet(1000000);

	private final static List<Integer> remainders = new ArrayList<Integer>();

	private static BitSet calcDivisors(long value) {
		BitSet bs = new BitSet((int) Math.sqrt(value) + 1);
		for (int i = 1; i < bs.getLength(); i++) {
			if (value % i == 0) {
				bs.set(i);
			}
		}
		return bs;
	}

	private static int[] calcPrimeFactors(long value) {
		int max = (int) (value / 2);
		if (Util.primes == null || Util.primes.getLength() < max) {
			Util.primes = Util.calcPrimesUntil(max);
		}

		max = (int) (Math.log(value) / Math.log(2));
		if (Util.factors == null || Util.factors.length < max) {
			Util.factors = new int[max];
		}

		int i = 0, j = 2;
		while (j < Util.primes.getLength() && value > 1) {
			if (Util.primes.isSet(j) && (value % j) == 0) {
				Util.factors[i++] = j;
				value /= j;
			} else {
				j++;
			}
		}

		if (i == 0) {
			return new int[] {
			                  (int) value
			};
		} else {
			int[] result = new int[i];
			System.arraycopy(Util.factors, 0, result, 0, i);
			return result;
		}
	}

	private static BitSet calcPrimesUntil(int value) {
		BitSet bs = new BitSet(value);
		int max = (int) Math.sqrt(value);
		bs.set(2);
		for (int i = 3; i < value; i += 2) {
			bs.set(i);
		}
		for (int prime = 3; prime < max; prime += 2) {
			if (bs.isSet(prime)) {
				for (int comp = prime; comp < value && comp > 0; comp += prime) {
					bs.reset(comp);
				}
			}
		}
		return bs;
	}

	private static int chain(long value) {
		int length = 0;
		while (value != 1 && length >= 0) {
			if ((value & 1) == 0) {
				value = value >>> 1;
			} else {
				value = value * 3 + 1;
			}
			length++;
		}
		return length;
	}

	private static int factLoopLength(long value) {
		int length = 1;
		while (true) {
			if (value == 145 || value == 1 || value == 0 || value == 2 || value == 40585) {
				return length;
			} else if (value == 169 || value == 363601 || value == 1454) {
				return length + 2;
			} else if (value == 871 || value == 872 || value == 45361 || value == 45362) {
				return length + 1;
			} else {
				value = Util.nextFactorial(value);
				length++;
			}
		}
	}

	private static long[] generateHexagulars(int length) {
		long[] hex = new long[length];
		for (int i = 0; i < length; i++) {
			hex[i] = (i * (2 * i - 1));
		}
		return hex;
	}

	private static BigInteger[] generateHexagularsBig(int length) {
		BigInteger[] hex = new BigInteger[length];
		for (int i = 0; i < length; i++) {
			BigInteger n = BigInteger.valueOf(i + 1);
			hex[i] = n.multiply(n.multiply(BigInteger.valueOf(2)).subtract(BigInteger.ONE));
		}
		return hex;
	}

	private static long[] generatePentagulars(int length) {
		long[] pent = new long[length];
		for (int i = 0; i < length; i++) {
			pent[i] = (i * (3 * i - 1)) / 2;
		}
		return pent;
	}

	private static BigInteger[] generatePentagularsBig(int length) {
		BigInteger[] pent = new BigInteger[length];
		BigInteger three = BigInteger.valueOf(3);
		for (int i = 0; i < length; i++) {
			BigInteger n = BigInteger.valueOf(i + 1);
			pent[i] = n.multiply(three.multiply(n).subtract(BigInteger.ONE)).shiftRight(1);
		}
		return pent;
	}

	private static long[] generateTriangulars(int length) {
		long[] triangleNrs = new long[length];
		for (int i = 0; i < length; i++) {
			triangleNrs[i] = (i * (i + 1)) / 2;
		}
		return triangleNrs;
	}

	private static BigInteger[] generateTriangularsBig(int length) {
		BigInteger[] triangleNrs = new BigInteger[length];
		for (int i = 0; i < length; i++) {
			BigInteger n = BigInteger.valueOf(i + 1);
			triangleNrs[i] = n.multiply(n.add(BigInteger.ONE)).shiftRight(1);
		}
		return triangleNrs;
	}

	private static byte[] getDigits(long prime) {

		return null;
	}

	private static boolean isAbundant(int value) {
		return value < Util.sumOfDivisors(value);
	}

	private static boolean isAmicable(int value) {
		int sum = Util.sumOfDivisors(value);
		return sum != value && value == Util.sumOfDivisors(sum);
	}

	private static boolean isPalindrome(String string) {
		for (int i = 0; i < string.length() / 2; i++) {
			if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isPerfect(int value) {
		return value == Util.sumOfDivisors(value);
	}

	private static boolean isPrime(long value) {
		int max = (int) Math.sqrt(value) + 1;
		if (Util.primes == null || Util.primes.getLength() < max) {
			Util.primes = Util.calcPrimesUntil(max);
		}

		if (Util.primes.getLength() > value) {
			return Util.primes.isSet((int) value);
		}

		for (int i = 2; i < max; i++) {
			if (Util.primes.isSet(i) && value % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isRelativePrime(long v1, long v2) {
		if ((v1 & 1) == 0 && (v2 & 1) == 0) {
			return false;
		}

		int max = (int) Math.min(v1, v2);
		if (Util.primes == null || Util.primes.getLength() < max) {
			Util.primes = Util.calcPrimesUntil(max);
		}

		for (int x = 3; x <= max; x += 2) {
			if (Util.primes.isSet(x) && v1 % x == 0 && v2 % x == 0) {
				return false;
			}
		}
		return true;
	}

	private static long lastNumbersFact(long until, int mod) {
		long value = 1;
		for (long i = 1; i <= until; i++) {
			long m = i;
			while (m % 5 == 0) {
				m /= 5;
				value /= 2;
			}
			value = (value * m) % mod;
		}
		return value;
	}

	private static long nextFactorial(long value) {
		long res = 0;
		while (value > 0) {
			res += Util.factorials[(int) (value % 10)];
			value /= 10;
		}
		return res;
	}

	private static int nrOfRelativePrimes(int value, int from, int until) {
		Util.relativePrimes.setLength(value);

		int max = value;
		if (Util.primes == null || Util.primes.getLength() < max) {
			Util.primes = Util.calcPrimesUntil(max);
		}

		Util.relativePrimes.reset();
		Util.relativePrimes.flip(from, until);
		if ((value & 1) == 0) {
			for (int j = 2; j < value; j += 2) {
				Util.relativePrimes.reset(j);
			}
		}
		for (int i = 3; i <= max; i += 2) {
			if (value % i == 0 && Util.primes.isSet(i)) {
				for (int j = i; j < value; j += i) {
					Util.relativePrimes.reset(j);
				}
			}
		}

		return Util.relativePrimes.cardinality();
	}

	private static int nrOfWaysToAddUp(int sum, int max) {
		if (sum <= 1 || max == 1) {
			return 1;
		}
		int total = 0;
		for (int i = 1; i <= Math.min(max, sum); i++) {
			total += Util.nrOfWaysToAddUp(sum - i, i);
		}
		return total;
	}

	private static <T extends Comparable<T>> boolean prevLine(T[] line) {
		for (int i = line.length - 1; i > 0; i--) {
			if (line[i].compareTo(line[i - 1]) < 0) {
				T max = null;
				int maxIndex = i;
				for (int j = i; j < line.length; j++) {
					if ((max == null || line[j].compareTo(max) > 0)
							&& line[j].compareTo(line[i - 1]) < 0) {
						max = line[j];
						maxIndex = j;
					}
				}
				Util.swap(line, maxIndex, i - 1);

				Arrays.sort(line, i, line.length);
				for (int j = 0; i + j < (line.length - 1 - j); j++) {
					Util.swap(line, i + j, line.length - 1 - j);
				}
				return true;
			}
		}
		return false;
	}

	private static List<int[]> readNumberPairs(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		List<int[]> result = new ArrayList<int[]>();
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] nrs = line.split(",");
			if (nrs.length != 2) {
				throw new IOException("More than two parts on one line!");
			}
			try {
				result.add(new int[] {
				                      Integer.parseInt(nrs[0]), Integer.parseInt(nrs[1])
				});
			} catch (NumberFormatException e) {
				throw new IOException("Not a number that was found!");
			}
		}
		return result;
	}

	/** Wordt ondersteund tot 100 miljoen! */
	private static int rotate(int value, int powerTen) {
		int res = (value * 10) % powerTen;
		res += (value * 10) / powerTen;
		return res;
	}

	private static int squareOfDigits(int value) {
		int result = 0;
		while (value > 0) {
			int x = value % 10;
			result += x * x;
			value /= 10;
		}
		return result;
	}

	private static int sumOfDigits(BigInteger bi) {
		String s = bi.toString();
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += s.charAt(i) - '0';
		}
		return sum;
	}

	private static int sumOfDivisors(int value) {
		int sum = 1;
		int max = (int) Math.sqrt(value);
		for (int i = 2; i < max; i++) {
			if (value % i == 0) {
				sum += i;
				sum += value / i;
			}
		}
		if (value % max == 0) {
			sum += max;
			if (value / max != max) {
				sum += value / max;
			}
		}
		return sum;
	}

	private static <T> void swap(T[] array, int i, int j) {
		T t = array[i];
		array[i] = array[j];
		array[j] = t;
	}

	private static long toInt(Integer[] numbers) {
		long powerTen = 1;
		long value = 0;
		for (int i = numbers.length - 1; i >= 0; i--) {
			value += numbers[i] * powerTen;
			powerTen *= 10;
		}
		return value;
	}

	private static int[] toIntArray(BitSet bs) {
		int[] values = new int[bs.cardinality()];
		int v = 0;
		for (int i = 0; i < values.length; i++) {
			while (!bs.isSet(v)) {
				v++;
			}
			values[i] = v;
			v++;
		}
		return values;
	}

	private static void usePrimes(BitSet bs) {
		Util.primes = bs;
	}
}
