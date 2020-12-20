package com.adventofcode.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GroupTest {

    @Test
    fun shouldNotCountAnyResponseWhenNoPersonInTheGroup() {
        assertThat(Group().questionAnswered()).isEqualTo(0)
    }

    @Test
    fun shouldCountThePersonAnswersWhenTehreIsOnlyOnePersonOnTheGroup() {
        assertAll(
                "The group answers should be the same as the only perosnn in the group",
                {
                    assertThat(Group(Person("")).questionAnswered()).isEqualTo(0)
                    assertThat(Group(Person("a")).questionAnswered()).isEqualTo(1)
                    assertThat(Group(Person("aab")).questionAnswered()).isEqualTo(2)
                    assertThat(Group(Person("ab")).questionAnswered()).isEqualTo(2)
                }
        )
    }


    @Test
    fun shouldCountDistinctlyAnswersOfAllPersonsInTheGroup() {

        val persons = listOf(Person("a"), Person("b"), Person("c"))
        assertThat(Group(persons).questionAnswered()).isEqualTo(3)
    }

    @Test
    fun shouldDisplayNicely() {

        val onePerson = Person("abc")
        assertThat(Group(onePerson))
                .hasToString("Group of 1 person(s):\n\t$onePerson")
    }


    @Test
    fun shouldReadGroupFromInput() {

        assertThat(Group.readFrom("abc"))
                .`as`("One person groups hsould be read from input")
                .isEqualToComparingFieldByFieldRecursively(Group(Person("abc")))

        assertThat(Group.readFrom("abc\na"))
                .`as`("Multiple persons groups hsould be read from input")
                .isEqualToComparingFieldByFieldRecursively(
                        Group(listOf(Person("abc"), Person("a")))
                )
    }


}