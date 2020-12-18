package com.adventofcode.day05

import com.adventofcode.day05.BinarySpacePartitionAssert.Companion.assertThatBinarySpacePartition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RowBinarySpacePartitionTest {

    private val rowBinarySpacePartition = RowBinarySpacePartition()

    @Test
    fun shouldBeInitializedWithRangeFrom0To127Rows() {
        assertThatBinarySpacePartition(rowBinarySpacePartition)
                .hasRegion(0, 127)
    }

    @Test
    fun shouldDecodeRowFromInput() {
        assertThatBinarySpacePartition(
                rowBinarySpacePartition.decode("FBFBBFF")
        )
                .hasRegion(44, 44)
    }


    @Test
    fun shouldDisplaysNicely() {
        assertThat(rowBinarySpacePartition)
                .hasToString("Row Space : [0,127]")
    }

}