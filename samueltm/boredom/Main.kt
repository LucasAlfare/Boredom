package samueltm.boredom

import samueltm.boredom.math.linearalgebra.Matrix2D
import samueltm.boredom.mymath.MyNumber
import kotlin.random.Random

fun generateMatrix(nRows: Int, nColumns: Int): Matrix2D {
    val numbers = DoubleArray(nRows *nColumns)
    for(i in numbers.indices) {
        numbers[i] = Random.nextInt(1, 11).toDouble()
    }
    return Matrix2D(numbers, nRows, nColumns)
}

fun main() {
    val n: MyNumber = MyNumber("1.3333...")

}