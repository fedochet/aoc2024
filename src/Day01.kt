import kotlin.math.abs

fun main() {
    fun parseData(input: List<String>): Pair<List<Int>, List<Int>> {
        val twoNumbersRegex = Regex("(\\d+)\\s+(\\d+)")

        return input
            .map { line ->
                val matchResult = twoNumbersRegex.find(line) ?: error("Cannot parse input: $line")
                val (first, second) = matchResult.destructured

                first.toInt() to second.toInt()
            }
            .unzip()
    }

    fun part1(input: List<String>): Int {
        val (left, right) = parseData(input)

        val leftSorted = left.sorted()
        val rightSorted = right.sorted()

        return leftSorted.zip(rightSorted) { a, b -> abs(a - b) }.sum()
    }

    fun part2(input: List<String>): Int {
        val (left, right) = parseData(input)

        val rightCountMap = right.groupingBy { it }.eachCount().withDefault { 0 }

        return left.sumOf { it * rightCountMap.getValue(it) }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
