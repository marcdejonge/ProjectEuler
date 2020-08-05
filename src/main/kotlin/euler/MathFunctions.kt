package euler

import java.math.*

fun Int.factorial(): BigInteger {
    var fact = BigInteger.ONE
    for (i in 2..this) {
        fact *= BigInteger.valueOf(i.toLong())
    }
    return fact
}

fun Int.pow(exponent: Int): BigInteger = BigInteger.valueOf(toLong()).pow(exponent)

fun Long.pow(exponent: Int): BigInteger = BigInteger.valueOf(this).pow(exponent)
