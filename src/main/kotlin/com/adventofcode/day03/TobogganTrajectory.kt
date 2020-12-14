package com.adventofcode.day03

class TobogganTrajectory(private val gridWithTrees: Grid, private val slopesModel: SlopesModel) {

    constructor() : this(Grid(listOf("")), CheaperSlopesModel())

    companion object {
        fun fromInputWithSlopeModel(inputGridWithTrees: String, slopesModel: SlopesModel): TobogganTrajectory {
            return TobogganTrajectory(Grid.fromInput(inputGridWithTrees), slopesModel)
        }
    }

    private var encounteredTrees: Int = 0

    override fun toString(): String {
        return "Toboggan Trajectory: $encounteredTrees tree(s) encountered"
    }

    fun treesEncountered(): Int {
        return encounteredTrees
    }

    fun downTheSlope() {
        var position = Triple(0, 0, '.')
        val lastLineOnGrid = gridWithTrees.lastLine()

        while (position.first < lastLineOnGrid){
            position = slopesModel.moveOn(gridWithTrees)
            if (position.third == '#') {
                encounteredTrees += 1
            }
        }

    }

}
