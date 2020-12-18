package com.adventofcode.day05

class SeatDecoder {

    companion object {

        fun readSeat(input: String): Seat {
            val rowCode = input.substring(0,7)
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
    }

}
