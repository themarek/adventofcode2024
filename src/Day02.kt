fun main() {
    var (safeReports1,safeReports2) = 0 to 0
    readInput("Day02").forEach {
        val numbers = it.split(" ").map { it.toInt() }
        if (valid(numbers)) safeReports1++
        else for (i in 0..numbers.size - 1) {
                val n = numbers.toMutableList()
                n.removeAt(i)
                if (valid(n)) safeReports2++
                if (valid(n)) break
            }
    }
    println("safeReports part1 ${safeReports1}, safeReports part2 ${safeReports1 + safeReports2}")
}

fun valid(numbers: List<Int>): Boolean = numbers.windowed(2, 1)
        .map { (x1, x2) -> x2 - x1 }
        .map { it * if (numbers[0] < numbers[1]) 1 else -1 }
        .filter { it >= 1 && it <= 3 }
        .size == numbers.size - 1