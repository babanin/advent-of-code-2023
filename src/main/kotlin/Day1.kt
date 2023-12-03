package org.example

import kotlin.io.path.Path
import kotlin.io.path.readLines

val digitMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {
    println("Part #1")
    println(
        Path("input.txt").readLines()
            .map { it.filter(Character::isDigit) }
            .filter(String::isNotEmpty)
            .sumOf { it.first().digitToInt() * 10 + it.last().digitToInt() }
    )

    println("Part #2")
    println(
        Path("input.txt").readLines()
            .map { line ->
                val minBy: Pair<Int, Pair<String, Int>>? =
                    digitMap.map { (k, v) -> Pair(line.indexOf(k), Pair(k, v)) }
                        .filter { it.first >= 0 }
                        .minByOrNull { it.first }


                val maxBy: Pair<Int, Pair<String, Int>>? =
                    digitMap.map { (k, v) -> Pair(line.lastIndexOf(k), Pair(k, v)) }
                        .filter { it.first >= 0 }
                        .maxByOrNull { it.first }


                val sb = StringBuilder(line)
                if (minBy != null) {
                    sb.insert(minBy.first, minBy.second.second)
                }

                if (maxBy != null) {
                    sb.insert(
                        maxBy.first + maxBy.second.first.length + (if (minBy != null) 1 else 0),
                        maxBy.second.second
                    )
                }

                sb.toString()
            }
            .map { it.filter(Character::isDigit) }
            .filter(String::isNotEmpty)
            .sumOf { it.first().digitToInt() * 10 + it.last().digitToInt() }
    )
}