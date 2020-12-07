package advent.twenty20

import java.io.*
import java.util.*
import kotlin.streams.*

open class Advent(val year: Int, private val day: Int) {
    private val fileName = "input/" + File.separator + "advent$year" + File.separator + "$day.txt"
    private val file = File(fileName)

    val reader: BufferedReader
        get() = BufferedReader(FileReader(file))

    val lines by lazy { reader.use { it.lines().toList() } }

    fun <T> parseLines(parseFunction: (String) -> T): List<T> =
            reader.useLines { it.map(parseFunction).toList() }

    fun <T> parseLines(delim: String, parseFunction: StringTokenizer.()-> T): List<T> =
            parseLines {
                with(StringTokenizer(it, delim)) {
                    parseFunction(this)
                }
            }

    fun execute() {
        println("Running day $day of year $year")
        println("Part 1: ${part1()}")
        println("Part 2: ${part2()}")
    }

    open fun part1(): Number = 0

    open fun part2(): Number = 0
}
