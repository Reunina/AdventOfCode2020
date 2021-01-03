package com.adventofcode.day17


data class Cube(val value: Char) {

    fun isActive(): Boolean {
        return this.value == '#'
    }

}

class State(val cubes: Map<Triple<Int, Int, Int>, Cube>) {

    fun countActives(): Long {
        return cubes.values.count { it.isActive() }.toLong()
    }

    companion object {
        fun fromInput(vararg input: String): State {
            return State(input
                    .mapIndexed { x, line ->
                        line.toCharArray()
                                .mapIndexed { y, state -> Triple(x, y, 0) to Cube(state) }
                    }
                    .flatten()
                    .toMap())
        }

    }

}
