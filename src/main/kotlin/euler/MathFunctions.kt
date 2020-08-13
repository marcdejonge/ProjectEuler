package euler

import java.math.*
import kotlin.math.*

fun Int.factorial(): BigInteger {
    var fact = BigInteger.ONE
    for (i in 2..this) {
        fact *= BigInteger.valueOf(i.toLong())
    }
    return fact
}

fun Int.pow(exponent: Int): BigInteger = BigInteger.valueOf(toLong()).pow(exponent)

fun Long.pow(exponent: Int): BigInteger = BigInteger.valueOf(this).pow(exponent)

fun Int.square() = this * this
fun Int.sqrt() = sqrt(toDouble()).toInt()
fun Int.isSquare() = sqrt().square() == this

fun Long.square() = this * this
fun Long.sqrt() = sqrt(toDouble()).toLong()
fun Long.isSquare() = sqrt().square() == this

infix fun Int.gcd(other: Int): Int = if (other == 0) this else other gcd (this % other)
infix fun Long.gcd(other: Long): Long = if (other == 0L) this else other gcd (this % other)

fun Long.primeFactors() = sequence {
    var nr: Long = this@primeFactors

    for (prime in primes()) {
        if (prime * prime > nr) break
        while (nr % prime == 0L) {
            nr /= prime
            yield(prime)
        }
    }

    if (nr > 1) yield(nr)
}
