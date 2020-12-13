package com.adventofcode.day02

interface Policy {

    fun checkValidity(password: String): Boolean
}
