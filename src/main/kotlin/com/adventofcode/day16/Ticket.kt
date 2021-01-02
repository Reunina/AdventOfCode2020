package com.adventofcode.day16

typealias Field = Int

class Ticket(internal vararg val fields: Field) {

    fun findErrorsAccordingTo(validRanges: Collection<ValidRanges>): Collection<Field> {
        return listOf(fields.firstOrNull { field ->
            !validRanges.any { it.accept(field) }
        } ?: return emptyList())
    }

    fun findValidAccordingTo(validRanges: Collection<ValidRanges>): Map<Int, List<String>> {
        return fields
                .mapIndexed() { index, field ->
                    index to validRanges
                            .filter {  it.accept(field) }
                }
                .map { it.first to it.second.map { it.name }.toList() }
                .toMap()
    }
}
