package samueltm.boredom.mymath

class MyNumber(private val stringRep: String) {
    init {
        val regex = "([-]?[0-9]+)(\\.[0-9]+(\\.{3})?)?".toRegex()
        if (!regex.matches(stringRep)) {
            throw IllegalArgumentException("Invalid number")
        }
    }


}