fun main() {
    val rules = readInput("Day05").filter { it.contains("|") }.map { it.split("|") }.map { it[0].toInt() to it[1].toInt() }.toList()
    val updates = readInput("Day05").filter { it.contains(",") }.map { it.split(",").map { it.toInt() }.toList() }.toList()

    val result1 = updates.sumOf { update -> if (checkAllRules(rules, update)) update[update.size / 2] else 0 }
    val result2 = updates.filter{!checkAllRules(rules,it)}.map{it.toMutableList()}
        .map { update->
            while(!checkAllRules(rules, update))
                for(rule in rules)
                    while (!checkUpdate(update, rule) )
                        java.util.Collections.swap(update, update.indexOf(rule.first), update.lastIndexOf(rule.second))
            update
        }
        .sumOf { update -> update[update.size / 2] }
    println("result1 $result1")
    println("result2 $result2")
}
fun checkAllRules(rules: List<Pair<Int, Int>>, update: List<Int>): Boolean = rules.map { rule -> checkUpdate(update, rule) }.reduce { acc, bool -> acc && bool }
fun checkUpdate(update: List<Int>, rule: Pair<Int, Int>): Boolean = update.count{it==rule.first || it==rule.second }<2 || update.indexOf(rule.first) < update.indexOf(rule.second)