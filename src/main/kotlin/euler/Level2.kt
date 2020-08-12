package euler

fun problem75(): Int {
    val max = 1_500_000
    val nrs = IntArray(max + 1)

    pythagoreanTriplets().takeWhilePrev { prev, next ->
        prev.sum < next.sum || next.sum < max
    }.forEach {
        val sum = it.sum
        (sum..max step sum).forEach { ix -> nrs[ix]++ }
    }

    return nrs.count { it == 1 }
}
