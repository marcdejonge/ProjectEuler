package advent.twenty20

fun main() = Day2().execute()

class Day2 : Advent(2020, 2) {
    private val passwords = parseLines(" -:") {
        PasswordSpec(nextToken().toInt(), nextToken().toInt(), nextToken().first(), nextToken())
    }

    override fun part1() = passwords.count(PasswordSpec::isValid1)
    override fun part2() = passwords.count(PasswordSpec::isValid2)
}

data class PasswordSpec(
        val a: Int,
        val b: Int,
        val c: Char,
        val password: String
) {
    val isValid1 get() = password.count { it == c } in a..b
    val isValid2 get() = (password.getOrNull(a - 1) == c) xor (password.getOrNull(b - 1) == c)
}
