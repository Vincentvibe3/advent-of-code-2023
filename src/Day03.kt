import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        val numData = arrayListOf<Pair<Int, Pair<Int, IntRange>>>()
        val symbols = arrayListOf<Pair<Int, Int>>()
        for ((lineIndex, line) in input.withIndex()){
            var inDigit = false
            var startIndex = 0
            val currentNum = arrayListOf<Char>()
            for ((currentIndex, c) in line.withIndex()){
                if (!inDigit && c.isDigit()){
                    inDigit = true
                    startIndex = currentIndex
                    currentNum.add(c)
                } else if (c.isDigit()){
                    currentNum.add(c)
                }
                if ((currentIndex==line.lastIndex && c.isDigit()) || (inDigit && !c.isDigit())){
                    val num = currentNum.joinToString("").toInt()
                    val start = max(startIndex-1, 0)
                    val end = min(currentIndex, line.lastIndex)
                    numData.add(num to (lineIndex to start..end))
                    currentNum.clear()
                    inDigit = false
                }
                if (!c.isDigit() && c != '.'){
                    symbols.add(lineIndex to currentIndex)
                }
            }
        }
        for (num in numData){
            for (symbol in symbols){
                if (symbol.first+1 == num.second.first && symbol.second in num.second.second){
                    sum += num.first
                    break
                }
                if (symbol.first-1 == num.second.first && symbol.second in num.second.second){
                    sum += num.first
                    break
                }
                if (symbol.first == num.second.first && symbol.second in num.second.second){
                    sum += num.first
                    break
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        val numData = arrayListOf<Pair<Int, Pair<Int, IntRange>>>()
        val symbols = arrayListOf<Pair<Int, Int>>()
        for ((lineIndex, line) in input.withIndex()){
            var inDigit = false
            var startIndex = 0
            val currentNum = arrayListOf<Char>()
            for ((currentIndex, c) in line.withIndex()){
                if (!inDigit && c.isDigit()){
                    inDigit = true
                    startIndex = currentIndex
                    currentNum.add(c)
                } else if (c.isDigit()){
                    currentNum.add(c)
                }
                if ((currentIndex==line.lastIndex && c.isDigit()) || (inDigit && !c.isDigit())){
                    val num = currentNum.joinToString("").toInt()
                    val start = max(startIndex-1, 0)
                    val end = min(currentIndex, line.lastIndex)
                    numData.add(num to (lineIndex to start..end))
                    currentNum.clear()
                    inDigit = false
                }
                if (!c.isDigit() && c == '*'){
                    symbols.add(lineIndex to currentIndex)
                }
            }
        }
        for (symbol in symbols){
            var adjacentCount = 0
            var ratio = 1
            for (num in numData){
                if (symbol.first+1 == num.second.first && symbol.second in num.second.second){
                    ratio *= num.first
                    adjacentCount += 1
                }
                if (symbol.first-1 == num.second.first && symbol.second in num.second.second){
                    ratio *= num.first
                    adjacentCount += 1
                }
                if (symbol.first == num.second.first && symbol.second in num.second.second){
                    ratio *= num.first
                    adjacentCount += 1
                }
                if (adjacentCount > 2){
                    break
                }
            }
            if (adjacentCount == 2){
                sum+=ratio
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)
    val testInput2 = readInput("Day03_test")
    check(part2(testInput2) == 467835)

    val input = readInput("Day03")
    part1(input).print("Part 1:")
    part2(input).print("Part 2:")
}
