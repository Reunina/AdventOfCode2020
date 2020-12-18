package com.adventofcode.day05

import java.io.Serializable
import java.util.*

data class Seat(val row: Int, val column: Int) : Serializable {

    val id: Int = row * 8 + column

    override fun toString(): String {
        return "Seat(row=$row, column=$column, id=$id)"
    }



}
