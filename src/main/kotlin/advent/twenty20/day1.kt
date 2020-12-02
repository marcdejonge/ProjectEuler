package advent.twenty20

import euler.*
import java.lang.Integer.*

const val SUM = 2020

fun main() {
    val nrs = adventFile(2020, 1).readLines().map(::parseInt).toSortedSet()

    nrs.first { nrs.contains(SUM - it) }.let { println(it * (SUM - it)) }

    nrs.combine(nrs).first { (x, y) -> nrs.contains(SUM - x - y) }.let { (x, y) ->
        println(x * y * (SUM - x - y))
    }
}
