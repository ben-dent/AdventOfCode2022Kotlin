fun main() {

    fun part1(input: List<String>): String {
        val piles = getBoxes(input)
        var boxes = piles.first
        val i = piles.second

        val newData = input.subList(i + 2, input.size)

        for (line in newData) {
            val s1 = line.split("move ")[1]
            val s2 = s1.split(" from ")
            val number = s2[0].toInt()
            val s3 = s2[1].split(" to ")
            val from = s3[0].toInt()
            val to = s3[1].toInt()

            boxes = move1(from, to, number, boxes)
        }

        var toReturn = ""

        for (key in boxes.keys) {
            toReturn += boxes[key]!![0]
        }

        return toReturn
    }

    fun part2(input: List<String>): String {
        val piles = getBoxes(input)
        var boxes = piles.first
        val i = piles.second

        val newData = input.subList(i + 2, input.size)

        for (line in newData) {
            val s1 = line.split("move ")[1]
            val s2 = s1.split(" from ")
            val number = s2[0].toInt()
            val s3 = s2[1].split(" to ")
            val from = s3[0].toInt()
            val to = s3[1].toInt()

            boxes = move2(from, to, number, boxes)
        }

        var toReturn = ""

        for (key in boxes.keys) {
            toReturn += boxes[key]!![0]
        }

        return toReturn
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

fun move2(from: Int, to: Int, number: Int, boxes: HashMap<Int, ArrayList<String>>): HashMap<Int, ArrayList<String>> {
    var firstData = boxes[from]!!
    val secondData = boxes[to]!!

    val newTo = ArrayList(firstData.subList(0, number))
    newTo.addAll(secondData)

    firstData = ArrayList(firstData.drop(number))

    boxes[from] = firstData
    boxes[to] = newTo

    return boxes
}

fun move1(from: Int, to: Int, number: Int, boxes: HashMap<Int, ArrayList<String>>): HashMap<Int, ArrayList<String>> {
    val firstData = boxes[from]!!
    val secondData = boxes[to]!!
    val newTo = ArrayList<String>()
    var newFrom = ArrayList<String>(firstData)
    val temp = ArrayList<String>()

    for (i in 1..number) {
        temp.add(newFrom[0])

        newFrom = ArrayList(newFrom.drop(1))
    }

    newTo.addAll(temp.reversed())
    newTo.addAll(secondData)
    boxes[to] = newTo
    boxes[from] = newFrom

    return boxes
}

fun getBoxes(input: List<String>): Pair<HashMap<Int, ArrayList<String>>, Int> {
    val map = HashMap<Int, ArrayList<String>>()

    for (i in input.indices) {
        val line = input[i]
        if (line.isEmpty() || line.contains(" 1 ")) {
            return Pair(map, i)
        }

        var count = 1
        val list = ArrayList<String>()
        var current = ""
        var j = 0

        while (j < line.length) {
            val c = line[j]
            current += c

            if (count == 3) {
                list.add(current.trim())
                current = ""
                count = 0
                ++j
            }

            ++j
            ++count
        }

        for (k in 0 until list.size) {
            val item = list[k]
            if (item.isNotBlank()) {
                val toAdd = item.split("[")[1].split("]")[0]
                val prev = map.getOrDefault(k + 1,  ArrayList())
                prev.add(toAdd)
                map[k + 1] = prev
            }
        }
    }

    return Pair(map, -1)
}
