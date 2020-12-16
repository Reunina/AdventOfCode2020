package com.adventofcode.day04

enum class DataField(val label: String) {

    BYR("Birth Year") {
        override fun validate(value: String): Boolean = isInRange(1920, 2002, value)
    },
    IYR("Issue Year") {
        override fun validate(value: String): Boolean = isInRange(2010, 2020, value)
    },
    EYR("Expiration Year") {
        override fun validate(value: String): Boolean = isInRange(2020, 2030, value)
    },
    HGT("Height") {
        override fun validate(value: String): Boolean = when {
            value.endsWith("cm", false) -> isInRange(150, 193, value.substringBefore("cm"))
            value.endsWith("in", false) -> isInRange(59, 76, value.substringBefore("in"))
            else -> false
        }
    },
    HCL("Hair Color") {
        override fun validate(value: String): Boolean = value.contains(Regex("^#([0-9a-f]){6,6}$"))
    },
    ECL("Eye Color") {
        override fun validate(value: String): Boolean =
                listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
    },
    PID("Passport ID") {
        override fun validate(value: String): Boolean = value.length == 9 && value.toLongOrNull() != null
    },
    CID("Country ID");

    open fun validate(value: String): Boolean = true

    override fun toString(): String {
        return "$name ('$label')"
    }

    internal fun isInRange(lower: Int, higher: Int, value: String) =
            lower.rangeTo(higher).contains(value.toIntOrNull())
}

