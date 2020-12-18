package com.adventofcode.day05

class SeatDecoder {

    private val seats: List<Seat> = listOf()

    fun readSeat(input: String): Seat {
        val rowCode = input.substring(0, 7)
        val columnCode = input.substring(7)
        return Seat(
                RowBinarySpacePartition().decode(rowCode).min,
                ColumnBinarySpacePartition().decode(columnCode).min
        )
    }

    fun findSeatWithHigherIdFrom(input: List<String>): Seat? {
        return input
                .map { readSeat(it) }
                .maxBy { it.id }

    }

    fun findMissingSeats(input: List<String>): List<Int> {

        val missingIds = 0.rangeTo(1023).toMutableList()
        input
                .map { readSeat(it)}
                .map { it.id }
                .forEach { missingIds.remove(it) }
        missingIds.remove(127)
        //row * 8 + column
        // row == 0
        0.rangeTo(7).forEach { missingIds.remove(it) }
        0.rangeTo(7).forEach { missingIds.remove(127*8 + it) }
        return missingIds //592

    }
}
