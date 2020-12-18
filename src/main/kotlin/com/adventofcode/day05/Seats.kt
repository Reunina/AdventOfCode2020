package com.adventofcode.day05

import java.util.*


class Seats(private var seats: MutableCollection<Seat>) {

    fun addSeat(seat: Seat) {
        this.seats.add(seat)
    }

    companion object {

        fun emptySeats(): Seats {
            return Seats(Collections.emptyList())
        }

    }


}
