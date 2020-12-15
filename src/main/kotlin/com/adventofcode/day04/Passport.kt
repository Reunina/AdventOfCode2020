package com.adventofcode.day04

class Passport(
        private val data: Map<DataField, String>
) {
    fun containsAllDataField(values: Collection<DataField>): Boolean {
        return values.all { data.keys.contains(it) }
    }

    override fun toString(): String {
        return "Passport($data)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Passport

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

    companion object {
        fun fromInput(input: String): Passport {
            return Passport(input.split(" ", "\n")
                    .map { it.split(":") }
                    .map { Pair(DataField.valueOf(it.first().toUpperCase()), it.last()) }
                    .toMap()
            )
        }
    }




}
