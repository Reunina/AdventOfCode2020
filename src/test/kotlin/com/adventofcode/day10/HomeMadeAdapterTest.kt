package com.adventofcode.day10

import com.adventofcode.day10.HomeMadeAdapterAssert.Companion.assertThatHomeMadeAdapter
import com.adventofcode.day10.JoltDifferences.ONE_JOLT_DIFF
import com.adventofcode.day10.JoltDifferences.THREE_JOLT_DIFF
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HomeMadeAdapterTest {
    private val adapters = "16\n" +
            "10\n" +
            "15\n" +
            "5\n" +
            "1\n" +
            "11\n" +
            "7\n" +
            "19\n" +
            "6\n" +
            "12\n" +
            "4"

    @Test
    fun shouldRateDeviceAccordingToAdapters() {
        assertThatHomeMadeAdapter(HomeMadeAdapter(adapters))
                .isRatedTo(22)

        assertThatHomeMadeAdapter(HomeMadeAdapter("16\n10"))
                .isRatedTo(19)

    }


    @Test
    fun shouldFindDifferencesAfterJoiningAdapters() {
        assertThat(HomeMadeAdapter(adapters).joinAdapters())
                .hasSize(2)
                .containsEntry(ONE_JOLT_DIFF, 7)
                .containsEntry(THREE_JOLT_DIFF, 4)

    }


    @Test
    fun shouldReturnAllValidArrangementAsExpected() {

        val adapters = "16\n" +
                "10\n" +
                "15\n" +
                "5\n" +
                "1\n" +
                "11\n" +
                "7\n" +
                "19\n" +
                "6\n" +
                "12\n" +
                "4"

        assertThat(HomeMadeAdapter(adapters).findAllValidArrangements())
                .isEqualTo(8)


    }

}


class HomeMadeAdapterAssert(value: HomeMadeAdapter?) : AbstractObjectAssert<HomeMadeAdapterAssert, HomeMadeAdapter?>(value, HomeMadeAdapterAssert::class.java) {


    fun isRatedTo(expectedRate: Int): HomeMadeAdapterAssert {
        Assertions.assertThat(actual?.rateJolts).isEqualTo(expectedRate)
        return this
    }


    companion object {
        fun assertThatHomeMadeAdapter(value: HomeMadeAdapter): HomeMadeAdapterAssert {
            return HomeMadeAdapterAssert(value)
        }
    }

}