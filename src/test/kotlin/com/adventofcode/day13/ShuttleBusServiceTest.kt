package com.adventofcode.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class ShuttleBusServiceTest {

    @Test
    fun shouldFindEarliestTimeStampWithOnyOneBus() {
        assertThat(
                ShuttleBusService(939L, 7).findEarliestDeparture()
        ).isEqualTo(7 to 945L)

    }

    @Test
    fun shouldFindEarliestTimeStampWithOnyTwoBus() {
        assertThat(
                ShuttleBusService(939L, 7, 13).findEarliestDeparture()
        ).isEqualTo(7 to 945L)

    }

    @Test
    fun shouldFindEarliestTimeStampWithOnyThreeBus() {
        assertThat(
                ShuttleBusService(939L, 7, 13, 59).findEarliestDeparture()
        ).isEqualTo(59 to 944L)

    }

    @Test
    fun shouldFindEarliestTimeStampWithCompleteExample() {
        assertThat(
                ShuttleBusService(939L, 7, 13, 59, 31, 19).findEarliestDeparture()
        ).isEqualTo(59 to 944L)
    }


    @Test
    fun shouldFindEarliestTimeStampFromExampleInput() {
        assertThat(
                ShuttleBusService.readFrom("939",
                        "7,13,x,x,59,x,31,19")
        ).isEqualToComparingFieldByFieldRecursively(
                ShuttleBusService(939L, 7, 13, 59, 31, 19))

    }

    @Test
    fun shouldReturnWeirdCalculus() {
        assertThat(
                ShuttleBusService(939L, 7, 13, 59, 31, 19).findEarliestIdByWaitingTime()
        ).isEqualTo(295L)

    }


    @TestFactory
    fun shouldFindEarliestTimeStampThatMatchesWithTheBugsDeparturePosition() = listOf(
            arrayOf("7", "13", "x", "x", "59", "x", "31", "19") to 1068781L,
            arrayOf("17", "x", "13", "19") to 3417L,
            arrayOf("67", "7", "59", "61") to 754018L,
            arrayOf("67", "x", "7", "59", "61") to 779210L,
            arrayOf("67", "7", "x", "59", "61") to 1261476L,
            arrayOf("1789", "37", "47", "1889") to 1202161486L
    )
            .map { (busIds, expectedResult) ->
                DynamicTest.dynamicTest("should computed to : $expectedResult") {
                    assertThat(
                            ShuttleBusServicePart02(*busIds).findEarliestTimeStampThatMatchesWithTheBusDeparturePosition()
                    ).isEqualTo(expectedResult)
                }

            }
}


        