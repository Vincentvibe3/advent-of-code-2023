fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input){
            var first = ""
            var last = ""
            for (char in line){
                if (char.isDigit()){
                    if (first == ""){
                        first = char.toString()
                    }
                    last = char.toString()
                }
            }
            sum += (first + last).toIntOrNull() ?: 0
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val alphaDigits = mapOf(
            "one" to "1", "two" to "2", "three" to "3",
            "four" to "4", "five" to "5", "six" to "6",
            "seven" to "7", "eight" to "8", "nine" to "9"
        )
        val cleanedLines = arrayListOf<String>()
        for (line in input){
            var cleanLine = ""
            for ( i in line.indices){
                if (line[i].isDigit()){
                    cleanLine+=line[i]
                    continue
                }
                for (alphaDigit in alphaDigits){
                    val checkLen = alphaDigit.key.length
                    if (i+checkLen <= line.length){
                        val sub = line.subSequence(i until i+checkLen)
                        if (sub==alphaDigit.key){
                            cleanLine+=alphaDigit.value
                        }
                    }
                }
            }
            cleanedLines.add(cleanLine)
        }
        return part1(cleanedLines)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)
    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
