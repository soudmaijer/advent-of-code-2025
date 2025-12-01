import kotlin.io.path.Path
import kotlin.io.path.forEachLine

typealias Steps = Int

/**
 * Process the input file and invoke the counter function for each line.
 */
fun inputIterator(count: (CircularList<Int>, Steps, Direction) -> Int): Int {
    val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
    var counter = 0

    Path("src/main/resources/day-01-input.txt").forEachLine { line ->
        val trimmed = line.trim()
        val steps = trimmed.substring(1).toInt()
        val direction = Direction.from(trimmed.first())
        counter += count(list, steps, direction)
    }
    return counter
}

/**
 * Count number of times we hit position 0 after the specified number of steps in the given direction.
 */
fun countZeroAfterSteps(list: CircularList<Int>, steps: Steps, direction: Direction): Int {
    return if (list.step(direction, steps) == 0) 1 else 0
}

/**
 * Count number of times we hit position 0 on each step in the iteration in the given direction.
 */
fun countZeroAfterEachStep(list: CircularList<Int>, steps: Steps, direction: Direction): Int {
    var count = 0
    repeat(steps) {
        count += countZeroAfterSteps(list, steps = 1, direction = direction)
    }
    return count
}

fun day1a() {
    val password = inputIterator(::countZeroAfterSteps)
    println("Part 1 - Password: $password")
}

fun day1b() {
    val password = inputIterator(::countZeroAfterEachStep)
    println("Part 2 - Password: $password")
}

fun main() {
    day1a()
    day1b()
}