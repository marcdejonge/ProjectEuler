package euler.sequence;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractSequence implements Iterable<Long> {
	public static abstract interface Test {
		public boolean test(long value);
	}

	public AbstractSequence() {
		reset();
	}

	public abstract long current();

	public void dropWhile(Test test) {
		reset();
		long nr = next();
		while (test.test(nr)) {
			nr = next();
		}
	}

	public long get(int ix) {
		for (int i = 1; i < ix; i++) {
			next();
		}
		return next();
	}

	public long[] head(long until) {
		ArrayList<Long> list = new ArrayList<Long>();
		for (long nr = next(); nr < until; nr = next()) {
			list.add(nr);
		}

		long[] result = new long[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i).longValue();
		}

		return result;
	}
	
	public int[] head(int until) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (long nr = next(); nr < until; nr = next()) {
			list.add((int) nr);
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i).intValue();
		}
		
		return result;
	}

	public abstract long next();

	public abstract long position();

	public void reset() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This returns the first 100 numbers and ends with three dots. Remember that this tries to call
	 * {@link #next()} 100 times from the current point.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(100);
	}

	public String toString(int max) {
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < max; i++) {
			sb.append(next());
			sb.append(", ");
		}
		sb.append("...");
		return sb.toString();
	}
	
	@Override
	public Iterator<Long> iterator() {
		return new Iterator<Long>() {
			@Override
			public boolean hasNext() {
				return true; // There is always a next
			}
			
			@Override
			public Long next() {
				return AbstractSequence.this.next();
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
