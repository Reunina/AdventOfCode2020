package com.adventofcode.day16

import java.io.File
 
fun main() {
    val ticketScanning = TicketScanning.fromInput(File("src/main/resources/day16/puzzle_input.txt")
            .readLines())
    println("Day 16, part 01: my ticket scanning error rate is: "+ticketScanning.errorsInNearByTickets().sum())
    println("Day 16, part 02: my ticket scanning error rate is: "+ticketScanning.multiplyDepartureFieldsTogether())
}
 
