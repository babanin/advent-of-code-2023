package org.example

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val lines = Path("input.txt").readLines()

    println("Part #1")
    val sum = lines.sumOf {
        val (_, number) = it.split(":")
        val (win, current) = number.split("|")
        val winNumbers = win.split(" ").map(String::trim).toSet()
        val actualWin = current.split(" ").map(String::trim).filter(String::isNotBlank).count(winNumbers::contains)

        println("$winNumbers | $actualWin")

        if (actualWin > 0) 1 shl (actualWin - 1) else 0
    }

    println(sum)

    println("Part #2")
    var totalCards = 0
    val cards = IntArray(lines.size) { 1 }

    for (i in lines.indices) {
        val line = lines[i]
        totalCards += cards[i]

        val (_, number) = line.split(":")
        val (win, current) = number.split("|")
        val winNumbers = win.split(" ").map(String::trim).toSet()
        val copies = current.split(" ").map(String::trim).filter(String::isNotBlank).count(winNumbers::contains)


        println("Card #${i + 1} = ${cards[i]} \t copies = $copies")

        for (j in 1..copies) {
            if (i + j < lines.size) {
                cards[i + j] += cards[i]
            }
        }
    }

    println(totalCards)
}