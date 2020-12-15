package main.puzzle

import java.io.File

fun main() {
    val inputs = File("src/main/resources/input/14.txt").readLines()
    println("Part1 = ${part1(inputs)}")
    println("Part2 = ${part2(inputs)}")
}

private val memory: MutableMap<Long, Long> = mutableMapOf()

private const val DEFAULT_MASK = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

private fun toBinary(value: String): String =
        value.toLong().toString(2).padStart(36, '0')

private fun maskedWith(value: String, mask: String): Long =
        toBinary(value).zip(mask).map { (valueChar, maskChar) ->
            maskChar.takeUnless { it == 'X' } ?: valueChar
        }.joinToString("").toLong(2)

private fun part1(inputs: List<String>) : Long {
        var mask = DEFAULT_MASK
        inputs.forEach { instruction ->
            if (instruction.startsWith("mask")) {
                mask = instruction.substringAfter("= ")
            } else {
                val address = instruction.substringAfter("[").substringBefore("]").toLong()
                val value = instruction.substringAfter("= ")
                memory[address] = maskedWith(value, mask)
            }
        }
        return memory.values.sum()
}

private fun generateAddressMasks(value: String, mask: String): List<Long> {
    val addresses = mutableListOf(toBinary(value).toCharArray())
    mask.forEachIndexed { idx, bit ->
        when (bit) {
            '1' -> addresses.forEach { it[idx] = '1' }
            'X' -> {
                addresses.forEach { it[idx] = '1' }
                addresses.addAll(
                        addresses.map {
                            it.copyOf().apply {
                                this[idx] = '0'
                            }
                        }
                )
            }
        }
    }
    return addresses.map { it.joinToString("").toLong(2) }
}

private fun part2(inputs: List<String>): Long {
    var mask = DEFAULT_MASK
    inputs.forEach { instruction ->
        if (instruction.startsWith("mask")) {
            mask = instruction.substringAfter("= ")
        } else {
            val unmaskedAddress = instruction.substringAfter("[").substringBefore("]")
            val value = instruction.substringAfter("= ").toLong()
            generateAddressMasks(unmaskedAddress, mask).forEach { address ->
                memory[address] = value
            }

        }
    }
    return memory.values.sum()
}