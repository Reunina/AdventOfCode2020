package com.adventofcode.day16

class TicketScanning(private val validRanges: Collection<ValidRanges>, val ticket: Ticket, private val nearbyTickets: Collection<Ticket>) {

    fun errorsInNearByTickets(): Collection<Int> {
        return nearbyTickets.map { it.findErrorsAccordingTo(validRanges) }.flatten()
    }

    fun findPossibleFields(): Map<Int, Collection<Collection<String>>> {
        return nearbyTickets.mapIndexed { index, it ->
            val data = it.findValidAccordingTo(validRanges)
            data.map { it.key to it.value }
        }.flatten()
                .groupBy { it.first }
                .map { it.key to it.value.map { it.second } }
                .map { it.first to it.second }.toMap()

    }

    fun findWhichFieldIsWhich(): Map<Int, String> {

        val namedFields = (0 until ticket.fields.size).map { it to "" }.toMap().toMutableMap()

        var possibles= findPossibleFields()
                .map { it.key to it.value.filterNot { possibleFields -> possibleFields.isEmpty() }
                        .reduce { acc, collection -> acc.intersect(collection) } }
                .toMap()

        while (possibles.isNotEmpty() ) {
            memorizeFoundFields(possibles, namedFields)
            possibles = removeFoundFields(possibles)
            possibles = reducePossibilitiesAccordingToFoundFields(possibles, namedFields)
        }
        return namedFields
    }

    private fun reducePossibilitiesAccordingToFoundFields(possibles: Map<Int, Collection<String>>, namedFields: MutableMap<Int, String>) =
            possibles.map { it.key to it.value.filterNot { namedFields.values.contains(it) } }.toMap()

    private fun removeFoundFields(possibles: Map<Int, Collection<String>>) =
            possibles.filter { it.value.size > 1 }

    private fun memorizeFoundFields(possibles: Map<Int, Collection<String>>, namedFields: MutableMap<Int, String>) {
        possibles.filter { it.value.size == 1 }.forEach { namedFields[it.key] = it.value.first() }
    }

    fun multiplyDepartureFieldsTogether(): Long {
       return  findWhichFieldIsWhich()
                .filter { it.value.startsWith("departure") }
                .map {  ticket.fields[it.key].toLong() }
                .reduce{a, b -> a*b}

    }

    companion object {
        fun fromInput(input: List<String>): TicketScanning {


            val validRanges = mutableListOf<ValidRanges>()
            var ticket: Ticket? = null
            val nearbyTickets = mutableListOf<Ticket>()

            var typeOfLine = "validRanges"
            input.filterNot { it.isEmpty() }.forEachIndexed { index, data ->
                if (data == "your ticket:") {
                    typeOfLine = "ticket"; return@forEachIndexed
                }
                if (data == "nearby tickets:") {
                    typeOfLine = "nearbyticket"; return@forEachIndexed
                }

                when (typeOfLine) {
                    "validRanges" -> validRanges += extractValidRanges(data)
                    "ticket" -> ticket = extractTicket(data)
                    "nearbyticket" -> nearbyTickets.add(extractTicket(data))
                }

            }

            return TicketScanning(validRanges, ticket!!, nearbyTickets)
        }

        private fun extractValidRanges(data: String): ValidRanges {
            val splitData = data.split(": ")
            val name = splitData[0]
            val bounds = splitData[1].split(" or ")
            val firstRAnge = bounds[0].split("-").map { it.toInt() }
            val secondRange = bounds[1].split("-").map { it.toInt() }
            return ValidRanges(name = name, firstLowerBound = firstRAnge[0], firstHigherBound = firstRAnge[1], secondLowerBound = secondRange[0], secondHigherBound = secondRange[1]) as ValidRanges
        }

        private fun extractTicket(data: String): Ticket = Ticket(*data.split(",").map { it.toInt() }.toIntArray())
    }
}

