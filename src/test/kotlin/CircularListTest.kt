import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CircularListTest {

    @Test
    fun moveToPosition() {
        val list = CircularList(listOf(1, 2, 3))
        list.moveToPosition(0)
        list.currentElement() shouldBe 1

        list.moveToPosition(2)
        list.currentElement() shouldBe 3

        shouldThrow<IndexOutOfBoundsException> {
            list.moveToPosition(3)
        }
    }

    @Test
    fun stepForward() {
        val list = CircularList(listOf(1, 2, 3))
        list.moveToPosition(0)
        list.stepForward(1)
        list.currentElement() shouldBe 2

        list.stepForward(1)
        list.currentElement() shouldBe 3

        list.stepForward(1)
        list.currentElement() shouldBe 1
    }

    @Test
    fun stepBack() {
        val list = CircularList(listOf(1, 2, 3))
        list.moveToPosition(2)
        list.stepBack(1)
        list.currentElement() shouldBe 2

        list.stepBack(1)
        list.currentElement() shouldBe 1

        list.stepBack(1)
        list.currentElement() shouldBe 3
    }

    /**
     *  The dial starts by pointing at 50.
     *  The dial is rotated L68 to point at 82.
     *  The dial is rotated L30 to point at 52.
     *  The dial is rotated R48 to point at 0.
     *  The dial is rotated L5 to point at 95.
     *  The dial is rotated R60 to point at 55.
     *  The dial is rotated L55 to point at 0.
     *  The dial is rotated L1 to point at 99.
     *  The dial is rotated L99 to point at 0.
     *  The dial is rotated R14 to point at 14.
     *  The dial is rotated L82 to point at 32.
     */
    @Test
    fun day01Test() {
        val list = CircularList((0..99).toList()).apply { moveToPosition(50) }
        list.stepBack(68) shouldBe 82
        list.stepBack(30) shouldBe 52
        list.stepForward(48) shouldBe 0
        list.stepBack(5) shouldBe 95
        list.stepForward(60) shouldBe 55
        list.stepBack(55) shouldBe 0
        list.stepBack(1) shouldBe 99
        list.stepBack(99) shouldBe 0
        list.stepForward(14) shouldBe 14
        list.stepBack(82) shouldBe 32
    }
}