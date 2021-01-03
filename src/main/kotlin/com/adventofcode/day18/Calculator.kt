package com.adventofcode.day18

class Calculator(private val expressions: List<String>) {
    constructor(expression: String) : this(listOf(expression))


    fun evaluate( ): Long {
        return expressions.map {
            evaluate(it)
        }.sum()

    }

    fun evaluate(expression : String ): Long {
        var reducedExpression = expression
        while (reducedExpression.contains("(")) { // soppose that expression is well formed then Nb of ( == nb of )

            val inParenthesisExpression = reducedExpression.substringAfterLast("(").substringBefore(")")
            val inParenthesisResult = executeSimply(inParenthesisExpression).toString()

            reducedExpression =reducedExpression.replace("($inParenthesisExpression)", inParenthesisResult)
        }
        return executeSimply(reducedExpression)
    }

    private fun executeSimply(data: String): Long {
        val split = data.split(" ").iterator()
        var m1 = split.next().toLong()
        var res = 0L
        while (split.hasNext()) {
            val op = split.next()
            val m2 = split.next()
            res = evaluateExpression(m1, op, m2.toLong())
            m1 = res
        }
        return res
    }

    private fun evaluateExpression(m1: Long, op: String, m2: Long): Long {
        if (op == "+") return (m1 + m2)
        return (m1 * m2)
    }

}
