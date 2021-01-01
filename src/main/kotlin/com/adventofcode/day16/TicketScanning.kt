package com.adventofcode.day16

class TicketScanning(private val validRanges: Collection<ValidRanges>, val ticket: Ticket, private val nearbyTickets: Collection<Ticket>) {

    fun errorsInNearByTickets(): Collection<Int> {
        return nearbyTickets.map { it.findErrorsAccordingTo(validRanges) }.flatten()
    }

    companion object {
        fun fromInput(input: List<String>): TicketScanning {


            val validRanges = mutableListOf<ValidRanges>()
            var ticket : Ticket? = null
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
                    "validRanges" -> validRanges+=extractValidRanges(data)
                    "ticket" -> ticket = extractTicket(data)
                    "nearbyticket" -> nearbyTickets.add(extractTicket(data))
                }

            }

            return TicketScanning(validRanges, ticket!!, nearbyTickets)
        }

        private fun extractValidRanges(data: String) :ValidRanges  {
            val splitData= data.split(": ")
            val name = splitData[0]
            val bounds = splitData[1].split(" or ")
            val firstRAnge =bounds[0].split("-").map { it.toInt() }
            val secondRange =bounds[1].split("-").map { it.toInt() }
            return ValidRanges(name = name, firstLowerBound = firstRAnge[0], firstHigherBound = firstRAnge[1], secondLowerBound = secondRange[0], secondHigherBound = secondRange[1]) as ValidRanges
        }

        private fun extractTicket(data: String): Ticket = Ticket(*data.split(",").map { it.toInt() }.toIntArray())
    }
}

