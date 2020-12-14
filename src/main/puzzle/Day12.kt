package main.puzzle

import java.io.File
import kotlin.math.absoluteValue

fun main() {
  val inputs = File("src/main/resources/input/12.txt").readLines()
      .map { it.first() to it.drop(1).toInt() }
  println("Part 1= ${part1(inputs)}")
  println("Part 2= ${part2(inputs)}")
}


private fun part1(inputs: List<Pair<Char,Int>>) =
    applyCommands(inputs, Ship(0, 0, Position(1, 0)), "S").manhattan()

private fun part2(inputs: List<Pair<Char,Int>>) =
    applyCommands(inputs, Ship(0, 0, Position(10, 1)), "V").manhattan()

fun applyCommands(commands: List<Pair<Char, Int>>, ship: Ship, target: String) =
    ship.apply {
      commands.map {
        this.applyCommand(it, target)
      }
    }

data class Position(
    var x: Int,
    var y: Int
) {

  fun turn(command: Pair<Char, Int>) {
    repeat(command.second / 90) {
      when(command.first) {
        'L' -> turnLeft()
        'R' -> turnRight()
      }
    }
  }

  private fun turnLeft() = x.let { this.x = -y; this.y = it }
  private fun turnRight() = x.let { this.x = y; this.y = -it }
}

data class Ship(
    var x : Int,
    var y: Int,
    var position: Position) {

  fun applyCommand(command: Pair<Char,Int>, target: String) =
      when (command.first) {
        'N' -> if (target == "S") this.y += command.second else this.position.y += command.second
        'S' -> if (target == "S") this.y -= command.second else this.position.y -= command.second
        'E' -> if (target == "S") this.x += command.second else this.position.x += command.second
        'W' -> if (target == "S") this.x -= command.second else this.position.x -= command.second
        'F' -> {
          this.x += position.x * command.second
          this.y += position.y * command.second
        }
        else -> this.position.turn(command)
      }

  fun manhattan() = x.absoluteValue + y.absoluteValue
}
