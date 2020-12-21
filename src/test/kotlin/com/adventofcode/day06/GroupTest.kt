package com.adventofcode.day06

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.Lists
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class GroupTest {

    @Test
    fun shouldNotCountAnyResponseWhenNoPersonInTheGroup() {
        assertThat(Group().questionAtLeastOnePersonAnswered()).isEqualTo(0)
        assertThat(Group(Lists.emptyList()).questionAtLeastOnePersonAnswered()).isEqualTo(0)
    }

    @Test
    fun shouldCountThePersonAnswersWhenThereIsOnlyOnePersonOnTheGroup() {
        assertAll(
                "The group answers should be the same as the only perosnn in the group, Using the 'Anyone method'",
                {
                    assertThat(Group(Person("")).questionAtLeastOnePersonAnswered()).isEqualTo(0)
                    assertThat(Group(Person("a")).questionAtLeastOnePersonAnswered()).isEqualTo(1)
                    assertThat(Group(Person("aab")).questionAtLeastOnePersonAnswered()).isEqualTo(2)
                    assertThat(Group(Person("ab")).questionAtLeastOnePersonAnswered()).isEqualTo(2)
                }
        )
        assertAll(
                "The group answers should be the same as the only perosnn in the group, Using the 'EveryOne method'",
                {
                    assertThat(Group(Person("")).questionEveryPersonAnswered()).isEqualTo(0)
                    assertThat(Group(Person("a")).questionEveryPersonAnswered()).isEqualTo(1)
                    assertThat(Group(Person("aab")).questionEveryPersonAnswered()).isEqualTo(2)
                    assertThat(Group(Person("ab")).questionEveryPersonAnswered()).isEqualTo(2)
                }
        )

    }


    @Test
    fun shouldCountDistinctlyAnswersOfAllPersonsInTheGroupUsingAnyoneCountingMethod() {

        val persons = listOf(Person("a"), Person("b"), Person("c"))
        assertThat(Group(persons).questionAtLeastOnePersonAnswered()).isEqualTo(3)
    }

    @Test
    fun shouldCountDistinctlyAnswersOfAllPersonsInTheGroupUsingEveryoneCountingMethod() {

        val personsWithDistinctAnswers = listOf(Person("a"), Person("b"), Person("c"))
        val personsWithSameAnswers = listOf(Person("a"), Person("ab"), Person("ac"))

        assertAll(
                "The group answers should be the same as the only perosnn in the group, Using the 'EveryOne method'",
                {

                    assertThat(Group(personsWithDistinctAnswers).questionEveryPersonAnswered()).isEqualTo(0)
                    assertThat(Group(personsWithSameAnswers).questionEveryPersonAnswered()).isEqualTo(1)
                }
        )
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


    @Test
    fun shouldCountAnswerWhenEveryOneAnswerIt() {

        val onePerson = Person("abc")
        assertThat(Group(onePerson))
                .hasToString("Group of 1 person(s):\n\t$onePerson")
    }


}