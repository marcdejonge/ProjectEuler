package euler

import java.util.stream.*

private var debugIsEnabled = false
fun enableDebugMode() {
    debugIsEnabled = true
}

fun debug(lineProducer: () -> String) {
    if (debugIsEnabled) {
        println(lineProducer())
    }
}

fun <T> Sequence<T>.debug(block: (T) -> String = { it.toString() }) =
        if (debugIsEnabled) {
            this.map {
                println(block(it))
                it
            }
        } else {
            this
        }

fun <T> Stream<T>.debug(block: (T) -> String = { it.toString() }) =
        if (debugIsEnabled) {
            this.map {
                println(block(it))
                it
            }
        } else {
            this
        }