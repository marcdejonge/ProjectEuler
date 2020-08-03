package euler

import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun findFile(problemIndex: Int): File {
    val filename = "input" + File.separator + String.format("Problem%03d.txt", problemIndex)
    val file = File(filename)
    return if (file.exists() && file.canRead()) {
        file
    } else {
        throw IOException("Can not read file [$filename]")
    }
}

