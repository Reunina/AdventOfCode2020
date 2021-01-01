package com.adventofcode.day16

typealias Field=Int
class Ticket(private vararg val fields: Field) {

    fun findErrorsAccordingTo(validRanges: Collection<ValidRanges>): Collection<Field>{

        val ll = fields.firstOrNull {
            field ->
            ! validRanges.any { it.accept(field) }
        } ?: return emptyList()
        return listOf(ll)
    }


}
