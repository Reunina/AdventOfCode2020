package com.adventofcode.day12

import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions

open class FerryAssert(value: Ferry) : AbstractObjectAssert<FerryAssert, Ferry>(value, FerryAssert::class.java) {


    fun isPositionedAt(eastWest: Long, northSouth: Long): FerryAssert {
        return hasPositionNothSouth(northSouth)
                .hasPositionEastWest(eastWest)

    }

    fun hasPositionNothSouth(expected: Long) : FerryAssert {
        Assertions.assertThat(actual.positionNorthSouth).isEqualTo(expected)
        return this
    }
    fun hasPositionEastWest(expected: Long) : FerryAssert {
        Assertions.assertThat(actual.positionEastWest).isEqualTo(expected)
        return this
    }

    fun face(expectedFacing: Orientation): FerryAssert {
        val actualFacing = actual.facing
        Assertions.assertThat(actualFacing)
                .`as`("Expecting facing <%s> but foung facing <%s>", expectedFacing , actual.facing)
                .isEqualTo(expectedFacing)
        return this

    }


    fun afterFollowingInstruction(instruction: String): FerryAssert {
        actual.followInstruction(instruction)
        return this

    }

    fun isAtTheDistanceOf(expectedDistance: Long): FerryAssert {
        Assertions.assertThat(actual.distance()).isEqualTo(expectedDistance)
        return this
    }


    companion object {
        fun assertThatFerry(value: Ferry): FerryAssert {
            return FerryAssert(value)
        }
    }

}