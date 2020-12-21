package com.adventofcode.day08


typealias  Instruction = String

class Console(vararg inputInstructions: Instruction) {

    var instructions: MutableList<Pair<Instruction, Boolean>> = inputInstructions.map { it to false }.toMutableList()

    fun boot(): Console {
        var index = 0
        while (index < instructions.size) {
            val instruction = instructions.getOrNull(index)
            markCurrentInstructionAsVisited(index, instruction)

            if (isInstructionNullOrAlreadyVisited(instruction)) {
                break
            }

            val currentInstruction = instruction!!.first
            val value = currentInstruction.split(" ").last().toIntOrNull()

            if (currentInstruction.startsWith("acc")) {
                changeAccumulatorValueBy(value); index += 1
            }
            if (currentInstruction.startsWith("nop")) {
                index += 1
            }
            if (currentInstruction.startsWith("jmp")) {
                if (value != null) index += value;continue
            }


        }
        return this
    }

    private fun changeAccumulatorValueBy(value: Int?) {
        if (value != null) {
            accumulator += value
        }
    }

    private fun isInstructionNullOrAlreadyVisited(instruction: Pair<Instruction, Boolean>?) =
            instruction == null || instruction.second


    private fun markCurrentInstructionAsVisited(index: Int, currentInstruction: Pair<Instruction, Boolean>?) {
        instructions[index] = Pair(currentInstruction!!.first, true)
    }


    constructor() : this("")
    constructor(inputInstructions: List<Instruction>) : this(*inputInstructions.toTypedArray())

    var accumulator: Int = 0

}