import io.kotest.matchers.shouldBe
import kotlin.test.Test

class Day02Test {

    /**
     * 11-22 has two invalid IDs, 11 and 22.
     * 95-115 has one invalid ID, 99.
     * 998-1012 has one invalid ID, 1010.
     * 1188511880-1188511890 has one invalid ID, 1188511885.
     * 222220-222224 has one invalid ID, 222222.
     * 1698522-1698528 contains no invalid IDs.
     * 446443-446449 has one invalid ID, 446446.
     * 38593856-38593862 has one invalid ID, 38593859.
     * The rest of the ranges contain no invalid IDs.
     * Adding up all the invalid IDs in this example produces 1227775554.
     */
    @Test
    fun testPart1() {
        val total = mutableListOf<Long>()

        findInvalidIds("11-22") shouldBe listOf<Long>(11, 22).also { total += it }
        findInvalidIds("95-115") shouldBe listOf<Long>(99).also { total += it }
        findInvalidIds("998-1012") shouldBe listOf<Long>(1010).also { total += it }
        findInvalidIds("1188511880-1188511890") shouldBe listOf<Long>(1188511885).also { total += it }
        findInvalidIds("222220-222224") shouldBe listOf<Long>(222222).also { total += it }
        findInvalidIds("1698522-1698528") shouldBe listOf<Long>()
        findInvalidIds("446443-446449") shouldBe listOf<Long>(446446).also { total += it }
        findInvalidIds("38593856-38593862") shouldBe listOf<Long>(38593859).also { total += it }

        total.sum() shouldBe 1227775554
    }

    /**
     * From the same example as before:
     *
     * 11-22 still has two invalid IDs, 11 and 22.
     * 95-115 now has two invalid IDs, 99 and 111.
     * 998-1012 now has two invalid IDs, 999 and 1010.
     * 1188511880-1188511890 still has one invalid ID, 1188511885.
     * 222220-222224 still has one invalid ID, 222222.
     * 1698522-1698528 still contains no invalid IDs.
     * 446443-446449 still has one invalid ID, 446446.
     * 38593856-38593862 still has one invalid ID, 38593859.
     * 565653-565659 now has one invalid ID, 565656.
     * 824824821-824824827 now has one invalid ID, 824824824.
     * 2121212118-2121212124 now has one invalid ID, 2121212121.
     * Adding up all the invalid IDs in this example produces 4174379265
     */
    @Test
    fun testPart2() {
        val total = mutableListOf<Long>()
        findInvalidIdsMultiple("1-10") shouldBe listOf<Long>().also { total += it }
        findInvalidIdsMultiple("11-22") shouldBe listOf<Long>(11, 22).also { total += it }
        findInvalidIdsMultiple("95-115") shouldBe listOf<Long>(99, 111).also { total += it }
        findInvalidIdsMultiple("998-1012") shouldBe listOf<Long>(999, 1010).also { total += it }
        findInvalidIdsMultiple("1188511880-1188511890") shouldBe listOf<Long>(1188511885).also { total += it }
        findInvalidIdsMultiple("222220-222224") shouldBe listOf<Long>(222222).also { total += it }
        findInvalidIdsMultiple("1698522-1698528") shouldBe listOf<Long>().also { total += it }
        findInvalidIdsMultiple("446443-446449") shouldBe listOf<Long>(446446).also { total += it }
        findInvalidIdsMultiple("38593856-38593862") shouldBe listOf<Long>(38593859).also { total += it }
        findInvalidIdsMultiple("565653-565659") shouldBe listOf<Long>(565656).also { total += it }
        findInvalidIdsMultiple("824824821-824824827") shouldBe listOf<Long>(824824824).also { total += it }
        findInvalidIdsMultiple("2121212118-2121212124") shouldBe listOf<Long>(2121212121).also { total += it }

        total.sum() shouldBe 4174379265
    }
}