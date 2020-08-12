package euler

fun collatzSeq(seed: Long) = generateSequence(seed) {
    when {
        it == 1L -> null
        it and 1 == 0L -> it / 2
        else -> 3 * it + 1
    }
}

fun fibonacci() = generateSequence(1L to 1L) {
    it.second to (it.first + it.second)
}.map { it.first }

fun primes(): Sequence<Long> = euler.sequence.Primes()

fun naturalNumbers() = generateSequence(1L) { it + 1 }

fun triangleNumbers() = naturalNumbers().map { (it * (it + 1)) / 2 }

fun pythagoreanBasePairs() = generateSequence(2 to 1) { (v, w) ->
    if (w == 1) {
        val next = (v / 2) + 1
        next + (v % 2) to next
    } else {
        v + 1 to w - 1
    }
}.filter { (v, w) ->
    v gcd w == 1
}

fun pythagoreanTriplets() = pythagoreanBasePairs().filter { (v, w) -> (v - w) % 2 != 0 }.map { (v, w) ->
    val v2 = v * v
    val w2 = w * w
    val a = 2 * v * w
    val b = v2 - w2
    val c = v2 + w2
    if (a < b) Triple(a, b, c) else Triple(b, a, c)
}

fun pythagorean120Triplets() = pythagoreanBasePairs().filter { (v, w) -> (v - w) % 3 != 0 }.map { (v, w) ->
    val a = 2 * v * w + w * w
    val b = v * v - w * w
    val c = v * v + w * w + v * w
    if (a < b) Triple(a, b, c) else Triple(b, a, c)
}

val Triple<Int, Int, Int>.sum
    get() = first + second + third
