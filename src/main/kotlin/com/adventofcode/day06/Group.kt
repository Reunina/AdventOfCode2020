package com.adventofcode.day06

class Group(private val persons: List<Person>) {

    constructor() : this(Person(""))
    constructor(onePerson: Person) : this(listOf(onePerson))

    fun  questionAtLeastOnePersonAnswered(): Int {
        return persons.map { it.answers }.flatten().distinct().count()

    }

    fun  questionEveryPersonAnswered(): Int {
        return 'a'.rangeTo('z')
                .filter { char -> persons.map { it.answers }.all { it.contains(char) } }
                .count()

    }

    override fun toString(): String {
        return "Group of ${persons.size} person(s):"+persons.joinToString { "\n\t$it" }
    }

    companion object {
        fun readFrom(input: String): Group {
            return Group(input.split("\n").map(::Person))
        }
    }

}
