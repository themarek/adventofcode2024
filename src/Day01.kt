import kotlin.collections.sorted

fun main() {
    val mappedValues = readInput("Day01").flatMap { it.split("\\s+".toRegex()) }.withIndex().groupBy { it.index % 2 }.map { it.value.map { it.value.toLong() } }
    val l1 = mappedValues[0].sorted()
    val l2 = mappedValues[1].sorted()
    println("part1 ${l1.mapIndexed{ i, e -> kotlin.math.abs(l1[i] - l2[i]) }.sum()}")
    println("part2 ${l1.sumOf { it * l2.filter { s -> s == it }.size }}")
}