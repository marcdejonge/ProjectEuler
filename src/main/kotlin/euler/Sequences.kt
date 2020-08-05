package euler

import kotlin.sequences.*

fun collatzSeq(seed: Long) = generateSequence(seed) {
    when {
        it == 1L -> null
        it and 1 == 0L -> it / 2
        else -> 3 * it + 1
    }
}

fun fibonacci() = generateSequence(1L to 1L) {
    it.second to (it.first + it.second)
}.map { it.second }

fun primes(): Sequence<Long> = euler.sequence.Primes()

fun naturalNumbers() = generateSequence(1L) { it + 1 }

fun triangleNumbers() = naturalNumbers().map { (it * (it + 1)) / 2 }
