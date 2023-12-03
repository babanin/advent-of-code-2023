package org.example

import day2.Game
import day2.Subset
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    println("Part #1")
    println(
        Path("input.txt").readLines()
            .map(Game.Companion::read)
            .filter(Game::isValid)
            .sumOf(Game::id)
    )

    println("Part #2")
    println(
        Path("input.txt").readLines()
            .map(Game.Companion::read)
            .map(Game::min)
            .sumOf(Subset::power)
    )
}