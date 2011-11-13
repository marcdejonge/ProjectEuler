package euler.level4;

import java.math.BigInteger;

import euler.Problem;

public class Problem162 extends Problem<BigInteger> {

	@Override
	public BigInteger solve() {
		BigInteger total = BigInteger.ZERO;
		BigInteger SIXTEEN = BigInteger.valueOf(16), FIFTEEN = BigInteger.valueOf(15), FOURTEEN = BigInteger.valueOf(14), THIRTEEN = BigInteger.valueOf(13);
		BigInteger alles = FIFTEEN;
		BigInteger zonder1 = FIFTEEN.add(FOURTEEN).add(FOURTEEN);
		BigInteger zonder2 = FOURTEEN.add(FOURTEEN).add(THIRTEEN);
		BigInteger zonder3 = THIRTEEN;
		for (int i = 1; i <= 16; i++) {
			total = total.add(alles.subtract(zonder1).add(zonder2).subtract(zonder3));
			alles = alles.multiply(SIXTEEN);
			zonder1 = zonder1.multiply(FIFTEEN);
			zonder2 = zonder2.multiply(FOURTEEN);
			zonder3 = zonder3.multiply(THIRTEEN);
		}
		return total;
	}

}
