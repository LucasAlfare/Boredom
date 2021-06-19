package samueltm.boredom.math

import kotlin.math.pow

@Suppress("MemberVisibilityCanBePrivate")
class CustomOperations {

    companion object {
        fun nthRoot(base: Double, root: Double) = base.pow(1.0 / root)

        fun arithmeticMean(numbers: DoubleArray) = numbers.sum() / numbers.size

        fun geometricMean(numbers: DoubleArray): Double {
            var product = 1.0
            for(n in numbers) {
                product *= n
            }
            return nthRoot(product, numbers.size.toDouble())
        }
    }
}
