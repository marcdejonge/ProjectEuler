package euler.level2;

import java.math.BigInteger;

import euler.Problem;

public class Problem097 extends Problem<BigInteger> {
    private final static BigInteger MODULO = BigInteger.valueOf(10000000000l);
    private final static BigInteger TWO = BigInteger.valueOf(2);
    private final static BigInteger BASE = BigInteger.valueOf(28433);
    private final static BigInteger POWER = BigInteger.valueOf(7830457);

    @Override
    public BigInteger solve() {
        return BASE.multiply(TWO.modPow(POWER, MODULO)).add(BigInteger.ONE).mod(MODULO);
    }

}
