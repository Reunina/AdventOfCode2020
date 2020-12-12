package day02

class NeutralPolicy : Policy {
    override fun checkValidity(password: String): Boolean {
        return true
    }

}
