package com.adventofcode.day05


import org.assertj.core.api.AbstractObjectAssert

class BinarySpacePartitionAssert(value: BinarySpace) : AbstractObjectAssert<BinarySpacePartitionAssert, BinarySpace>(value, BinarySpacePartitionAssert::class.java) {


    fun hasRegion(expectedMin: Int, expectedMax: Int): BinarySpacePartitionAssert {

        val expectedSpace = BinarySpace(expectedMin, expectedMax)

        if (!(expectedSpace.min == actual.min && expectedSpace.max == actual.max)) {
            failWithMessage("%nExpecting Row Space <%s> to be <%s> ", actual, expectedSpace)

        }
        return this

    }


    companion object {
        fun assertThatBinarySpacePartition(value: BinarySpace): BinarySpacePartitionAssert {
            return BinarySpacePartitionAssert(value)
        }
    }

}