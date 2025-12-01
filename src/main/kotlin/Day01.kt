import kotlin.io.path.Path
import kotlin.io.path.forEachLine

fun part1() {
    val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
    var password = 0

    Path("src/main/resources/day-01-input.txt").forEachLine { line ->
        val trimmed = line.trim()
        val steps = trimmed.substring(1).toInt()
        when(trimmed.first()) {
            'L' -> list.stepBack(steps)
            'R' -> list.stepForward(steps)
            else -> throw IllegalArgumentException("Invalid step $trimmed")
        }
        if(list.currentPosition() == 0) password++
    }

    println("Part 1 - Password: $password")
}

fun part2() {
    val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
    var password = 0

    Path("src/main/resources/day-01-input.txt").forEachLine { line ->
        val trimmed = line.trim()
        var steps = trimmed.substring(1).toInt()
        when(trimmed.first()) {
            'L' -> {
                while (steps-- > 0) {
                    if(list.stepBack() == 0) password++ // stepBack() returns current position
                }
            }
            'R' -> {
                while (steps-- > 0) {
                    if(list.stepForward() == 0) password++ // stepForward() returns current position
                }
            }
            else -> throw IllegalArgumentException("Invalid step $trimmed")
        }
    }

    println("Part 2 - Password: $password")
}

fun main() {
    part1()
    part2()
}