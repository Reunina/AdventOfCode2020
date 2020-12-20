package com.adventofcode.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PersonTest{

    @Test
    fun shouldNotCountAnyResponseWhenNoQuestionAnswered(){
        assertThat(Person("").questionAnswered())
                .isEqualTo(0)
    }


    @Test
    fun shouldCountDistinctResponses(){
        assertThat(Person("aab").questionAnswered())
                .isEqualTo(2)
    }


    @Test
    fun shouldIngoreAnswersNotInAToZ(){
        assertThat(Person("1234567890").questionAnswered())
                .isEqualTo(0)
    }


    @Test
    fun shouldDisplayNicely(){
        assertThat(Person("aab"))
                .hasToString("ab")
    }
}