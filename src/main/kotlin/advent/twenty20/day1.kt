package advent.twenty20

fun main() = Day1().execute()

class Day1 : Advent(2020, 1) {
    val nrs = parseLines(Integer::parseInt).toSortedSet()

    override fun part1() = nrs.first { nrs.contains(year - it) }.let { it * (year - it) }

    override fun part2() = nrs.flatMap { x -> nrs.map { y -> x to y } }
            .first { (x, y) -> nrs.contains(year - x - y) }
            .let { (x, y) -> x * y * (year - x - y) }
}
