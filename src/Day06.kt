fun main() {
    var input = readInput("Day06")
    var matrix = toMatrix(input.toMutableList())
    var directions = listOf(0 to -1, 1 to 0, 0 to 1,-1 to 0)
    var (part1,loopsFound)=0 to 0
    for(xS in 0..matrix.size-1)
        for (yS in 0..matrix[0].size-1) {
            matrix = toMatrix(input.toMutableList())
            var (x,y) = matrix.mapIndexed { yR, r -> if (r.indexOf("^") >= 0) r.indexOf("^") to yR else -1 to -1 }.first { it.first != -1 }
            matrix[y][x]=directions[0].toString()
            if(x==xS && y==yS || matrix[yS][xS]=="#") {part1++}else{matrix[yS][xS]="#"}
            var whichDirection=0
            while (true) {
                val direction = directions[whichDirection]
                x+=direction.first; y+=direction.second
                if (x < 0 || x >= matrix[0].size || y < 0 || y >= matrix.size) break
                if (matrix[y][x] == direction.toString()) {loopsFound++;break}
                if (matrix[y][x] != "#") matrix[y][x] = direction.toString()
                else {
                    x-=direction.first; y-=direction.second
                    whichDirection = (whichDirection + 1) % directions.size
                }
            }
            if (part1++==1)
                println("Part1 ${matrix.sumOf { it.map { if (it.startsWith("(")) 1 else 0 }.sum() }}")
        }
    println("Part2 $loopsFound")
}