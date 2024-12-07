fun main() {
    val initialList = """mul\((\d+),(\d+)\)|don't\(\)|do\(\)""".toRegex()
        .findAll(readInputRaw("day03"))
        .toMutableList()

    println("Result 1 ${calculateResult(initialList.filter { it.value.startsWith("mul") })}")

    var doState = true
    val rList = mutableListOf<MatchResult>()
    initialList.forEach {
            if (it.value == "do()") doState = true
            if (it.value.startsWith("don")) doState = false
            if (it.value.startsWith("mul") && doState) rList.add(it)
        }
    println("Result 2 ${calculateResult(rList)}")
}

private fun calculateResult(rList: List<MatchResult>): Int = rList
    .map { it.groupValues[1].toInt() to it.groupValues[2].toInt() }
    .sumOf { it.first * it.second }