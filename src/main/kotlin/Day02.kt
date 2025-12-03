import kotlin.io.path.Path
import kotlin.io.path.forEachLine
import kotlin.time.measureTime

/**
 * Range: 11-22  (11,12,13...22)
 *
 * 11, 22, 1010, 2020 invalid.
 */
fun findInvalidIds(rangeString: String): List<Long> {
    val split = rangeString.split("-")
    val range = split[0].toLong()..split[1].toLong()
    val result = mutableListOf<Long>()

    for (item in range) {
        val input = item.toString()
        var str = ""
        for(c in input.withIndex()) {
            str += c.value
            if (str == input.substring(c.index + 1)) {
                result.add(item)
                break
            }
        }
    }
    return result
}

fun countSequence(text: String, seq: String): Int {
    var count = 0
    var index = 0
    while (true) {
        index = text.indexOf(seq, index)
        if (index < 0) break
        count++
        index += seq.length // use index += seq.length for non-overlapping matches
    }
    return count
}

fun findInvalidIdsMultiple(rangeString: String): List<Long> {
    val split = rangeString.split("-")
    val range = split[0].toLong()..split[1].toLong()
    val result = mutableListOf<Long>()

    for (item in range) {
        val input = item.toString()
        var str = ""
        for(c in input.withIndex()) {
            str += c.value
            val count = countSequence(input, str)
            // at least 2 matches
            if (count > 1) {
                if (c.index == 0 && count == input.length) {
                    result.add(item)
                    break
                } else if (str.length > 1 && c.index < input.length / 2 && str.length * count == input.length) {
                    result.add(item)
                    break
                }
            }
        }
    }
    return result
}

fun part1() {
    Path("src/main/resources/day-02-input.txt").forEachLine { line ->
        measureTime {
            val sequences = line.trim().split(",")
            val answer = sequences.flatMap { findInvalidIds(it) }.sum()
            println("Total of invalid IDs: $answer")
        }.also { println("Part 1 took: $it") }
    }
}

fun part2() {
    Path("src/main/resources/day-02-input.txt").forEachLine { line ->
        measureTime {
            val sequences = line.trim().split(",")
            var total = 0L
            println("Total number of sequences: ${sequences.size}")
            sequences.forEach { sequence ->
                val findInvalidIds = findInvalidIdsMultiple(sequence)
                val sum = findInvalidIds.sum()
                total += sum
                println(
                    "Sequence: \t\t\t$sequence\n" +
                            "\tInvalidIDs: \t$findInvalidIds \n" +
                            "\tSum: \t\t\t$sum \n" +
                            "\tTotal: \t\t\t$total"
                )
            }

            println("Total of invalid IDs: $total")
        }.also { println("Part 2 took: $it") }
    }
}

fun main() {
    part1()
    part2()
}