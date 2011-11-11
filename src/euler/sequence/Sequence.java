package euler.sequence;

import java.math.BigInteger;

import euler.numberic.Number;

/**
 * A {@link Sequence} is a series of number that can be read one at a time. The
 * {@link Sequence} does not have to have a memory, so only the next number can
 * be queries each time using the {@link #next()} method. The type of
 * {@link java.lang.Number} can differ.
 * 
 * @author Marc de Jonge
 * 
 * @param <T>
 *            The type of number that this method will return. Many will just
 *            use longs for speed, but the {@link BigInteger} or {@link Number}
 *            can also be used when in need of very large results.
 */
public interface Sequence<T extends java.lang.Number> {
	/**
	 * Returns the next number in this sequence. Depending on the type of
	 * sequence, this probably needs some calculating and there are no
	 * guarantees this will return within a certain amount of time. This method
	 * may return <code>null</code> when the sequence is finished, but some
	 * sequences may never end (e.g. prime numbers).
	 * 
	 * @return The next number in this sequence or <code>null</code> when there
	 *         are no more numbers.
	 */
	T next();

	/**
	 * Should always return the number of times the {@link #next()} method has
	 * been called. When more than {@link Integer#MAX_VALUE} call have been
	 * made, this method can overflow and negative numbers may be returned. This
	 * method should not be used for such long sequences.
	 * 
	 * @return The index of the current number in the sequence.
	 */
	int position();
}
