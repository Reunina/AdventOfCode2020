package com.adventofcode.day08


typealias  Instruction = String
typealias  Program = List<Instruction>

class Console(vararg inputInstructions: Instruction) {

    constructor() : this("")
    constructor(inputInstructions: List<Instruction>) : this(*inputInstructions.toTypedArray())

    var accumulator: Int = 0
    var instructions: MutableList<Instruction> = inputInstructions.toMutableList()
    var visitedInstructions: List<Int> = mutableListOf()

    fun boot(): Console {
        var index = 0
        if (instructions.isNullOrEmpty()) return this
        while (index < instructions.size) {
            val currentInstruction = instructions.getOrNull(index)

            if (currentInstruction == null || visitedInstructions.contains(index)) {
                break
            }

            visitedInstructions += listOf(index)

            val value = currentInstruction.split(" ").last().toIntOrNull()

            if (currentInstruction.startsWith("acc")) {
                if (value != null) accumulator += value
                index += 1
            }
            if (currentInstruction.startsWith("nop")) {
                index += 1
            }
            if (currentInstruction.startsWith("jmp")) {
                if (value != null) index += value; continue
            }
        }
        return this
    }


    fun smartBoot(): Console {
        boot()
        if (lastInstructionHasBeenVisited()) return this

        val mutatedInstructions = generatedMutatedInstructions()

        mutatedInstructions.forEach {
            if (!lastInstructionHasBeenVisited()) {
                instructions = it as MutableList<Instruction>
                accumulator = 0
                visitedInstructions = mutableListOf()
                boot()
            }
        }

        return this
    }

    private fun generatedMutatedInstructions(): List<Program> {
        return instructions
                .mapIndexed { index, instruction -> instruction to index }
                .filter { it.first.startsWith("jmp") }
                .map {
                    instructions.subList(0, it.second) + listOf(it.first.replace("jmp", "nop")) + instructions.subList(it.second + 1, instructions.size)
                }
                .toList()

    }

    fun lastInstructionHasBeenVisited(): Boolean = visitedInstructions.contains(instructions.size - 1)


}