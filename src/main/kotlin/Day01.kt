import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

/**
 * Process the input file and invoke the counter function for each line.
 */
fun inputIterator(count: (CircularList<Int>, Int, Direction, AtomicInteger) -> Unit): AtomicInteger {
    val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
    val password = AtomicInteger(0)

    Path("src/main/resources/day-01-input.txt").forEachLine { line ->
        val trimmed = line.trim()
        val steps = trimmed.substring(1).toInt()
        val direction = Direction.from(trimmed.first())
        count(list, steps, direction, password)
    }
    return password
}

/**
 * Count number of times we hit position 0 after the specified number of steps.
 */
fun countZeroAfterSteps(list: CircularList<Int>, steps: Int, direction: Direction, counter: AtomicInteger) {
    if (list.step(direction, steps) == 0) counter.incrementAndGet()
}

/**
 * Count number of times we hit position 0 on each step in the iteration.
 */
fun countZeroAfterEachStep(list: CircularList<Int>, steps: Int, direction: Direction, counter: AtomicInteger) {
    repeat(steps) {
        countZeroAfterSteps(list, steps = 1, direction = direction, counter = counter)
    }
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