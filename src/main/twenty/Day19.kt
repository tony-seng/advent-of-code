package main.twenty

import java.io.File

fun main() {
  println("Part1 = ${part1()}")
  println("Part2 = ${part2()}")
}

private val inputs = File("src/main/resources/twenty/19.txt").readLines()
private val rules: MutableMap<Int, List<List<Rule>>> = parseRules(inputs)
private val messages: List<String> = inputs.dropWhile { it.isNotBlank() }.drop(1)

private fun part1(): Int = countRuleMatches()

private fun part2(): Int {
  rules[8] = listOf(
      listOf(RuleReference(42)),
      listOf(RuleReference(42), RuleReference(8))
  )
  rules[11] = listOf(
      listOf(RuleReference(42), RuleReference(31)),
      listOf(RuleReference(42), RuleReference(11), RuleReference(31))
  )
  return countRuleMatches()
}

private fun countRuleMatches(): Int =
    messages.count { message ->
      message.ruleMatch(0).any { it == message.length }
    }

private fun parseRules(input: List<String>): MutableMap<Int, List<List<Rule>>> =
    input.takeWhile { it.isNotBlank() }.map { line ->
      val (id, rhs) = line.split(": ")
      val sides = rhs.split(" | ")
      id.toInt() to sides.map { side ->
        side.split(' ').map { part ->
          if (part.startsWith('"')) Atom(part[1])
          else RuleReference(part.toInt())
        }
      }
    }.toMap().toMutableMap()

private fun String.ruleMatch(ruleId: Int, position: Int = 0): List<Int> =
    rules.getValue(ruleId).flatMap { listOfRules -> // OR Rule
      var positions = listOf(position)
      listOfRules.forEach { rule ->  // AND Rule
        positions = positions.mapNotNull { idx ->
          when {
            rule is Atom && getOrNull(idx) == rule.symbol ->
              listOf(idx + 1) // End condition
            rule is RuleReference ->
              ruleMatch(rule.id, idx)
            else ->
              null
          }
        }.flatten()
      }
      positions
    }

interface Rule
class Atom(val symbol: Char) : Rule
class RuleReference(val id: Int) : Rule
