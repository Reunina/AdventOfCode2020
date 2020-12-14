package com.adventofcode.day03

interface SlopesModel {
    fun moveOn(gridWithTrees: Grid): Triple<Int, Int, Char>
}
