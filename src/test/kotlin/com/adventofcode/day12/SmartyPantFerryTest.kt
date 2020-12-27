package com.adventofcode.day12

import com.adventofcode.day12.FerryAssert.Companion.assertThatFerry
import com.adventofcode.day12.SmartFerryAssert.Companion.assertThatSmartFerry
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SmartyPantsTest {

    @Test
    fun shouldStartFacingEastWithGivenWaypoint() {
        val brandNewFerry = SmartyPantsFerry()
        assertThatSmartFerry(brandNewFerry)
                .isPositionedAt(0L, 0L)
                .hasWaypointAt(10L,1L)
    }

    @Test
    fun shouldFollowSimpleInstructions() {

        val brandNewFerry = SmartyPantsFerry()

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("F10")
                .isPositionedAt(100L, 10L)
                .hasWaypointAt(10L, 1L)

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("N3")
                .isPositionedAt(100L, 10L)
                .hasWaypointAt(10L,4L)

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("F7")
                .isPositionedAt(170L, 38L)
                .hasWaypointAt(10L,4L)

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("R90")
                .isPositionedAt(170L, 38L)
                .hasWaypointAt(4L,-10L)

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("F11")
                .isPositionedAt(214L, -72L)
                .hasWaypointAt(4L,-10L)

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("L90")
                .isPositionedAt(214L, -72L)
                .hasWaypointAt(10L,4L)

    }

    @Test
    fun shouldGiveThaManatthanDistance() {

        val brandNewFerry = SmartyPantsFerry()

        assertThatSmartFerry(brandNewFerry)
                .afterFollowingInstruction("F10")
                .afterFollowingInstruction("N3")
                .afterFollowingInstruction("F7")
                .afterFollowingInstruction("R90")
                .afterFollowingInstruction("F11")
                .isPositionedAt(214L, -72L)
                .hasWaypointAt(4L,-10L)
                .isAtTheDistanceOf(286L)

    }

}


class SmartFerryAssert(value: SmartyPantsFerry) : AbstractObjectAssert<SmartFerryAssert, SmartyPantsFerry>(value, SmartFerryAssert::class.java) {

    fun hasWaypointAt(eastWest: Long, northSouth: Long): SmartFerryAssert {
        return hasWayPointNorthSouth(northSouth)
                .hasWayPointEastWest(eastWest)

    }

    private fun hasWayPointEastWest(expected: Long): SmartFerryAssert {
        assertThat(actual.wayPointEastWest).isEqualTo(expected)
        return this
    }

    private fun hasWayPointNorthSouth(expected: Long): SmartFerryAssert {
        assertThat(actual.wayPointNorthSouth).isEqualTo(expected)
        return this
    }

    fun isPositionedAt(expectedEastWest: Long, expectedNorthSouth: Long): SmartFerryAssert {
        assertThatFerry(actual).isPositionedAt(expectedEastWest, expectedNorthSouth)
        return this
    }

    fun face(expected: Orientation): SmartFerryAssert {
        assertThatFerry(actual).face(expected)
        return this
    }

    fun afterFollowingInstruction(instruction: String): SmartFerryAssert {
        actual.followInstruction(instruction)
        return this
    }

    fun isAtTheDistanceOf(expectedDistance: Long): SmartFerryAssert {
        assertThatFerry(actual).isAtTheDistanceOf(expectedDistance)
        return this
    }


    companion object {
        fun assertThatSmartFerry(value: SmartyPantsFerry): SmartFerryAssert {
            return SmartFerryAssert(value)
        }
    }


}