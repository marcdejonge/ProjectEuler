/**
 * 
 */
package euler.sequence;


public class SquareDigits extends AbstractSequence {
	private final int n;

	private int sqN, a, num, den, pos;

	public SquareDigits(int n) {
		this.n = n;
		reset();
	}

	@Override
	public void reset() {
		this.pos = 0;
		this.sqN = (int) Math.floor(Math.sqrt(this.n));
		this.a = 0;
		this.num = this.n;
		this.den = 0;
	}

	@Override
	public long current() {
		return a;
	}

	@Override
	public long next() {
		int num = (this.n - (this.den * this.den)) / this.num;
		if (num == 0) {
			this.a = 0;
			this.pos++;
		} else {
			int sum = this.sqN + this.den;
			int a = sum / num;
			int den = -(this.den - (a * num));

			this.pos++;
			this.a = a;
			this.num = num;
			this.den = den;
		}

		return current();
	}
	
	public int getSquareN() {
		return sqN;
	}

	@Override
	public int position() {
		return pos;
	}

	public boolean equalPoint(SquareDigits other) {
		return this.n == other.n && this.a == other.a && this.num == other.num
				&& this.den == other.den;
	}
}