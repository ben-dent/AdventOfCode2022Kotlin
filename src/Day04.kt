fun main() {

    fun part1(input: List<String>): Int {
        return input.count { contains(it, 1) }
    }

    fun part2(input: List<String>): Int {
        return input.count { contains(it, 2) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun contains(input: String, part: Int): Boolean {
    val commaSplit = input.split(",")
    val left = commaSplit[0]
    val right = commaSplit[1]

    var split = left.split("-")
    var currentSet = HashSet<Int>()

    val map = HashMap<Int, Set<Int>>()

    for (i in split[0].toInt()..split[1].toInt()) {
        currentSet.add(i)
    }

    map[1] = currentSet
    currentSet = HashSet()

    split = right.split("-")

    for (i in split[0].toInt()..split[1].toInt()) {
        currentSet.add(i)
    }

    map[2] = currentSet

    if (part == 1) {
        return map[1]!!.containsAll(map[2]!!) || map[2]!!.containsAll(map[1]!!)
    }

    for (v in map[1]!!) {
        val two = map[2]!!

        if (two.contains(v)) {
            return true
        }
    }

    return false
}