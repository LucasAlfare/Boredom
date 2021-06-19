package samueltm.boredom.math.geometry

import kotlin.math.pow

class Cube(private val sidesLength: Double): Shape() {
    override fun getArea() = 6 * sidesLength.pow(2)
}