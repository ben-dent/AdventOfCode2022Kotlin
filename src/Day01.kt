fun main() {
    fun getGroups(input: List<String>): List<List<Int>> {
        val toReturn = ArrayList<ArrayList<Int>>()

        var current = ArrayList<Int>();

        for (s in input) {
            if (s.isEmpty()) {
                toReturn.add(current)
                current = ArrayList();
            } else {
                current.add(s.toInt())
            }
        }

        toReturn.add(current)

        return toReturn
    }

    fun part1(input: List<String>): Long {
        val groups = getGroups(input)

        return groups.maxOf { it.sum().toLong() }
    }

    fun part2(input: List<String>): Long {
        val groups = getGroups(input)

        return groups.map { it.sum() }.sortedDescending().take(3).sum().toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000L)
    check(part2(testInput) == 45000L)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}