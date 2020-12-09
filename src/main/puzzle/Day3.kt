package main.puzzle

import java.io.File
import java.math.BigDecimal
import java.text.ParseException

fun main() {
  day3()
}

fun day3(input: List<String> = File("src/main/resources/input/3.txt").readLines()) {
  val map = TreeMap.parse(input)
  val slope = Slope(3, 1)
  val part1 = countTreesHit(map, slope)
  println("Part 1: $part1")

  val slopes = listOf(Slope(1, 1), Slope(3, 1), Slope(5, 1), Slope(7, 1), Slope(1, 2))
  val part2 = slopes
      .map { countTreesHit(map, it) }
      .fold(BigDecimal.ONE) { acc, treesHit -> acc * treesHit.toBigDecimal() }
  println("Part 2: $part2")
}

fun countTreesHit(map: TreeMap, slope: Slope): Int {
  return (0 until map.height step slope.down)
      .asSequence()
      .filterIndexed { index, row -> map.isTreeAt(column = index * slope.right, row = row) }
      .count()
}

data class Slope(val right: Int, val down: Int)

data class TreeMap(
    val trees: List<List<Boolean>>,
    val sliceWidth: Int,
    val height: Int
) {

  companion object Parser {

    fun parse(rows: List<String>): TreeMap {
      val sliceWidth = rows[0].length
      val trees = rows.map(Parser::mapRow)
      return TreeMap(trees, sliceWidth, rows.size)
    }

    private fun mapRow(row: String): List<Boolean> = row.map(Parser::mapChar)
    private fun mapChar(char: Char): Boolean = when (char) {
      '.' -> false
      '#' -> true
      else -> throw ParseException("Failed to parse Map", -1)
    }
  }

  fun isTreeAt(column: Int, row: Int): Boolean = trees[row][column % sliceWidth]
}