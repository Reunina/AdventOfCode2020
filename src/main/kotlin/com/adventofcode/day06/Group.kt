package com.adventofcode.day06

class Group(val persons: List<Person>) {

    constructor() : this(Person(""))
    constructor(onePerson: Person) : this(listOf(onePerson))

    fun  questionAnswered(): Int {
        return persons.map { it.answers }.flatten().distinct().count()

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
