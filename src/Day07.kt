fun main() {
    fun check(l: List<Long>, r: Long, p: Long, ops: List<(x:Long, y:Long) -> Long> ): Boolean = if(l.isEmpty()) r==p else ops.any{check(l.drop(1),it(r,l[0]),p,ops)}
    val add= {x:Long,y:Long -> x+y}
    val mul= {x:Long,y:Long -> x*y}
    val concat={x:Long,y:Long -> (x.toString()+y.toString()).toLong()}
    val equations = readInput("Day07").map { it.split(":") }.map { it[0].toLong() to it[1].split(" ").filter(String::isNotEmpty).map { it.toLong() } }
    fun result(e: List<Pair<Long, List<Long>>>, ops: List<(Long, Long) -> Long>):Long = e.sumOf { (p,c) -> if(check(c.drop(1),c[0],p,ops)) p else 0 }
    println("result 1 = ${result(equations,listOf(add,mul))}")
    println("result 2 = ${result(equations,listOf(add,mul,concat))}")
}