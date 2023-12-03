package org.example

import java.lang.Character.isDigit
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val lines = Path("input.txt").readLines()

    println("Part #1")
    val schema = lines.map { it.toCharArray() }.toTypedArray()

    val surround = listOf(
        intArrayOf(-1, -1),
        intArrayOf(-1, 0),
        intArrayOf(-1, 1),
        intArrayOf(0, -1),
        intArrayOf(0, 1),
        intArrayOf(1, -1),
        intArrayOf(1, 0),
        intArrayOf(1, 1)
    )

    fun isSymbol(ch: Char) = (ch != '.') && !isDigit(ch)


    var sum = 0
    for (i in schema.indices) {
        var number = 0
        var touch = false
        for (j in schema[i].indices) {
            if (isDigit(schema[i][j])) {
                number *= 10
                number += schema[i][j].digitToInt()

                for (s in surround) {
                    val newI = i + s[0]
                    val newJ = j + s[1]

                    if (newI >= 0 && newI < schema.size && newJ >= 0 && newJ < schema[newI].size) {
                        touch = touch or isSymbol(schema[newI][newJ])
                    }
                }
            } else {
                if (number != 0 && touch) {
                    sum += number
                }

                number = 0
                touch = false
            }
        }

        if (number != 0 && touch) {
            sum += number
        }
    }
    println(sum)

    println("Part #2")
    data class Digit(val i: Int, val j: Int)

    var gear = 0
    for (i in schema.indices) {
        for (j in schema[i].indices) {
            if (schema[i][j] == '*') {
                val valid = ArrayDeque<Digit>()

                for (s in surround) {
                    val newI = i + s[0]
                    val newJ = j + s[1]

                    if (newI >= 0 && newI < schema.size && newJ >= 0 && newJ < schema[newI].size) {
                        if (isDigit(schema[newI][newJ])) {
                            valid.add(Digit(newI, newJ))
                        }
                    }
                }

                val visited = mutableSetOf<Digit>()
                val nums = mutableListOf<Int>()
                while (valid.isNotEmpty()) {
                    val digit = valid.removeFirst()
                    if (visited.contains(digit)) continue

                    var start = digit.j
                    var end = digit.j

                    visited.add(digit)

                    while (start >= 0 && isDigit(schema[digit.i][start])) {
                        start--
                        visited.add(digit.copy(j = start))
                    }

                    while (end < schema[digit.i].size && isDigit(schema[digit.i][end])) {
                        end++
                        visited.add(digit.copy(j = end))
                    }
                    
                    nums.add(String(schema[digit.i].copyOfRange(start + 1, end)).toInt())
                }

                if (nums.size == 2) {
                    gear += nums[0] * nums[1]
                }
            }
        }
    }

    println(gear)
}