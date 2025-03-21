enum class CalculatorFunction(
    val number: Int? = null,
    val string: String? = null,
) {
    ONE(number = 1),
    TWO(number = 2),
    THREE(number = 3),
    FOUR(number = 4),
    FIVE(number = 5),
    SIX(number = 6),
    SEVEN(number = 7),
    EIGHT(number = 8),
    NINE(number = 9),
    ZERO(number = 0),

    PLUS(string = "+"),
    MINUS(string = "-"),
    MULTIPLY(string = "*"),
    DIVIDE(string = "/"),
    POWER(string = "^"),
    ROOT(string = "√"),
    LOG(string = "log"),

    EQUALS(string = "="),
    CLEAR(string = "C"),
    DELETE(string = "Del"),
    BRACKETS1(string = "("),
    BRACKETS2(string = ")"),
    DOT(string = "."),

    SIN(string = "sin"),
    COS(string = "cos"),
    TAN(string = "tg"),
}
