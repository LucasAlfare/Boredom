package samueltm.boredom

import samueltm.boredom.math.linearalgebra.Matrix2D
import kotlin.random.Random

fun generateMatrix(nRows: Int, nColumns: Int): Matrix2D {
    val numbers = DoubleArray(nRows *nColumns)
    for(i in numbers.indices) {
        numbers[i] = Random.nextInt(1, 11).toDouble()
    }
    return Matrix2D(numbers, nRows, nColumns)
}

fun main() {
    println(generateMatrix(4,3))
}