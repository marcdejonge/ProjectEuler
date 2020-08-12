package euler

fun problem143(): Long {
    val until = 120_000L

    val sumsFound = hashSetOf<Long>()
    val pairLookup = hashMapOf<Long, MutableList<Long>>()
    val allPairs = hashSetOf<kotlin.Pair<Long, Long>>().toSortedSet(compareBy({ it.first }, { it.second }))

    pythagorean120Triplets().takeWhilePrev { prev, next ->
        prev.sum < next.sum || next.first + next.second < until
    }.flatMap { (a, b, _) ->
        (1..(until / b)).asSequence().map { k -> k * a to k * b }
    }.forEach {
        pairLookup.computeIfAbsent(it.first) { arrayListOf() }.add(it.second)
        allPairs.add(it)
    }

    allPairs.forEach { (p, q) ->
        pairLookup[p]?.forEach { r ->
            if (q < r && allPairs.contains(q to r)) {
                val sum = p + q + r
                if (sum <= until) {
                    debug { "sum = $sum, p = $p, q = $q, r = $r, a = ${(p * p + q * q + p * q).sqrt()}, b =  ${(p * p + r * r + p * r).sqrt()}, c = ${(q * q + r * r + q * r).sqrt()}" }
                    sumsFound += sum
                }
            }
        }
    }

    return sumsFound.sum()
}
