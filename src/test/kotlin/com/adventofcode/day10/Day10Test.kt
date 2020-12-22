package com.adventofcode.day10

import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun shouldReturnValuesAsGiven() {

        val adapters = "28\n" +
                "33\n" +
                "18\n" +
                "42\n" +
                "31\n" +
                "14\n" +
                "46\n" +
                "20\n" +
                "48\n" +
                "47\n" +
                "24\n" +
                "23\n" +
                "49\n" +
                "45\n" +
                "19\n" +
                "38\n" +
                "39\n" +
                "11\n" +
                "1\n" +
                "32\n" +
                "25\n" +
                "35\n" +
                "8\n" +
                "17\n" +
                "7\n" +
                "9\n" +
                "4\n" +
                "2\n" +
                "34\n" +
                "10\n" +
                "3"

        HomeMadeAdapterAssert.assertThatHomeMadeAdapter(HomeMadeAdapter(adapters).joinAdapters())
                .has1JoltDifferencesAs(22)
                .has3JoltDifferencesAs(10)
    }
}