package org.example

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {    
    println(
        Path("input.txt").readLines()
            .map { it.filter(Character::isDigit) }
            .filter(String::isNotEmpty)
            .sumOf { it.first().digitToInt() * 10 + it.last().digitToInt() }
    )
}