package main.puzzle

import java.io.File

private val inputs = File("src/main/resources/input/16.txt").readText().split("\n\n")
private val rules: Map<String, List<IntRange>> = inputs[0].split("\n").map {
    val rulePart = it.split(": ")
    val ranges = rulePart[1].split(" or ")
    rulePart[0] to ranges.map { range ->
        val rangePart = range.split("-")
        rangePart[0].toInt()..rangePart[1].toInt()
    }
}.toMap()
private val allRuleRanges: List<IntRange> = rules.values.flatten()
private val myTicket: List<Int> = inputs[1].split("\n").last().split(",").map(String::toInt)
private val tickets: List<List<Int>> = inputs[2].split("\n").drop(1)
        .map {
            it.split(",").map(String::toInt)
        }

fun main() {
    println("Part1 = ${part1()}")
    println("Part2 = ${part2()}")
}

private fun part1() =
        tickets.sumBy { ticket ->
            ticket.filter { field ->
                allRuleRanges.none { rule ->
                    field in rule
                }
            }.sum()
        }

private fun isValidTicket(ticket: List<Int>): Boolean =
        ticket.all { field ->
            allRuleRanges.any { rule ->
                field in rule
            }
        }

private fun columnPassesRule(tickets: List<List<Int>>, column: Int, fieldName: String): Boolean =
        tickets.all { ticket ->
            rules.getValue(fieldName).any { rule -> ticket[column] in rule }
        }

private fun part2(): Long {
    val validTickets = tickets.filter { isValidTicket(it) }

    val possibleFieldRules: Map<String, MutableSet<Int>> = rules.keys.map { rule ->
        rule to myTicket.indices.filter { column ->
            columnPassesRule(validTickets, column, rule)
        }.toMutableSet()
    }.toMap()

    val foundRules = reduceRules(possibleFieldRules)

    return foundRules.entries
            .filter { it.key.startsWith("departure") }
            .map { myTicket[it.value].toLong() }
            .reduce { a, b -> a * b }
}

private fun reduceRules(possibleRules: Map<String,MutableSet<Int>>): Map<String,Int> {
    val foundRules = mutableMapOf<String,Int>()
    while(foundRules.size < possibleRules.size) {
        possibleRules.entries
                .filter { (_, possibleValues) -> possibleValues.size == 1 }
                .forEach { (rule, possibleValues) ->
                    val columnNumber = possibleValues.first()
                    foundRules[rule] = columnNumber
                    possibleRules.values.forEach { it.remove(columnNumber) }
                }
    }
    return foundRules
}
