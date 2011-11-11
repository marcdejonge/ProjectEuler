package euler.sequence;

public class Primes extends AbstractSequence {
	public static class FactorCalculator {
		private static long[] factors = new long[128];

		private static int filled = 0;

		public final static long[] calc(long value, boolean distinct) {
			Primes primes = new Primes();
			FactorCalculator.filled = 0;

			for (long prime = primes.next(); value > 1; prime = primes.next()) {
				if (value % prime == 0) {
					value /= prime;
					FactorCalculator.factors[FactorCalculator.filled++] = prime;
					while (value % prime == 0) {
						value /= prime;
						if (!distinct) {
							FactorCalculator.factors[FactorCalculator.filled++] = prime;
						}
					}
				}
			}

			long[] result = new long[FactorCalculator.filled];
			System.arraycopy(FactorCalculator.factors, 0, FactorCalculator.factors, 0,
				FactorCalculator.filled);
			return result;
		}
	}
	
	private static class NumbersCalculator extends Thread {
		private volatile Numbers numbers; 

		NumbersCalculator() {
			super("NumbersCalculator");
		}
		
		void setNext(Numbers next) {
			synchronized(this) {
				if(this.numbers == null && next.next == null) {
					this.numbers = next;
					this.notify();
				}
			}
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					synchronized(this) {
						while(numbers == null) {
							this.wait();
						}
						numbers.next = new Numbers(numbers);
						currentMax = numbers.next.endNr;
						numbers = null;
					}
				} catch (InterruptedException e) {
					// Accept and continue
					interrupted();
				}
			}
		}
	}
	
	private static final NumbersCalculator NUMBERS_CALCULATOR = new NumbersCalculator();

	private static class Numbers {
		private final long[] bits;

		volatile Numbers next;

		final long startNr, endNr;

		private Numbers() {
			bits = new long[] {
				0x816d129a64b4cb6eL
			};
			startNr = 1;
			endNr = startNr + bits.length * 128;
			
			NUMBERS_CALCULATOR.start();
		}

		private Numbers(Numbers last) {
			startNr = last.endNr;
			bits = new long[Math.min(last.bits.length * 2, 131072)];
			for (int i = 0; i < bits.length; i++) {
				bits[i] = 0xffffffffffffffffl;
			}
			endNr = startNr + bits.length * 128;

			//System.out.print("Calculating primes until " + endNr + "...");
			Primes primes = new Primes();
			long prime = primes.next(); // Skip the 2
			while ((prime = primes.next()) * prime < endNr) {
				long mult = prime;
				long add = prime + prime;
				while (mult < endNr) {
					if (mult >= startNr) {
						int ix = (int) (mult - startNr);
						ix >>>= 1;
						bits[ix >>> 6] &= 0xffffffffffffffffL ^ (1L << ix);
					}
					mult += add;
				}
			}
			//System.out.println("done");
		}

		synchronized Numbers getNext() {
			while (next == null) {
				NUMBERS_CALCULATOR.setNext(this);
			}
			return next;
		}

		boolean isPrime(long nr) {
			if(next == null) {
				NUMBERS_CALCULATOR.setNext(this);
			}
			
			if ((nr & 1) == 0) {
				return nr == 2;
			} else if (nr < startNr) {
				return false;
			} else if (nr >= endNr) {
				return getNext().isPrime(nr);
			}

			int ix = (int) (nr - startNr);
			ix >>>= 1;

			return ((bits[ix >>> 6] >>> ix) & 1L) == 1L;
		}
	}

	private final static Numbers numbers = new Numbers();

	static long currentMax = numbers.endNr;

	public final static boolean isPrime(long nr) {
		if (nr < currentMax) {
			return numbers.isPrime(nr);
		} else {
			Primes ps = new Primes();
			for (long p = ps.next(); p * p <= nr; p = ps.next()) {
				if (nr % p == 0) {
					return false;
				}
			}
			return true;
		}
	}
	
	public final static int nrOfPrimeFactors(long nr, boolean distinct) {
		Primes p = new Primes();

		int cnt = 0;
		for (long prime = p.next(); prime * prime < nr; prime = p.next()) {
			if(nr % prime == 0) {
				cnt++;
				nr /= prime;
				
				while(nr % prime == 0) {
					if(!distinct) {
						cnt++;
					}
					nr /= prime;
				}
			}
		}
		
		if(nr > 1) {
			cnt++;
		}

		return cnt;
	}

	public final static int nrOfDivisors(long nr) {
		Primes p = new Primes();

		int cnt = 1;
		for (long prime = p.next(); prime * prime < nr; prime = p.next()) {
			int exp = 1;
			while (nr % prime == 0) {
				exp++;
				nr /= prime;
			}
			if (exp > 1) {
				cnt *= exp;
			}
		}

		return cnt;
	}
	
	public final static long phi(final long nr) {
		Primes primes = new Primes();

		long left = nr;
		long result = nr;
		for (long prime = primes.next(); prime * prime <= left; prime = primes.next()) {
			if (left % prime == 0) {
				result -= result / prime;
				left /= prime;

				while (left % prime == 0) {
					left /= prime;
				}
			}
		}

		if (left > 1) {
			result -= result / left;
		}

		return result;
	}

	/*public final static int phi(final int nr) {
		Primes p = new Primes();

		int left = nr;
		int result = nr;
		for (int prime = (int) p.next(); prime * prime < left; prime = (int) p.next()) {
			if (left % prime == 0) {
				result = (result - (result / prime));
				left /= prime;

				while (left % prime == 0) {
					left /= prime;
				}
			}
		}

		if (left > 1) {
			result = result - (result / left);
		}

		return result;
	}*/

	public final static long sumOfDivisors(long nr) {
		Primes p = new Primes();

		long sum = 1;
		for (long prime = p.next(); nr > 1 && prime * prime <= nr; prime = p.next()) {
			if (nr % prime == 0) {
				long j = prime;
				while (nr % prime == 0) {
					j *= prime;
					nr /= prime;
				}
				sum *= (j - 1);
				sum /= (prime - 1);
			}
		}
		if (nr > 1) {
			sum *= nr + 1;
		}
		return sum;
	}

	public final static long sumOfProperDivisors(long nr) {
		return Primes.sumOfDivisors(nr) - nr;
	}

	private int add, pos;

	private Numbers current;

	private long nr;

	public Primes() {
		reset();
	}

	public void copyFrom(Primes other) {
		current = other.current;
		nr = other.nr;
		pos = other.pos;
		add = other.add;
	}

	@Override
	public long current() {
		return nr;
	}

	@Override
	public long next() {
		if (nr == 1) {
			nr = 2;
		} else if (nr == 2) {
			nr = 3;
		} else if (nr == 3) {
			nr = 5;
		} else {
			while (true) {
				nr += add;
				add ^= 6;
				if (nr >= current.endNr) {
					current = current.getNext();
				} 
				if (current.isPrime(nr)) {
					break;
				}
			}
		}

		pos++;
		return nr;
	}

	@Override
	public int position() {
		return pos;
	}

	@Override
	public void reset() {
		current = Primes.numbers;
		nr = current.startNr;
		pos = 0;
		add = 2;
	}
}
