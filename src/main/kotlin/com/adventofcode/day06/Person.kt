package com.adventofcode.day06

class Person(responses: String) {

    val answers = responses.toCharArray().distinct().filter { it.toLowerCase() in  'a'.rangeTo('z')}

    fun questionAnswered(): Int {
        return answers.size
    }

    override fun toString(): String {
        return answers.joinToString("")
    }
}
