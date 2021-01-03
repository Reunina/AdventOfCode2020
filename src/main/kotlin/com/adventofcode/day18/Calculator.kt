package com.adventofcode.day18

class Calculator(private val expressions: List<String>) {
    constructor(expression: String) : this(listOf(expression))


    fun evaluate(): Long {
        return expressions.map {
            evaluate(it)
        }.sum()

    }

    private fun evaluate(expression: String): Long {
        val reducedExpression = reduceParenthesis(expression)
        return executeSimply(reducedExpression)
    }

    private fun reduceParenthesis(expression: String): String {
        var reducedExpression = expression
        while (reducedExpression.contains("(")) { // soppose that expression is well formed then Nb of ( == nb of )
            val inParenthesisExpression = reducedExpression.substringAfterLast("(").substringBefore(")")
            val inParenthesisResult = executeSimply(inParenthesisExpression).toString()
            reducedExpression = reducedExpression.replace("($inParenthesisExpression)", inParenthesisResult)
        }
        return reducedExpression
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

    fun evaluateWithPrecedence(): Long {
        return expressions.map {
            evaluateExpressionWithPrecedence(it)
        }.sum()

    }

    private fun evaluateExpressionWithPrecedence(expression: String): Long {
        val toto = reduceParenthesisv2(expression)
        val reducedExpression = reduceAddition(toto)
        if (reducedExpression.contains(" ")) return executeSimply(reducedExpression)
        return reducedExpression.toLong()
    }

    private fun reduceParenthesisv2(expression: String): String {
        var reducedExpression = expression
        while (reducedExpression.contains("(")) { // soppose that expression is well formed then Nb of ( == nb of )
            val inParenthesisExpression = reducedExpression.substringAfterLast("(").substringBefore(")")
            val inParenthesisResult = evaluateExpressionWithPrecedence(inParenthesisExpression).toString()
            reducedExpression = reducedExpression.replace("($inParenthesisExpression)", inParenthesisResult)
        }
        return reducedExpression
    }

    private fun reduceAddition(expression: String): String {
        var reducedExpression = expression
        while (reducedExpression.contains("+")) {
            val data = reducedExpression.split(" ")
            val index = data.indexOfFirst { it == "+" }
            val addition = data.subList(index - 1, index + 2).joinToString(" ")
            val additionResult = executeSimply(addition).toString()
            reducedExpression = data.subList(0, index - 1).plus(additionResult).plus(data.subList(index + 2, data.size)).joinToString(" ")
        }
        return reducedExpression
    }
}
