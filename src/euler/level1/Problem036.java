package euler.level1;

import euler.Problem;
import euler.numberic.Number;
import euler.sequence.Sequence;

class Base2Palindromes extends Sequence {
	private long nr, last;

	private boolean odd;

	private int pos, limit;

	public Base2Palindromes() {
		reset();
	}

	@Override
	public long current() {
		long nr = this.nr;
		long res = this.nr;
		if (odd) {
			nr >>>= 1;
		}
		while (nr > 0) {
			res = (res << 1) + (nr & 1);
			nr >>>= 1;
		}
		return res;
	}

	@Override
	public long next() {
		pos++;

		nr++;
		if ((nr & limit) != 0) {
			if (odd) {
				odd = false;
				nr = last;
			} else {
				limit <<= 1;
				odd = true;
				last = nr;
			}
		}
		return current();
	}

	@Override
	public int position() {
		return pos;
	}

	@Override
	public void reset() {
		nr = 0;
		last = 1;
		limit = 2;
		pos = 0;
		odd = true;
	}
}

public class Problem036 extends Problem<Long> {
	@Override
	public Long solve() {
		long total = 0;
		Base2Palindromes palindromes = new Base2Palindromes();
		for (long p = palindromes.next(); p < 1000000; p = palindromes.next()) {
			if (Number.valueOf(p).isPalindrome()) {
				total += p;
			}
		}
		return total;
	}
}
