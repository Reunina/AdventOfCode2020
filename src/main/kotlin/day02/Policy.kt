package day02

interface Policy {

    fun checkValidity(password: String): Boolean
}
