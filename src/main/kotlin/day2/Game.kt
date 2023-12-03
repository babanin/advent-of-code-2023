package day2

import kotlin.math.max

data class Game(val id: Int, val subsets: List<Subset>) {
    companion object {
        fun read(input: String): Game {
            val (gameStr, subsetsStr) = input.split(":")
            val gameId = gameStr.substring("Game ".length).toInt()
            val subsetsArr = subsetsStr.split(";")
            return Game(gameId, subsetsArr.map(Subset.Companion::read))
        }
    }

    fun min(): Subset = subsets.reduce { acc, subset ->
        Subset(
            max(acc.red ?: 0, subset.red ?: 0),
            max(acc.green ?: 0, subset.green ?: 0),
            max(acc.blue ?: 0, subset.blue ?: 0),
        )
    }

    fun isValid(): Boolean = subsets.all(Subset::valid)
}

data class Subset(val red: Int?, val green: Int?, val blue: Int?) {
    fun valid(): Boolean = (red == null || red <= 12) && (green == null || green <= 13) && (blue == null || blue <= 14)
    fun power(): Int = listOfNotNull(red, green, blue).reduce { acc, i -> acc * i }

    companion object {
        fun read(input: String): Subset {
            val ballsStr = input.split(",").map { it.trim() }

            var red: Int? = null
            var green: Int? = null
            var blue: Int? = null

            for (ball in ballsStr) {
                val value = ball.substring(0, ball.indexOf(' ')).toInt()

                with(ball) {
                    when {
                        endsWith("red") -> red = value
                        endsWith("green") -> green = value
                        endsWith("blue") -> blue = value
                    }
                }
            }

            return Subset(red, green, blue)
        }
    }
}