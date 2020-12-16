package main.puzzle

import java.io.File

private val inputs = File("src/main/resources/input/16.txt").readText().split("\n\n")
private val rules : Map<String, List<IntRange>> = inputs[0].split("\n").map {
  val rulePart = it.split(": ")
  val ranges = rulePart[1].split(" or ")
  rulePart[0] to ranges.map { range ->
    val rangePart = range.split("-")
    rangePart[0].toInt() .. rangePart[1].toInt()
  }
}.toMap()
private val allRuleRanges : List<IntRange> = rules.values.flatten()
private val myTicket: List<Int> = inputs[1].split("\n").last().split(",").map(String::toInt)
private val tickets: List<List<Int>> = inputs[2].split("\n").drop(1)
    .map{
      it.split(",").map(String::toInt)
    }

fun main() {
  println("${part1()}")
}

private fun part1() =
  tickets.sumBy { ticket ->
     ticket.filter { field ->
      allRuleRanges.none { rule ->
        field in rule
      }
    }.sum()
  }


