package com.adventofcode.day09

import com.adventofcode.day09.XmasCipherDecoderAssert.Companion.assertThatXmasCipherDecoder
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class XmasCipherDecoderTest {

    @Test
    fun shouldValidateNumberWhenFindingTwoDistinctValuesInto25PreviousNumbers() {
        val simplePreamble = 1.rangeTo(25).toList()
        val decoder = XmasCipherDecoder(simplePreamble)

        assertThatXmasCipherDecoder(decoder)
                .validates(26)
                .validates(49)
                .inValidates(100)
                .inValidates(50)

    }

    @Test
    fun shouldStillValidateNumberWhenChangingPreamble() {

        val customPreamble = listOf(20) + 2.rangeTo(19).toList() + 21.rangeTo(25)

        val decoder = XmasCipherDecoder(customPreamble)

        decoder.read(45)


        assertThatXmasCipherDecoder(decoder)
                .hasPreambleAs(customPreamble - listOf(20) + listOf(45))
                .validates(26)
                .inValidates(65)
                .validates(64)
                .validates(66)

    }


    private val exampleInput: String = "35\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "47\n" +
            "40\n" +
            "62\n" +
            "55\n" +
            "65\n" +
            "95\n" +
            "102\n" +
            "117\n" +
            "150\n" +
            "182\n" +
            "127\n" +
            "219\n" +
            "299\n" +
            "277\n" +
            "309\n" +
            "576"

    @Test
    fun shouldProcessInputContainingPreamble() {

        val decoderWithPreambleOf5 = XmasCipherDecoder.readFrom(exampleInput, 5)

        assertThatXmasCipherDecoder(decoderWithPreambleOf5)
                .hasPreambleAs(listOf(35, 20, 15, 25, 47))


        val decoderWithPreambleOf4 = XmasCipherDecoder.readFrom(exampleInput, 4)

        assertThatXmasCipherDecoder(decoderWithPreambleOf4)
                .hasPreambleAs(listOf(35, 20, 15, 25))

    }


    @Test
    fun shouldFindFirstInvalidCodeFromInput() {

        val decoderWithExampleInput = XmasCipherDecoder
                .readFrom(exampleInput, 5)


        assertThatXmasCipherDecoder(decoderWithExampleInput)
                .findFirstNonValidDataAs(127L)

    }

    @Test
    fun shouldHandleNonIntegerCodes() {

        val decoderWithConsequentCodesWithOneInvalid = XmasCipherDecoder
                .readFrom("1\n" +
                        "2\n" +
                        "63939575729959\n" +
                        "63939575729960"
                        , 2)


        assertThatXmasCipherDecoder(decoderWithConsequentCodesWithOneInvalid)
                .findFirstNonValidDataAs(63939575729959)


        val decoderWithConsequentCodesWithAllValid = XmasCipherDecoder
                .readFrom("1\n" +
                        "63939575729959\n" +
                        "63939575729960", 2)


        assertThatXmasCipherDecoder(decoderWithConsequentCodesWithAllValid)
                .hasNoInvalidCode()


    }

    @Test
    fun shouldHIgnoreEmptyLinesInInput() {

        val decoderWithConsequentCodesWithOneInvalid = XmasCipherDecoder
                .readFrom("1\n" +
                        "2\n" +
                        "63939575729959\n" +
                        "63939575729960\n"
                        , 2)


        assertThatXmasCipherDecoder(decoderWithConsequentCodesWithOneInvalid)
                .findFirstNonValidDataAs(63939575729959)

    }

    @Test
    fun shouldFindTheEncryptionWeakness() {


        val decoderWithExampleInput = XmasCipherDecoder
                .readFrom(exampleInput, 5)


        assertThatXmasCipherDecoder(decoderWithExampleInput)
                .findFirstNonValidDataAs(127L)


        assertThatXmasCipherDecoder(decoderWithExampleInput)
                .findEncryptionWeaknessAs(62L)


        //    in this example, these are 15 and 47, producing 62.
    }

}

class XmasCipherDecoderAssert(value: XmasCipherDecoder?) : AbstractObjectAssert<XmasCipherDecoderAssert, XmasCipherDecoder?>(value, XmasCipherDecoderAssert::class.java) {

    fun validates(expected: Int): XmasCipherDecoderAssert {
        Assertions.assertThat(actual?.validate(expected))
                .`as`("expecting $expected should be valid")
                .isTrue()
        return this
    }

    fun inValidates(expected: Int): XmasCipherDecoderAssert {
        Assertions.assertThat(actual?.validate(expected))
                .`as`("expecting $expected should not be valid")
                .isFalse()
        return this
    }

    fun hasPreambleAs(expectedPreamble: List<Int>): XmasCipherDecoderAssert {

        Assertions.assertThat(actual?.preamble)
                .`as`("expecting preamble to be as:\n$expectedPreamble")
                .containsExactlyElementsOf(expectedPreamble.map { it.toLong() })
        return this
    }

    fun findFirstNonValidDataAs(expectedFirstInvalidCode: Long): XmasCipherDecoderAssert {
        assertThat(actual?.findFirstInvalidCode()).isEqualTo(expectedFirstInvalidCode)
        return this
    }

    fun hasNoInvalidCode(): XmasCipherDecoderAssert {
        assertThat(actual?.findFirstInvalidCode()).isEqualTo(null)
        return this
    }

    fun findEncryptionWeaknessAs(expectedWeakness: Code): XmasCipherDecoderAssert {
        assertThat(actual?.findEncryptionWeakness()).isEqualTo(expectedWeakness)
        return this
    }


    companion object {
        fun assertThatXmasCipherDecoder(value: XmasCipherDecoder): XmasCipherDecoderAssert {
            return XmasCipherDecoderAssert(value)
        }
    }

}