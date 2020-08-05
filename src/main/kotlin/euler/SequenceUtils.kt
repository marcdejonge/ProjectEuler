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

inline fun <T1, T2, R> Iterable<T1>.combine(other: Iterable<T2>, crossinline combiner: (T1, T2) -> R): Sequence<R> =
        this.combine({ other }, combiner)

inline fun <T1, T2, R> Iterable<T1>.combine(crossinline other: (T1) -> Iterable<T2>, crossinline combiner: (T1, T2) -> R): Sequence<R> {
    return object : Sequence<R> {
        override fun iterator(): Iterator<R> {
            val leftIt = this@combine.iterator()

            if (!leftIt.hasNext())
                return emptyList<R>().iterator()

            return object : Iterator<R> {
                var left: T1 = leftIt.next()
                var rightIt: Iterator<T2> = other(left).iterator()

                override fun hasNext(): Boolean = when {
                    rightIt.hasNext() -> true
                    leftIt.hasNext() -> {
                        left = leftIt.next()
                        rightIt = other(left).iterator()
                        true
                    }
                    else -> false
                }

                override fun next(): R = combiner.invoke(left, rightIt.next())
            }
        }
    }
}
