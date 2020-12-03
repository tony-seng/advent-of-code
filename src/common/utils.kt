package common

import java.io.File

fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()