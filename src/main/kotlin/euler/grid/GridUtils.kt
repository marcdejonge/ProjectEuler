package euler.grid

import euler.*
import kotlin.streams.*

typealias IntGrid = List<List<Int>>

fun readIntGrid(problemIndex: Int): IntGrid =
        findFile(problemIndex).bufferedReader().lines().map {
            it.split(" ").map(String::toInt)
        }.toList()

fun <T> IntGrid.mapEachLocation(block: (IntGrid, Int, Int) -> T): List<T> =
        this.mapIndexed { y, row ->
            row.mapIndexed { x, _ -> block(this, x, y) }
        }.flatten()

fun IntGrid.coord(x: Int, y: Int) =
        if (y < 0 || y >= size || x < 0 || x >= this[y].size) 0
        else this[y][x]