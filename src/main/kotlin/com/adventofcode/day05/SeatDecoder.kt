package com.adventofcode.day05

class SeatDecoder(val seats: List<Seat>) {

    constructor(seat: Seat) : this(listOf(seat))

    companion object {

        fun readSeatFromInput(input: String): SeatDecoder {
            return SeatDecoder(readSeat(input))
        }

        private fun readSeat(input: String): Seat {
            val rowCode = input.substring(0, 7)
            val columnCode = input.substring(7)
            return Seat(
                    RowBinarySpacePartition().decode(rowCode).min,
                    ColumnBinarySpacePartition().decode(columnCode).min
            )
        }

        fun readFromInput(input: List<String>): SeatDecoder {
            return SeatDecoder(input.map { readSeat(it) }.toList())
        }
    }

    fun findSeatWithHigherIdFrom(): Seat? {
        return seats.maxBy { it.id }
    }

    fun findMissingSeat(): Int {
        val seatsIds = seats.map { it.id }
        val maxRow = seats.minBy { it -> it.id }!!.row
        val maxColumn = seats.maxBy { it.id }!!.row

        val filterIds = seats
                .filterNot { it.row == maxRow }
                .filterNot { it.row == maxColumn }
                .map { it.id }

        return filterIds.min()!!.rangeTo(filterIds.max()!!)
                .filterNot { it in seatsIds }
                .first()

    }

}
