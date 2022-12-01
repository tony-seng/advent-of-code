package main.twenty

import java.io.File

fun main() {
  println("Part1 = ${part1()}")
  println("Part2 = ${part2()}")
}

fun Iterable<Long>.product(): Long =
    this.reduce { a, b -> a * b }

fun Char.asLong(): Long =
    this.toString().toLong()

private val inputs = File("src/main/resources/twenty/18.txt").readLines()

private val equations = inputs.map { it.replace(" ", "") }

private fun part1(): Long =
    equations.sumOf { solvePart1(it.iterator()) }

private fun part2(): Long =
    equations.sumOf { solvePart2(it.iterator()) }

private fun solvePart1(equation: CharIterator): Long {
  val numbers = mutableListOf<Long>()
  var op = '+'
  while (equation.hasNext()) {
    when (val next = equation.nextChar()) {
      '(' -> numbers += solvePart1(equation)
      ')' -> break
      in setOf('+', '*') -> op = next
      else -> numbers += next.asLong()
    }
    if (numbers.size == 2) {
      val a = numbers.removeLast()
      val b = numbers.removeLast()
      numbers += if (op == '+') a + b else a * b
    }
  }
  return numbers.first()
}

private fun solvePart2(equation: CharIterator): Long {
  val multiplyThese = mutableListOf<Long>()
  var added = 0L
  while (equation.hasNext()) {
    val next = equation.nextChar()
    when {
      next == '(' -> added += solvePart2(equation)
      next == ')' -> break
      next == '*' -> {
        multiplyThese += added
        added = 0L
      }
      next.isDigit() -> added += next.asLong()
    }
  }
  return (multiplyThese + added).product()
}

