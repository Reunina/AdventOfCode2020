package com.adventofcode.day12

import com.adventofcode.day12.FerryAssert.Companion.assertThatFerry
import org.junit.jupiter.api.Test

class BasicFerryTest {

    @Test
    fun shouldStartFacingEast() {
        val brandNewFerry = BasicFerry()
        assertThatFerry(brandNewFerry)
                .isPositionedAt(0L, 0L)
                .face(Orientation.E)
    }

    @Test
    fun shouldFollowSimpleInstructions() {

        val brandNewFerry = BasicFerry()

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("F10")
                .isPositionedAt(10L, 0L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("N3")
                .isPositionedAt(10L, 3L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("F7")
                .isPositionedAt(17L, 3L)
                .face(Orientation.E)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("R90")
                .isPositionedAt(17L, 3L)
                .face(Orientation.S)

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("F11")
                .isPositionedAt(17L, -8L)
                .face(Orientation.S)

    }


    @Test
    fun shouldGiveThaManatthanDistance() {

        val brandNewFerry = BasicFerry()

        assertThatFerry(brandNewFerry)
                .afterFollowingInstruction("F10")
                .afterFollowingInstruction("N3")
                .afterFollowingInstruction("F7")
                .afterFollowingInstruction("R90")
                .afterFollowingInstruction("F11")
                .isPositionedAt(17L, -8L)
                .face(Orientation.S)
                .isAtTheDistanceOf(25L)

    }

}

