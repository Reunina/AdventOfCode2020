package com.adventofcode.day03

class CheaperSlopesModel(private var moveRightBy: Int, private var moveDownBy: Int) : SlopesModel {

    override fun moveOn(gridWithTrees: Grid): Triple<Int, Int, Char> {
        gridWithTrees.moveRightBy(moveRightBy)
        gridWithTrees.moveDownBy(moveDownBy)
        return gridWithTrees.getPosition()

    }

    override fun toString(): String {
        return "CheaperSlopesModel(moveRightBy=$moveRightBy, moveDownBy=$moveDownBy)"
    }


}