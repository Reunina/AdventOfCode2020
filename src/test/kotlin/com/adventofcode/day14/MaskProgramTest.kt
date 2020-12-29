package com.adventofcode.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MaskProgramTest {

    @Test
    fun shouldApplyMaskSimplyToOneValue() {

        assertThat(
                MaskProgram( "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", (8L to 11L))
                        .getMemory())
                .hasSize(1)
                .containsEntry(8L, 73L)

        assertThat(
                MaskProgram( "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",  (7L to 101L))
                        .getMemory())
                .hasSize(1)
                .containsEntry(7L, 101L)


    }
    @Test
    fun shouldHandleTrickyMasks() {

        assertThat(
                MaskProgram( "01011010X10000X1111111111XX00101111X",(56344L to 1237L), (23638L to 37922654L), (20307L to 593227321L))
                        .getMemory())
                .hasSize(3)
                .containsEntry(20307L, 24228396639L)
                .containsEntry(23638L, 24228396638L)
                .containsEntry(56344L, 24228396127L)
    }

    @Test
    fun shouldApplyCompleteProgram() {

        assertThat(
                MaskProgram( "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", (8L to 11L) , (7L to 101L), (8L to 0L))
                        .getMemory())
                .hasSize(2)
                .containsEntry(8L, 64L)
                .containsEntry(7L, 101L)



    }

}