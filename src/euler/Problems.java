package euler;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;

import euler.field.Matrix;
import euler.field.Node;
import euler.field.TriangleNumbers;
import euler.numberic.BitSet;
import euler.twoD.Line;
import euler.twoD.Triangle;
import euler.twoD.Vector;

public class Problems {
	public static void problem160() {
		System.out.println(Util.lastNumbersFact(2560000, 100000));
	}

	public static void problem162() {
		// int t = 0;
		// for(int i = 0; i < 16*16*16*16*16*16; i++) {
		// String s = Integer.toHexString(i);
		// if(s.contains("1") && s.contains("0") && s.contains("a")) {
		// t++;
		// }
		// }
		// System.out.println(t);

		BigInteger total = BigInteger.ZERO;
		BigInteger SIXTEEN = BigInteger.valueOf(16), FIFTEEN = BigInteger.valueOf(15), FOURTEEN = BigInteger.valueOf(14), THIRTEEN = BigInteger.valueOf(13);
		BigInteger alles = FIFTEEN;
		BigInteger zonder1 = FIFTEEN.add(FOURTEEN).add(FOURTEEN);
		BigInteger zonder2 = FOURTEEN.add(FOURTEEN).add(THIRTEEN);
		BigInteger zonder3 = THIRTEEN;
		for (int i = 1; i <= 16; i++) {
			total = total.add(alles.subtract(zonder1).add(zonder2).subtract(zonder3));
			System.out.println(total.toString().toUpperCase());
			alles = alles.multiply(SIXTEEN);
			zonder1 = zonder1.multiply(FIFTEEN);
			zonder2 = zonder2.multiply(FOURTEEN);
			zonder3 = zonder3.multiply(THIRTEEN);
		}
		System.out.println(total.toString(16).toUpperCase());
	}

	public static void problem165() {
		// Line l1 = new Line(27,44,12,32);
		// Line l2 = new Line(46,53,17,62);
		// Line l3 = new Line(46,70,22,40);
		// System.out.println(l1.crosses(l2));
		// System.out.println(l1.crosses(l3));
		// System.out.println(l2.crosses(l1));
		// System.out.println(l2.crosses(l3));
		// System.out.println(l3.crosses(l1));
		// System.out.println(l3.crosses(l2));
		// System.out.println(new Line(30,0,30,10000).crosses(new Line(30,50,20,20)));
		// System.out.println(new Line(30,0,31,10000).crosses(new Line(30,50,50,80)));

		long[] s = new long[20000];
		s[0] = (290797l * 290797l) % 50515093;
		for (int i = 1; i < 20000; i++) {
			s[i] = (s[i - 1] * s[i - 1]) % 50515093;
		}

		Line[] t = new Line[5000];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Line((int) (s[4 * i] % 500), (int) (s[4 * i + 1] % 500),
				(int) (s[4 * i + 2] % 500), (int) (s[4 * i + 3] % 500));
		}

		int total = 0;
		for (int x = 0; x < t.length; x++) {
			for (int y = x + 1; y < t.length; y++) {
				if (t[x].crosses(t[y])) {
					total++;
				}
			}
		}

		System.out.println(total);
	}

	public static void problem205() {
		int[] pyramidalDice = new int[] {
		                                 1, 2, 3, 4
		};
		int[] cubicDice = new int[] {
		                             1, 2, 3, 4, 5, 6
		};

		int[] optionsPyramid = new int[40];
		double totalPyramid = 0;
		int[] optionsCubic = new int[40];
		double totalCubic = 0;

		for (int a : pyramidalDice) {
			for (int b : pyramidalDice) {
				for (int c : pyramidalDice) {
					for (int d : pyramidalDice) {
						for (int e : pyramidalDice) {
							for (int f : pyramidalDice) {
								for (int g : pyramidalDice) {
									for (int h : pyramidalDice) {
										for (int i : pyramidalDice) {
											optionsPyramid[a + b + c + d + e + f + g + h + i]++;
											totalPyramid++;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		for (int a : cubicDice) {
			for (int b : cubicDice) {
				for (int c : cubicDice) {
					for (int d : cubicDice) {
						for (int e : cubicDice) {
							for (int f : cubicDice) {
								optionsCubic[a + b + c + d + e + f]++;
								totalCubic++;
							}
						}
					}
				}
			}
		}

		double chance = 0;

		for (int i = 0; i < optionsCubic.length; i++) {
			if (optionsCubic[i] == 0) {
				continue;
			}
			int total = 0;
			for (int j = i + 1; j < optionsPyramid.length; j++) {
				total += optionsPyramid[j];
			}
			if (total == 0) {
				continue;
			}
			chance += (optionsCubic[i] / totalCubic) * (total / totalPyramid);
		}

		System.out.printf("%8.7f%n", chance);
	}

	public static void problem206() {
		long[] arr = new long[] {
		                         0, 9, 8, 7, 6, 5, 4, 3, 2, 1
		};
		loop: for (long i = 1010101010; i < 1389026623; i++) {
			long square = i * i;
			for (long a : arr) {
				if (square % 10 != a) {
					continue loop;
				}
				square /= 100;
			}
			System.out.println(i);
		}
	}

	public static void problem72() {
		long total = 0;
		int MAX = 1000000;

		// for(int y = 2; y <= MAX; y++) {
		// total += y - 1;
		// }
		//
		// System.out.println(total);

		BitSet bs = new BitSet(2);
		for (int y = 2; y <= MAX; y++) {
			bs.setLength(y - 1);
			bs.setAll();
			for (int x = 2; x < y; x++) {
				if (y % x == 0) {
					for (int z = x; z < y; z += x) {
						bs.reset(z);
					}
				}
			}
			total += bs.cardinality();
			if (y % 10000 == 0) {
				System.out.println(y + ": " + total);
			}
		}

		System.out.println(total);
	}

	public static void problem73() {
		long total = 0;
		Util.isPrime(1000000);
		for (int x = 1; x <= 10000; x++) {
			total += Util.nrOfRelativePrimes(x, x / 3 + 1, (x + 1) / 2);
		}

		System.out.println(total);
	}

	public static void problem74() {
		int total = 0;
		for (int x = 3; x <= 1000000; x++) {
			int l = Util.factLoopLength(x);
			if (l == 60) {
				total++;
			}
		}
		System.out.println(total);
	}

	public static void problem85() {
		long[] triangleNumbers = Util.generateTriangulars(10000);

		long closest = 0;
		for (int x = 0; x < triangleNumbers.length; x++) {
			for (int y = 0; y <= x; y++) {
				long nr = triangleNumbers[x] * triangleNumbers[y];
				if (Math.abs(2000000 - nr) < Math.abs(2000000 - closest)) {
					closest = nr;
					System.out.println(x + " " + y);
				}
				if (nr > 2000000) {
					break;
				}
			}
		}
	}

	public static void problem87() {
		final int MAX = 50000000;
		BitSet bs = new BitSet(MAX);
		BitSet primes = Util.calcPrimesUntil((int) Math.sqrt(MAX));

		int size = primes.cardinality();
		long[] x2 = new long[size];
		long[] x3 = new long[size];
		long[] x4 = new long[size];

		int index = 0;
		for (int x = 2; x < primes.getLength(); x++) {
			if (primes.isSet(x)) {
				x4[index] = x * (x3[index] = x * (x2[index] = x * x));
				index++;
			}
		}

		for (int i2 = 0; i2 < size; i2++) {
			for (int i3 = 0; i3 < size; i3++) {
				long x23 = x2[i2] + x3[i3];
				if (x23 > MAX) {
					break;
				}

				for (int i4 = 0; i4 < size; i4++) {
					long x234 = x23 + x4[i4];
					if (x234 > MAX) {
						break;
					}

					bs.set((int) x234);
				}
			}
		}

		System.out.println(bs.cardinality());
	}

	public static void problem92() {
		int total = 0;
		for (int i = 1; i < 10000000; i++) {
			int x = i;
			while (x != 1 && x != 89) {
				x = Util.squareOfDigits(x);
			}
			if (x == 89) {
				total++;
			}
		}
		System.out.println(total);
	}

	public static void problem97() {
		BigInteger two = BigInteger.valueOf(2);
		BigInteger mod = BigInteger.valueOf(10000000000l);
		BigInteger value = two;
		value = value.modPow(BigInteger.valueOf(7830457), mod);
		value = value.multiply(BigInteger.valueOf(28433));
		value = value.add(BigInteger.ONE);
		value = value.mod(mod);
		System.out.println(value);
	}

	public static void problem99() {
		try {
			List<int[]> numbers = Util.readNumberPairs(new File("Problem99.txt"));
			MathContext mc = new MathContext(12);
			BigDecimal max = BigDecimal.ZERO;
			int maxLine = 0;
			System.out.println(numbers.size());
			for (int i = 0; i < numbers.size(); i++) {
				int[] nrs = numbers.get(i);
				System.out.print(nrs[0] + "^" + nrs[1] + "=");
				BigDecimal x = BigDecimal.valueOf(nrs[0]).pow(nrs[1], mc);
				if (x.compareTo(max) > 0) {
					maxLine = i + 1;
					max = x;
				}
				System.out.println(x);
			}
			System.out.println(maxLine);
			System.out.println(max);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
