fun main() {
    val matrix = readInput("Day04").map { it.split("").drop(1).dropLast(1)}.toList()
    var (countP1,countP2) = 0 to 0
    for (x in 0..matrix.size-1) {
        for (y in 0..matrix[0].size - 1) {
            countP1 += part1(matrix, x, y, "XMAS".split("").drop(1).dropLast(1))
            countP2 += part2(matrix, x, y)
        }
    }
    println("Part 1: $countP1")
    println("Part 2: $countP2")
}
fun part2(matrix: List<List<String>>, x: Int, y: Int): Int {
    for (w in listOf("MMSS", "SSMM", "MSMS", "SMSM").map{it.split("").drop(1).dropLast(1)}) {
        try {
            if (matrix[x + 1][y + 1] == "A" && matrix[x][y] == w[0] && matrix[x + 2][y] == w[1] && matrix[x][y + 2] == w[2] && matrix[x + 2][y + 2] == w[3]) return 1
        } catch (_: IndexOutOfBoundsException) { }
    }
    return 0
}
fun part1(matrix: List<List<String>>, x: Int, y: Int, searchWord: List<String>): Int {
    var result =0
    val permutations = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0, 1 to 1, -1 to 1, 1 to -1, -1 to -1)
    permutations.forEach { p ->
        for(i in 0..searchWord.size-1) {
            try {
                if (matrix[x + i * p.first][y + i * p.second] != searchWord[i]) break
                if(i==searchWord.size-1) {result++}
            } catch (_: IndexOutOfBoundsException) { }
        }
    }
    return result
}