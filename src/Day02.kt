fun main() {
    fun part1(input: List<String>): Int {
        val maxCubes = 39
        val colorCount = mapOf(
            "red" to 12,
            "green" to 13,
            "blue" to 14
        )
        var sum = 0
        for (line in input){
            val splitLine = line.split(":")
            val id = splitLine[0].removePrefix("Game").trim().toInt()
            var validGame = true
            for (set in splitLine[1].split(";")){
                var setTotal = 0
                var valid = true
                for (colorInfo in set.split(",")){
                    val splitColor = colorInfo.trim().split(" ")
                    val count = splitColor[0].trim().toInt()
                    val color = splitColor[1]
                    val colorMax = colorCount[color] ?: 0
                    if (colorMax < count){
                        valid = false
                        break
                    } else {
                        setTotal += count
                    }
                }
                if (setTotal > maxCubes){
                    validGame = false
                    break
                }
                if (!valid){
                    validGame = false
                    break
                }
            }
            if (validGame){
                sum += id
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input){
            val splitLine = line.split(":")
            var power = 1
            val gameMaxes = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
            for (set in splitLine[1].split(";")){


                for (colorInfo in set.split(",")){
                    val splitColor = colorInfo.trim().split(" ")
                    val count = splitColor[0].trim().toInt()
                    val color = splitColor[1]
                    val currentMax = gameMaxes[color]?:0
                    if (currentMax<count){
                        gameMaxes[color] = count
                    }
                }
            }
            for (color in gameMaxes){
                power*=color.value
            }
            sum += power
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    val testInput2 = readInput("Day02_test")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).print("Part 1:")
    part2(input).print("Part 2:")
}
