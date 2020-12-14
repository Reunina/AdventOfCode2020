package com.adventofcode.day03

import com.adventofcode.day03.TobogganTrajectoryAssert.Companion.assertThatTobogganTrajectory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class TobogganTrajectoryTest {

    @Test
    fun indicates0TreeEncounteredAtInitialization() {
        assertThatTobogganTrajectory(TobogganTrajectory())
                .hasTreesEncounteredAtNumberOf(0)
    }

    @Test
    fun indicatesNumberOfTreeEncounteredWithSimple2linesGridAndCheaperSlopesModel() {

        val trajectoryWithTree = TobogganTrajectory.fromInputWithSlopeModel(".#..\n...#", CheaperSlopesModel())

        trajectoryWithTree.downTheSlope()

        val trajectoryWithoutTree = TobogganTrajectory.fromInputWithSlopeModel("..##\n#...", CheaperSlopesModel())

        trajectoryWithoutTree.downTheSlope()

        assertAll(
                "Trajectory slopes must count trees encountered with simple grid And Slopes Model",
                { assertThatTobogganTrajectory(trajectoryWithTree).hasTreesEncounteredAtNumberOf(1) },
                { assertThatTobogganTrajectory(trajectoryWithoutTree).hasTreesEncounteredAtNumberOf(0) }
        )
    }

    @Test
    fun indicatesNumberOfTreeEncounteredWithMoreThan2linesGridAndCheaperSlopesModel() {

        val trajectoryWithTree = TobogganTrajectory.fromInputWithSlopeModel(
                "..##.......\n" +
                        "#...#...#..\n" +
                        ".#....#..#.\n" +
                        "..#.#...#.#\n" +
                        ".#...##..#.\n" +
                        "..#.##.....\n" +
                        ".#.#.#....#\n" +
                        ".#........#\n" +
                        "#.##...#...\n" +
                        "#...##....#\n" +
                        ".#..#...#.#"
                , CheaperSlopesModel())

        trajectoryWithTree.downTheSlope()

        assertThatTobogganTrajectory(trajectoryWithTree).hasTreesEncounteredAtNumberOf(7 )

    }

}
