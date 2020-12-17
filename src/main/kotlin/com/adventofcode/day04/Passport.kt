package com.adventofcode.day04

class Passport(internal val data: Map<DataField, String>) {

    override fun toString(): String {
        return "Passport(${data.toSortedMap()})"
    }


    companion object {
        fun fromInput(input: String): Passport {
            return Passport(input.split(" ", "\n")
                    .map { it.split(":") }
                    .filter { !it.first().isNullOrEmpty() }
                    .map { Pair(DataField.valueOf(it.first().toUpperCase()), it.last()) }
                    .toMap()
            )
        }
    }




}
