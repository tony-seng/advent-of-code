package main.puzzle

import java.io.File

fun main() {
    day4()
}

private val PASSPORT_ATTRIBUTES = listOf("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")

private val fieldPatterns = listOf(
        """\bbyr:(19[2-9][0-9]|200[0-2])\b""",
        """\biyr:(201[0-9]|2020)\b""",
        """\beyr:(202[0-9]|2030)\b""",
        """\bhgt:((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))\b""",
        """\bhcl:#[0-9a-f]{6}\b""",
        """\becl:(amb|blu|brn|gry|grn|hzl|oth)\b""",
        """\bpid:[0-9]{9}\b"""
    ).map { it.toRegex() }

fun day4() {
    val passports : List<String> = File("src/main/resources/input/4.txt").readText().split("\n\n")

    val validPassportPart1 = passports.count{ passport -> PASSPORT_ATTRIBUTES.all { passport.contains(it)}}
    println(validPassportPart1)

    val validPassportPart2 = passports.count { passport -> fieldPatterns.all { it.containsMatchIn(passport)}}
    println(validPassportPart2)
}
