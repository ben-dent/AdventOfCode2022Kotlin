fun getIntersection(input: String): Set<Char> {
    val mid = input.length / 2

    val first = input.substring(0, mid).toSet()
    val second = input.substring(mid).toSet()

    return first.intersect(second)
}

fun getIntersection(input: List<String>): Set<Char> {
    val first = input[0].toSet()
    val second = input[1].toSet()
    val third = input[2].toSet()

    return first.intersect(second).intersect(third)
}

fun getPriority(n: Int): Int {
    if (n in 65..90) {
        return n - 38
    }

    return n - 96
}

fun getGroups(input: List<String>): List<List<String>> {
    val groups = ArrayList<List<String>>()
    var current = ArrayList<String>()

    var count = 0

    input.forEach {
        current.add(it)

        if (count == 2) {
            groups.add(current)
            current = ArrayList()
            count = 0
        } else {
            ++count
        }
    }

    return groups
}

fun main() {

    fun part1(input: List<String>): Long {
        return input.sumOf { getIntersection(it).sumOf { i -> getPriority(i.code) } }.toLong()
    }

    fun part2(input: List<String>): Long {
        val groups = getGroups(input)
        return groups.sumOf { getIntersection(it).sumOf { i -> getPriority(i.code) } }.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157L)
    check(part2(testInput) == 70L)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}