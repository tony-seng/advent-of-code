package main.puzzle

import java.io.File

fun main() {
  val inputs : List<Int> = File("src/main/resources/input/10.txt").readLines().map(String::toInt)
  part1(inputs)
  part2(inputs)
}

private fun part1(inputs : List<Int>) {
  val adapters = inputs.sorted().toMutableList()
  adapters.mapIndexed { index, i -> i - adapters.getOrElse(index - 1) { 0 } }
      .plus(3)
      .let { it.count { i -> i == 1 } * it.count { i -> i == 3 } }
      .let { println(it) }
}

private fun part2(inputs : List<Int>) {
  val adapters = inputs.sorted().toMutableList()
  adapters.fold(mapOf(0 to 1L),
      { acc, i -> acc.plus(i to (i - 3 until i)
          .map { acc[it] ?: 0 }
          .sum())
      }
  )[adapters.last()]
      .let { println(it) }
}