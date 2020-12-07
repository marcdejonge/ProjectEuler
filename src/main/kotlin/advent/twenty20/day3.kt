package advent.twenty20

fun main() = Day3().execute()

class Day3 : Advent(2020, 3) {
    override fun part1(): Number = calc(1, 3)

    override fun part2() =
            calc(1, 1).toLong() * calc(1, 3) * calc(1, 5) * calc(1, 7) * calc(2, 1)


    private fun calc(downStep: Int, rightStep: Int) =
            lines.withIndex().count { (ix, trees) ->
                ix % downStep == 0 && trees[((ix / downStep) * rightStep) % trees.length] == '#'
            }
}
