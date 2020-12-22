package com.adventofcode.day10

import com.adventofcode.day10.HomeMadeAdapterAssert.Companion.assertThatHomeMadeAdapter
import com.adventofcode.day10.JoltDifferences.*
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class HomeMadeAdapterTest {
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

    @Test
    fun shouldRateDeviceAccordingToAdapters() {
        assertTrue(HomeMadeAdapter(adapters).rateJolts() == 22)
    }


    @Test
    fun shouldFindDifferencesAfterJoiningAdapters() {
        assertThatHomeMadeAdapter(HomeMadeAdapter(adapters).joinAdapters())
                .has1JoltDifferencesAs(7)
                .has3JoltDifferencesAs(5)

    }

}


class HomeMadeAdapterAssert(value: HomeMadeAdapter?) : AbstractObjectAssert<HomeMadeAdapterAssert, HomeMadeAdapter?>(value, HomeMadeAdapterAssert::class.java) {

    fun has1JoltDifferencesAs(expected: Int): HomeMadeAdapterAssert {
        Assertions.assertThat(actual?.differences).containsEntry(ONE_JOLT_DIFF, expected)
        return this
    }

    fun has3JoltDifferencesAs(expected: Int): HomeMadeAdapterAssert {
        Assertions.assertThat(actual?.differences).containsEntry(THREE_JOLT_DIFF, expected)
        return this
    }


    companion object {
        fun assertThatHomeMadeAdapter(value: HomeMadeAdapter): HomeMadeAdapterAssert {
            return HomeMadeAdapterAssert(value)
        }
    }

}