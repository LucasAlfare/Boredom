package samueltm.boredom.miscellaneous

import kotlin.math.floor

class Formatting {
    companion object {
        fun valueOf(number: Double) = if (floor(number) == number) number.toInt().toString() else number.toString()
    }
}