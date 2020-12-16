package com.adventofcode.day04

interface PassportsValidator {

    val validationData: Collection<Pair<Passport, Boolean>>
    val validData : Collection<Pair<Passport, Boolean>>
    var invalidityCause : String

    fun areAllPassportsValidOnes(): Boolean {
        return validationData.all { validatePassport(it.first) }
    }

    fun validatePassport(passport: Passport): Boolean {
        val invalidData = DataField.values().filter { it != DataField.CID }.subtract(passport.data.keys)
        invalidityCause = "Missing some data = $invalidData"
        return invalidData.isEmpty()
    }

     fun asString(): String {
        return "${this.javaClass.simpleName}: ${validData.size} data(s) validated among:\n${validationData.joinToString("\n")}"
    }

}
