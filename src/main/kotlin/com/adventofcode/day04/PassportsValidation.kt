package com.adventofcode.day04

class PassportsValidation(val data: Collection<Pair<Passport, Boolean>>) {
    fun areAllPassportsValidOnes(): Boolean {
        return data.all { it.second }
    }

    override fun toString(): String {
        return "PassportsValidation: ${data.count { it.second }} passport(s) validated.\n\n${data.joinToString("\n")}"
    }


}
