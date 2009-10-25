package euler.level2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import euler.Problem;
import euler.numberic.BitSet;
import euler.sequence.Primes;

public class Problem060_ extends Problem<Integer> {
	private final static int MAX = 2560;
	
	private final static boolean check(final int v1, final int v2) {
		long mult1 = 10;
		while (v2 > mult1) {
			mult1 *= 10;
		}

		long mult2 = 10;
		while (v1 > mult2) {
			mult2 *= 10;
		}

		long p1 = v1 * mult1 + v2;
		long p2 = v1 + v2 * mult2;
		return Primes.isPrime(p1) && Primes.isPrime(p2);
	}
	
	public static void main(String[] args) {
		System.out.println(new Problem060_().solve());
	}

	@Override
	public Integer solve() {
		int minSum = Integer.MAX_VALUE;

		int[] primes = new int[MAX];
		{
			Primes ps = new Primes();
			for (int prime = (int) ps.next(), ix = 0; ix < primes.length; prime = (int) ps.next(), ix++) {
				primes[ix] = prime;
			}
		}
		
		HashMap<Integer, BitSet> couples = new HashMap<Integer, BitSet>(MAX);
		Set<BitSet> sets = new HashSet<BitSet>();

		for(int ix = 0; ix < primes.length; ix++) {
			final int prime = primes[ix];
			
			BitSet bs = new BitSet(MAX);
			
			for(int jx = 0; jx < ix; jx++) {
				final int prime2 = primes[jx];
				if(check(prime, prime2)) {
					bs.set(jx);
					couples.get(prime2).set(ix);
				}
			}
			
			couples.put(prime, bs);
		}
		
		return null;
	}
}
