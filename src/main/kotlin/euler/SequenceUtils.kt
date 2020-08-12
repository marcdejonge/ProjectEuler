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
                        do {
                            left = leftIt.next()
                            rightIt = other(left).iterator()
                        } while (!rightIt.hasNext() && leftIt.hasNext())
                        rightIt.hasNext()
                    }
                    else -> false
                }

                override fun next(): R = combiner.invoke(left, rightIt.next())
            }
        }
    }
}

fun <T> Sequence<T>.takeWhilePrev(predicate: (T, T) -> Boolean): Sequence<T> = TakeWhilePrevSequence(this, predicate)

internal class TakeWhilePrevSequence<T>
constructor(
        private val sequence: Sequence<T>,
        private val predicate: (T, T) -> Boolean
) : Sequence<T> {
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        val iterator = sequence.iterator()
        var calculated = false
        var prevItem: T? = null
        var nextItem: T? = null

        private fun calcNext() {
            prevItem = nextItem
            if (iterator.hasNext()) {
                val nextItem = iterator.next()
                val prevItem = this.prevItem
                this.nextItem = if (prevItem == null || predicate(prevItem, nextItem)) nextItem else null
            } else {
                this.nextItem = null
            }
            calculated = true
        }

        override fun next(): T {
            if (!calculated)
                calcNext() // will change nextState

            val result = nextItem ?: throw NoSuchElementException()
            calculated = false
            return result
        }

        override fun hasNext(): Boolean {
            if (!calculated)
                calcNext() // will change nextState
            return nextItem != null
        }
    }
}
