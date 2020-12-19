package com.adventofcode.day05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BinarySpaceTest {

    @Test
    fun shouldDisplayNicely() {
        assertThat(BinarySpace(5, 10))
                .hasFieldOrPropertyWithValue("min", 5)
                .hasFieldOrPropertyWithValue("max", 10)
                .hasToString("[5,10]")
    }


    @Test
    fun shouldBeAbleToReduceToItsLowerHalf() {


        assertAll(
                "Handles lower half on odd numbers at max",
                {
                    assertThat(BinarySpace(0, 127).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 63))

                    assertThat(BinarySpace(32,63).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(32,47))

                    assertThat(BinarySpace(1, 11).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(1, 6))

                }
        )


        assertAll(
                "Handles lower half on even numbers at max",
                {
                    assertThat(BinarySpace(0, 12).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 6))
                    assertThat(BinarySpace(0, 10).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 5))

                    assertThat(BinarySpace(0, 126).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 63))
                }
        )

        assertAll(
                "Handles lower half on singleton range",
                {
                    assertThat(BinarySpace(12, 12).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(12,12))

                    assertThat(BinarySpace(11, 11).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(11,11))

                    assertThat(BinarySpace(0,0).takeLowerHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 0))
                }
        )


    }


    @Test
    fun shouldBeAbleToReduceToItsUpperHalf() {


        assertAll(
                "Handles higher half on odd numbers at max",
                {
                    assertThat(BinarySpace(0, 63).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(32, 63))

                    assertThat(BinarySpace(32, 47).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(40, 47))

                    assertThat(BinarySpace(0, 127).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(64, 127))

                }
        )


        assertAll(
                "Handles higher half on even numbers at max",
                {
                    assertThat(BinarySpace(0, 12).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(6, 12))
                    assertThat(BinarySpace(0, 10).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(5,10))

                    assertThat(BinarySpace(0, 126).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(63, 126))
                }
        )

        assertAll(
                "Handles higher half on singleton range",
                {
                    assertThat(BinarySpace(12, 12).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(12,12))

                    assertThat(BinarySpace(11, 11).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(11,11))

                    assertThat(BinarySpace(0,0).takeUpperHalf())
                            .isEqualToComparingFieldByFieldRecursively(BinarySpace(0, 0))
                }
        )
    }



    @Test
    fun shouldReduceToSingleValueAfter7SplitsWhenStartingWithSpaceFrom0To127() {
        assertThat(
                BinarySpace(0, 127)
                        .takeLowerHalf()
                        .takeUpperHalf()
                        .takeLowerHalf()
                        .takeUpperHalf()
                        .takeUpperHalf()
                        .takeLowerHalf()
                        .takeLowerHalf()
        )
                .isEqualToComparingFieldByFieldRecursively(BinarySpace(44,44))
    }


    @Test
    fun shouldReduceToSingleValueAfter3SplitsWhenStartingWithSpaceFrom0To7() {
        assertThat(
                BinarySpace(0,7)
                        .takeUpperHalf()
                        .takeLowerHalf()
                        .takeUpperHalf()
        )
                .isEqualToComparingFieldByFieldRecursively(BinarySpace(5,5))
    }


}
