import Direction.BACK
import Direction.FORWARD
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.Path
import kotlin.io.path.forEachLine

enum class Direction(private val direction: Char) {
    BACK('L'),
    FORWARD('R');

    companion object {
        fun from(direction: Char): Direction {
            return when (direction) {
                BACK.direction -> BACK
                FORWARD.direction -> FORWARD
                else -> throw IllegalArgumentException()
            }
        }
    }
}

/**
 * Process the input file and invoke the counter function for each line.
 */
fun inputIterator(count: (CircularList<Int>, Int, Direction, AtomicInteger) -> Unit): AtomicInteger {
    val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
    val password = AtomicInteger(0)

    Path("src/main/resources/day-01-input.txt").forEachLine { line ->
        val trimmed = line.trim()
        val steps = trimmed.substring(1).toInt()
        count(list, steps, Direction.from(trimmed.first()), password)
    }
    return password
}

/**
 * Count number of times we hit position 0 after the specified number of steps.
 */
fun countZeroAfterSteps(list: CircularList<Int>, steps: Int, direction: Direction, counter: AtomicInteger) {
    if (BACK == direction && list.stepBack(steps) == 0) counter.incrementAndGet()
    if (FORWARD == direction && list.stepForward(steps) == 0) counter.incrementAndGet()
}

/**
 * Count number of times we hit position 0 on each iteration of the number of steps.
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