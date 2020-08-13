package euler

import java.util.stream.*

fun Stream<Long>.sum(): Long = collect(Collectors.summingLong { it })