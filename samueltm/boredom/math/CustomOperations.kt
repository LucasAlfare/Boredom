package samueltm.boredom.math

import kotlin.math.pow

class CustomOperations {

    companion object {
        fun nthRoot(base: Double, root: Double): Double {
            return base.pow(1.0 / root)
        }

        fun geometricMean(numbers: DoubleArray): Double {
            var product = 1.0
            for(n in numbers) {
                product *= n
            }
            return nthRoot(product, numbers.size.toDouble())
        }

        fun arithmeticMean(numbers: DoubleArray): Double {
            return numbers.sum() / numbers.size
        }
    }
}