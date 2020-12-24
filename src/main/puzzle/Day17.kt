package main.puzzle

import java.io.File

fun main() {
  println("Part1 = ${part1()}")
  println("Part2 = ${part2()}")
}

private val inputs = File("src/main/resources/input/17.txt").readLines()

private fun part1() =
    solve { x, y ->
      Point3D(x, y, 0)
    }

private fun part2(): Int =
    solve { x, y ->
      Point4D(x, y, 0, 0)
    }

private fun solve(rounds: Int = 6, pointFunction: (Int, Int) -> Point): Int {
  var conwayGrid = parseInput(inputs, pointFunction)
  repeat(rounds) {
    conwayGrid = conwayGrid.nextCycle()
  }
  return conwayGrid.count { it.value }
}

private fun Map<Point, Boolean>.nextCycle(): Map<Point, Boolean> {
  val nextMap = this.toMutableMap()
  keys.forEach { point ->
    point.neighbors.forEach { neighbor ->
      nextMap.putIfAbsent(neighbor, false)
    }
  }
  nextMap.entries.forEach { (point, active) ->
    val activeNeighbors = point.neighbors.count { this.getOrDefault(it, false) }
    nextMap[point] = when {
      active && activeNeighbors in setOf(2, 3) -> true
      !active && activeNeighbors == 3 -> true
      else -> false
    }
  }
  return nextMap
}

private fun parseInput(input: List<String>, pointFunction: (Int, Int) -> Point): Map<Point, Boolean> =
    input.flatMapIndexed { x, row ->
      row.mapIndexed { y, point ->
        pointFunction(x, y) to (point == '#')
      }
    }.toMap()


interface Point {
  val neighbors: List<Point>
}

data class Point3D(val x: Int, val y: Int, val z: Int) : Point {
  override val neighbors: List<Point3D> by lazy {
    (x - 1..x + 1).flatMap { dx ->
      (y - 1..y + 1).flatMap { dy ->
        (z - 1..z + 1).mapNotNull { dz ->
          Point3D(dx, dy, dz).takeUnless { it == this }
        }
      }
    }
  }
}

data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int) : Point {
  override val neighbors: List<Point4D> by lazy {
    (x - 1..x + 1).flatMap { dx ->
      (y - 1..y + 1).flatMap { dy ->
        (z - 1..z + 1).flatMap { dz ->
          (w - 1..w + 1).mapNotNull { dw ->
            Point4D(dx, dy, dz, dw).takeUnless { it == this }
          }
        }
      }
    }
  }
}