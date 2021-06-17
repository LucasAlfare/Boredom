package samueltm.boredom.math.geometry

import kotlin.math.PI
import kotlin.math.pow

class Cylinder(private val height: Double, diameter: Double) : Shape() {
    private val radius: Double = diameter / 2

    override fun getArea() = (2 * PI * radius.pow(2)) + (2 * PI * radius * height)
}