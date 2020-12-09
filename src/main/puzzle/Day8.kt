package main.puzzle

import java.io.File

fun main() {
  val executed: MutableSet<Int> = mutableSetOf()
  part1(instructions, executed)
  part2(executed)
}

private val instructions = File("src/main/resources/input/8.txt").readLines()
    .map {
       instruction -> instruction.split(" ")
    }.toMutableList()

private fun part1(instructions: List<List<String>>, executed: MutableSet<Int>) : Boolean {
  executed.clear()
  var instructionPointer = 0
  var accumulator = 0
  while(!executed.contains(instructionPointer) && instructionPointer < instructions.size) {
    executed.add(instructionPointer)
    val (ops, args) = instructions[instructionPointer]
    when(ops) {
      "acc" -> { accumulator += args.toInt()
        instructionPointer++ }
      "jmp" -> instructionPointer += args.toInt()
      "nop" -> instructionPointer++
    }
  }
  println("Part1 = $accumulator")
  return instructionPointer == instructions.size
}

private fun part2(executed: MutableSet<Int>) {
  val originalProgram = instructions
  for (i in executed.size - 2 downTo 0) {
    val lastExecuted = executed.elementAt(i)
    if (originalProgram[lastExecuted][0] == "jmp") {
      originalProgram[lastExecuted] = listOf("nop", originalProgram[lastExecuted][1])
      part1(originalProgram, executed)
      if (part1(originalProgram, executed)) {
        println("Instructions ended successfully")
        break
      }
    }
  }
}