package euler

import euler.grid.*
import euler.sequence.*
import kotlin.streams.*

fun problem1() = (1..999).filter { it % 3 == 0 || it % 5 == 0 }.sum()

fun problem2() = Fibionacci().takeWhile { it < 4000000 }.filter { it % 2 == 0L }.sum()

fun problem3(): Int {
    var value = 600851475143L

    return generateSequence(3) { it + 2 }.first {
        while (value % it == 0L) {
            value /= it
        }
        value == 1L
    }
}

fun problem4(): Int? {
    for (x in 9 downTo 0) {
        for (y in 9 downTo 0) {
            for (z in 9 downTo 0) {
                val nr = x * 100001 + y * 10010 + z * 1100
                for (div in 999 downTo 101) {
                    if (nr % div == 0) {
                        val mul = nr / div
                        if (mul < 1000) {
                            return if (mul < 100) {
                                break
                            } else {
                                nr
                            }
                        }
                    }
                }
            }
        }
    }

    return null
}

fun problem5() = 2 * 2 * 2 * 2 * 3 * 3 * 5 * 7 * 11 * 13 * 17 * 19

fun problem6() = (1L..100L).sum().let { it * it } - (1L..100L).map { it * it }.sum()

fun problem7() = Primes()[10001]

fun problem8() = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450"
        .chars().map { it - '0'.toInt() }.toList().windowed(13).map {
            it.fold(1L) { a, b -> a * b }
        }.max()

fun problem9(): Int? {
    for (c in 1..999) {
        for (b in 1 until c) {
            val a = 1000 - b - c
            if (a in 1 until b && a * a + b * b == c * c) {
                return a * b * c
            }
        }
    }
    return null
}

fun problem10() = Primes().stream().takeWhile { it < 2_000_000 }.sum()

fun problem11() =
    readIntGrid(11).mapEachLocation { grid, x, y ->
        (-1..1).flatMap { dx ->
            (-1..1).map { dy ->
                if (dx == 0 && dy == 0) 0
                else grid.coord(x, y) *
                        grid.coord(x + dx, y + dy) *
                        grid.coord(x + 2 * dx, y + 2 * dy) *
                        grid.coord(x + 3 * dx, y + 3 * dy)
            }
        }.max() ?: 0
    }.max()
