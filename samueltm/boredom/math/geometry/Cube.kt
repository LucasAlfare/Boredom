package samueltm.boredom.math.geometry

import kotlin.math.pow

class Cube(private val sidesLength: Double): Shape() {
    override fun getArea(): Double {
        return 6 * sidesLength.pow(2)
    }
}