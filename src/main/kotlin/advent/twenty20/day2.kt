package advent.twenty20

import euler.*
import java.util.*

fun main() {
    val lines = adventFile(2020, 2).readLines().map(::parseLine)
    println(lines.count { (spec, password) -> spec.isValid1(password) })
    println(lines.count { (spec, password) -> spec.isValid2(password) })
}

private fun parseLine(line: String): Pair<PasswordSpec, String> =
        with(StringTokenizer(line, " -:")) {
            PasswordSpec(nextToken().toInt(), nextToken().toInt(), nextToken().first()) to nextToken()
        }

data class PasswordSpec(
        val a: Int,
        val b: Int,
        val c: Char
) {
    fun isValid1(password: String) = password.count { it == c } in a..b

    fun isValid2(password: String) =
            (password.getOrNull(a - 1) == c) xor (password.getOrNull(b - 1) == c)
}
