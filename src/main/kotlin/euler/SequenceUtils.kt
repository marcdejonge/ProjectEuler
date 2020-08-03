package euler

val Sequence<*>.length: Long
    get() {
        val it = this.iterator()
        var count = 0L
        while (it.hasNext()) {
            it.next()
            count++
        }
        return count
    }

val <T> Sequence<T>?.first: T?
    get() = this?.iterator()?.next()

fun collatzSeq(seed: Long) = generateSequence(seed) {
    when {
        it == 1L -> null
        it and 1 == 0L -> it / 2
        else -> 3 * it + 1
    }
}
