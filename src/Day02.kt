fun main() {
    fun getPairs(input: List<String>): List<List<String>> {
        return input.map { it.split(" ") }
    }

    fun winScore(input: List<String>): Long {
        val opponentMove = input[0]
        val myMove = input[1]

        val wins = HashMap<Pair<String, String>, Long>()
        wins[Pair("A", "X")] = 3L
        wins[Pair("A", "Y")] = 6L
        wins[Pair("A", "Z")] = 0L

        wins[Pair("B", "X")] = 0L
        wins[Pair("B", "Y")] = 3L
        wins[Pair("B", "Z")] = 6L

        wins[Pair("C", "X")] = 6L
        wins[Pair("C", "Y")] = 0L
        wins[Pair("C", "Z")] = 3L

        return wins[Pair(opponentMove, myMove)]!!
    }

    fun getScore(input: List<List<String>>): Long {
        var score = 0L

        val moveScores = HashMap<String, Int>()
        moveScores["X"] = 1
        moveScores["Y"] = 2
        moveScores["Z"] = 3

        input.forEach { score += moveScores[it[1]]!! + winScore(it)}

        return score
    }

    fun convertToNeeded(input: List<String>): List<String> {
        val needed = HashMap<Pair<String, String>, String>()
        needed[Pair("A", "X")] = "Z"
        needed[Pair("A", "Y")] = "X"
        needed[Pair("A", "Z")] = "Y"

        needed[Pair("B", "X")] = "X"
        needed[Pair("B", "Y")] = "Y"
        needed[Pair("B", "Z")] = "Z"

        needed[Pair("C", "X")] = "Y"
        needed[Pair("C", "Y")] = "Z"
        needed[Pair("C", "Z")] = "X"

        val opponentMove = input[0]
        val outcome = input[1]

        val toReturn = ArrayList<String>()
        toReturn.add(opponentMove)
        toReturn.add(needed[Pair(opponentMove, outcome)]!!)

        return toReturn
    }

    fun part1(input: List<String>): Long {
        return getScore(getPairs(input))
    }

    fun part2(input: List<String>): Long {
        return getScore(getPairs(input).map { convertToNeeded(it) })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15L)
    check(part2(testInput) == 12L)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}