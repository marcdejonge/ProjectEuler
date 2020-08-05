package euler

import java.math.*

fun BigInteger.digitalSum() = toString().map { it - '0' }.sum()