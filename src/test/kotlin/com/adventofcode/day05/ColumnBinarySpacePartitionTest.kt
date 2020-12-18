package com.adventofcode.day05

import com.adventofcode.day05.BinarySpacePartitionAssert.Companion.assertThatBinarySpacePartition
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class ColumnBinarySpacePartitionTest {

    private val columnBinarySpacePartition = ColumnBinarySpacePartition()

    @Test
    fun shouldBeInitializedWithRangeFrom0To127Rows() {
        assertThatBinarySpacePartition(columnBinarySpacePartition)
                .hasRegion(0,7)
    }

    @Test
    fun shouldDecodeRowFromInput() {
        assertThatBinarySpacePartition(
                columnBinarySpacePartition.decode("FBFBBFF")
        )
                .hasRegion(2, 2)
    }

    @Test
    fun shouldDisplaysNicely() {
        assertThat(columnBinarySpacePartition)
                .hasToString("Column Space : [0,7]")
    }

}