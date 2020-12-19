package com.adventofcode.day05


val minRows = 0
val maxRows = 127

class RowBinarySpacePartition : BinarySpace(minRows, maxRows) {


    override fun toString(): String {
        return "Row Space : " + super.toString()
    }
}

