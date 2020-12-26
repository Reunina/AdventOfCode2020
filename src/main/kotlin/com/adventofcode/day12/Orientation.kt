package com.adventofcode.day12


enum class Orientation(cardinality: Int) {
    N(0),
    E(1),
    S(2),
    W(3);

    fun nextClockWise(): Orientation {
        if(this == W) return N
        return Orientation.values()[this.ordinal + 1]
    }

    fun previousClockWise(): Orientation {
        if(this == N) return W
        return Orientation.values()[this.ordinal - 1]
    }
}
