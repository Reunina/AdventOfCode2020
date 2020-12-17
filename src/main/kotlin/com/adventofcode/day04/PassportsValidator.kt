package com.adventofcode.day04

interface PassportsValidator {


    var invalidityCause: String
    val validationData: Collection<Pair<Passport, Boolean>>

    fun areAllPassportsValidOnes(): Boolean {
        // return validationData.all { it.second }  produces a false positive in mutatinon testing by pitest. ok when replaced by a for loop and returns as below :
        validationData.forEach {
            when {
                !it.second -> return false
            }
        }
        return true
    }

    fun validateAll(passports: Collection<Passport>): Collection<Pair<Passport, Boolean>> {
        return passports.map { it to validatePassport(it) }
    }

    fun validatePassport(passport: Passport): Boolean {
        val invalidData = DataField.values().filter { it != DataField.CID }.subtract(passport.data.keys).toList()
        invalidityCause = "Missing some data = $invalidData"
        return invalidData.isEmpty()
    }

    fun asString(): String {
        return "${this.javaClass.simpleName}: ${validationData.size} data(s) validated among:\n${validationData.joinToString("\n")}"
    }

}
