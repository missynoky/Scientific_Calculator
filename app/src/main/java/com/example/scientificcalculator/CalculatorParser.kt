package com.example.scientificcalculator

import java.util.Stack
import kotlin.math.cos
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

fun evaluateExpression(expression: String): Double {
    val tokens = tokenize(expression)
    val rpn = infixToRPN(tokens)
    return evaluateRPN(rpn)
}

fun tokenize(expression: String): List<String> {
    val regex = "\\d+(\\.\\d+)?|[+\\-*/^()!]|sin|cos|tan|log|sqrt".toRegex()
    return regex.findAll(expression).map { it.value }.toList()
}

fun infixToRPN(tokens: List<String>): List<String> {
    val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "^" to 3)
    val output = mutableListOf<String>()
    val operators = Stack<String>()

    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> output.add(token)
            token in precedence.keys -> {
                while (operators.isNotEmpty() && precedence.getOrDefault(token, 0) <= precedence.getOrDefault(operators.peek(), 0)) {
                    output.add(operators.pop())
                }
                operators.push(token)
            }
            token in listOf("sin", "cos", "tan", "log", "sqrt") -> operators.push(token)
            token == "(" -> operators.push(token)
            token == ")" -> {
                while (operators.isNotEmpty() && operators.peek() != "(") {
                    output.add(operators.pop())
                }
                operators.pop()
                if (operators.isNotEmpty() && operators.peek() in listOf("sin", "cos", "tan", "log", "sqrt")) {
                    output.add(operators.pop())
                }
            }
        }
    }
    while (operators.isNotEmpty()) {
        output.add(operators.pop())
    }
    return output
}

fun evaluateRPN(tokens: List<String>): Double {
    val stack = Stack<Double>()
    for (token in tokens) {
        when {
            token.toDoubleOrNull() != null -> stack.push(token.toDouble())
            token in listOf("+", "-", "*", "/", "^") -> {
                val b = stack.pop()
                val a = stack.pop()
                stack.push(
                    when (token) {
                        "+" -> a + b
                        "-" -> a - b
                        "*" -> a * b
                        "/" -> a / b
                        "^" -> a.pow(b)
                        else -> throw IllegalArgumentException("Nieznany operator")
                    }
                )
            }
            token in listOf("sin", "cos", "tan", "log", "sqrt") -> {
                val a = stack.pop()
                stack.push(
                    when (token) {
                        "sin" -> sin(a)
                        "cos" -> cos(a)
                        "tan" -> tan(a)
                        "log" -> log10(a)
                        "sqrt" -> sqrt(a)
                        else -> throw IllegalArgumentException("Nieznana funkcja")
                    }
                )
            }
        }
    }
    return stack.pop()
}

fun main() {
    val testExpressions = listOf(
        "3 + 4 * 2",
        "(1 + 2) * (3 + 4)",
        "10 / 2 + 3 * 4",
        "5 + 3 ^ (2 * 2 * sin(30))",
        "(2 + 3) ^ 2",
        "100 / (5 * (2 + 3))",
        "sin(30) * 5",
        "cos(60) + tan(45)",
        "sqrt(16) + log(100)"
    )

    for (expr in testExpressions) {
        println("Expression: $expr")
        println("Result: ${evaluateExpression(expr)}")
        println("--------------------------------")
    }
}