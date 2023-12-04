import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0.0
        for (line in input){
            val splitData = line.split(":")[1].trim().split("|")
            val winning = splitData[0].trim().split(" ")
                .filter { it.trim() != "" }
                .map { it.trim().toInt() }.toSet()
            val others = splitData[1].trim().split(" ")
                .filter { it.trim() != "" }
                .map { it.trim().toInt() }.toSet()
            val matchCount = winning.intersect(others).size
            if (matchCount!=0) {
                if (matchCount == 1){
                    sum += 1
                } else {
                    sum += 2.0.pow (matchCount-1)
                }
            }
        }
        return sum.toInt()
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var currentIndex = 1
        val counts = hashMapOf<Int, Int>()
        for (line in input){
            val currentCount = counts.getOrDefault(currentIndex, 0)
            counts[currentIndex] = currentCount + 1
            val splitData = line.split(":")[1].trim().split("|")
            val winning = splitData[0].trim().split(" ")
                .filter { it.trim() != "" }
                .map { it.trim().toInt() }.toSet()
            val others = splitData[1].trim().split(" ")
                .filter { it.trim() != "" }
                .map { it.trim().toInt() }.toSet()
            val matchCount = winning.intersect(others).size
            for (i in 1..matchCount){
                val currentCountNext = counts.getOrDefault(currentIndex+i, 0)
                counts[currentIndex+i] = currentCountNext + counts[currentIndex]!!
            }
            currentIndex += 1
        }
        sum += counts.values.sum()
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    val testInput2 = readInput("Day04_test")
    check(part2(testInput2) == 30)

    val input = readInput("Day04")
    part1(input).print("Part 1:")
    part2(input).print("Part 2:")
}
