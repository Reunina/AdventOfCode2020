package com.adventofcode.day13.day13

import com.adventofcode.day13.ShuttleBusService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
                ShuttleBusService.readFrom("939" ,
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

}