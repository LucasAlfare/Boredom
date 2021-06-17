package samueltm.boredom.math.geometry

import kotlin.math.PI
import kotlin.math.pow

class Sphere(private val radius: Double): Shape() {
    override fun getArea(): Double {
        return 4 * PI * radius.pow(2)
    }
}