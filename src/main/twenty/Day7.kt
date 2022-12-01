package main.twenty

import java.io.File

fun main() {
  part1()
  part2()
}

private val bagsRules = File("src/main/resources/twenty/7.txt")
    .readLines()
    .associate { line ->
      val (bag, contents) = line.split(" bags contain ")
      val contentList = contents.split(", ")
          .mapNotNull { s ->
            val (n, adj, color) = s.split(' ')
            n.toIntOrNull()?.let { it to "$adj $color" }
          }
      bag to contentList
    }

private const val MY_BAG = "shiny gold"

private fun part1() {
  val outerBags = mutableSetOf<String>()
  val bagQueue = ArrayDeque<String>()
  bagQueue.add(MY_BAG)
  while (bagQueue.isNotEmpty()) {
    val currentBag = bagQueue.removeLast()
    bagsRules.filterValues { bags ->
      bags.any { it.second == currentBag }
    }.keys.forEach { bag ->
          if (outerBags.add(bag))
            bagQueue.add(bag)
        }
  }
  println("Part1: ${outerBags.size}")
}

private fun part2() {
  fun bagsContainedIn(bag:String):Int =
      (bagsRules[bag] ?: error("")).sumBy {
        (n, innerBag) -> n + ( n * bagsContainedIn(innerBag) )
      }

  println("Part2: ${bagsContainedIn(MY_BAG)}")
}