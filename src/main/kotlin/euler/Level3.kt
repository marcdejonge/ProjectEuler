package euler

import euler.sequence.*
import java.util.concurrent.*

fun problem143(): Long {
    val until = 120_000L

    val sumsFound = ConcurrentHashMap<Long, Boolean>().keySet(true)
    val pairLookup = hashMapOf<Long, MutableList<Long>>()
    val allPairs = hashSetOf<kotlin.Pair<Long, Long>>()

    pythagorean120Triplets().takeWhilePrev { prev, next ->
        prev.sum < next.sum || next.first + next.second < until
    }.flatMap { (a, b, _) ->
        (1..(until / b)).asSequence().map { k -> k * a to k * b }
    }.forEach {
        pairLookup.computeIfAbsent(it.first) { arrayListOf() }.add(it.second)
        allPairs.add(it)
    }

    allPairs.parallelStream().forEach { (p, q) ->
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

fun problem146(): Long =
        (150_000_000L downTo 10 step 10).toList().parallelStream()
                .filter { n -> n % 3 != 0L && (n % 7 == 3L || n % 7 == 4L) && n % 13 != 0L }
                .filter { n ->
                    val n2 = n * n
                    val possiblePrimes = arrayOf(n2 + 1, n2 + 3, n2 + 7, n2 + 9, n2 + 13, n2 + 27)

                    primes().takeWhile { it * it < n2 }.all { prime ->
                        possiblePrimes.all { it % prime != 0L }
                    } && !Primes.isPrime(n2 + 21)
                }.debug().sum()
