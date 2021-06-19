package samueltm.boredom.math.linearalgebra

import samueltm.boredom.miscellaneous.Formatting
import kotlin.math.max

class Matrix2D(private val flatMatrix: DoubleArray, private val nRows: Int, private val nColumns: Int) {
    private val biggestNumberOfDecimalPlacesInEachColumn: IntArray

    init {
        val withinBounds = nRows > 0 && nColumns > 0 && nRows <= flatMatrix.size && nColumns <= flatMatrix.size
        val distributable = nRows * nColumns == flatMatrix.size
        if (withinBounds && distributable) {
            biggestNumberOfDecimalPlacesInEachColumn = IntArray(nColumns)
            for (i in flatMatrix.indices) {
                val currentColIndex = i % nColumns
                val currentBiggest = biggestNumberOfDecimalPlacesInEachColumn[currentColIndex]
                val potentialBiggest = Formatting.valueOf(flatMatrix[i]).length
                biggestNumberOfDecimalPlacesInEachColumn[currentColIndex] = max(potentialBiggest, currentBiggest)
            }
        } else {
            throw IllegalArgumentException("Unable to build a matrix with the provided parameters");
        }
    }

    fun getElement(rowIndex: Int, colIndex: Int): Double {
        if (rowIndex in 0 until nRows && colIndex in 0 until nColumns) {
            return flatMatrix[rowIndex * nColumns + colIndex]
        } else {
            throw IndexOutOfBoundsException("One of the provided matrix indices is out of bounds")
        }
    }

    fun sum(b: Matrix2D): Matrix2D {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            val numbers = DoubleArray(flatMatrix.size)
            for (i in numbers.indices) {
                numbers[i] = flatMatrix[i] + b.flatMatrix[i]
            }

            return Matrix2D(numbers, nRows, nColumns)
        } else {
            throw IllegalArgumentException("Matrices must have the same dimensions")
        }
    }

    fun subtract(b: Matrix2D): Matrix2D {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            val numbers = DoubleArray(flatMatrix.size)
            for (i in numbers.indices) {
                numbers[i] = flatMatrix[i] - b.flatMatrix[i]
            }

            return Matrix2D(numbers, nRows, nColumns)
        } else {
            throw IllegalArgumentException("Matrices must have the same dimensions")
        }
    }

    fun hadamard(b: Matrix2D): Matrix2D {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            val numbers = DoubleArray(flatMatrix.size)
            for (i in numbers.indices) {
                numbers[i] = flatMatrix[i] * b.flatMatrix[i]
            }

            return Matrix2D(numbers, nRows, nColumns)
        } else {
            throw IllegalArgumentException("Matrices must have the same dimensions")
        }
    }

    fun multiply(b: Matrix2D): Matrix2D {
        if (nColumns == b.nRows) {
            val numbers = DoubleArray(nRows * b.nColumns)
            for (i in numbers.indices) {
                val row = i / b.nColumns
                val col = i % b.nColumns
                var sum = 0.0
                for (currentDotProductMultiplication in 0 until b.nRows) {
                    sum += getElement(row, currentDotProductMultiplication) *
                            b.getElement(currentDotProductMultiplication, col)
                }
                numbers[i] = sum
            }
            return Matrix2D(numbers, nRows, b.nColumns)
        } else {
            throw IllegalArgumentException("Number of columns in A must be equal to the number of rows in B")
        }
    }

    fun multiply(scalar: Double): Matrix2D {
        val numbers = DoubleArray(flatMatrix.size)
        for (i in numbers.indices) {
            numbers[i] = flatMatrix[i] * scalar
        }

        return Matrix2D(numbers, nRows, nColumns)
    }

    fun transpose(): Matrix2D {
        val numbers = DoubleArray(flatMatrix.size)
        for (i in numbers.indices) {
            val row = i / nRows
            val col = i % nRows
            numbers[i] = getElement(col, row)
        }

        return Matrix2D(numbers, nColumns, nRows)
    }

    companion object {
        fun identity(n: Int): Matrix2D {
            if (n > 0) {
                val numbers = DoubleArray(n * n);
                for (i in numbers.indices) {
                    val row = i / n
                    val col = i % n
                    if (row == col) {
                        numbers[i] = 1.0
                    }
                }

                return Matrix2D(numbers, n, n)
            } else {
                throw IllegalArgumentException("Unable to create an identity matrix with the provided value")
            }
        }
    }

    fun zeroPad(nLayers: Int): Matrix2D {
        if (nLayers > 0) {
            val newNumberOfRows = nRows + (2 * nLayers)
            val newNumberOfColumns = nColumns + (2 * nLayers)
            val numbers = DoubleArray(newNumberOfRows * newNumberOfColumns)
            for (rowIndex in nLayers until nRows + nLayers) {
                for (colIndex in nLayers until nColumns + nLayers) {
                    numbers[rowIndex * newNumberOfColumns + colIndex] =
                            flatMatrix[(rowIndex - nLayers) * nColumns + (colIndex - nLayers)]
                }
            }

            return Matrix2D(numbers, newNumberOfRows, newNumberOfColumns)
        } else {
            throw IllegalArgumentException("Number of layers must be greater or equal to 1")
        }
    }

    fun getRow(rowIndex: Int): Matrix2D {
        if (rowIndex in 0 until nRows) {
            val numbers = DoubleArray(nColumns)
            val startIndex = rowIndex * nColumns
            for ((counter, i) in (startIndex until startIndex + nColumns).withIndex()) {
                numbers[counter] = flatMatrix[i]
            }

            return Matrix2D(numbers, 1, nColumns)
        } else {
            throw IndexOutOfBoundsException("Row index out of bounds")
        }
    }

    fun getColumn(colIndex: Int): Matrix2D {
        if (colIndex in 0 until nColumns) {
            val numbers = DoubleArray(nRows)
            val stopIndex = (colIndex + (nColumns * (nRows - 1))) + 1
            for ((counter, i) in (colIndex until stopIndex step nColumns).withIndex()) {
                numbers[counter] = flatMatrix[i]
            }

            return Matrix2D(numbers, nRows, 1)
        } else {
            throw IndexOutOfBoundsException("Column index out of bounds")
        }
    }

    fun getShape() = intArrayOf(nRows, nColumns)

    fun flatten() = Matrix2D(flatMatrix, 1, nRows * nColumns)

    fun reshape(nRows: Int, nColumns: Int) = Matrix2D(flatMatrix, nRows, nColumns)

    fun copy() = Matrix2D(flatMatrix, nRows, nColumns)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true;
        val m: Matrix2D? = other as? Matrix2D
        if (m != null) {
            return nRows == m.nRows && nColumns == m.nColumns && flatMatrix.contentEquals(m.flatMatrix)
                    && biggestNumberOfDecimalPlacesInEachColumn
                    .contentEquals(m.biggestNumberOfDecimalPlacesInEachColumn)
        }
        return false
    }

    override fun hashCode(): Int {
        var hash = 23
        hash = hash * 31 + nRows.hashCode()
        hash = hash * 31 + nColumns.hashCode()
        hash = hash * 31 + flatMatrix.contentHashCode()
        hash = hash * 31 + biggestNumberOfDecimalPlacesInEachColumn.contentHashCode()

        return hash
    }

    override fun toString(): String {
        val builder = StringBuilder("[")
        for (i in flatMatrix.indices) {
            val currentRowIndex = i / nColumns
            val currentColIndex = i % nColumns
            val shouldPrintColumn = nColumns < 20 || (currentColIndex < 3 || currentColIndex >= nColumns - 3)
            val shouldPrintRow = nRows < 20 || (currentRowIndex < 3 || currentRowIndex >= nRows - 3)

            if (shouldPrintRow) {
                if (currentColIndex == 0) {
                    if (currentRowIndex > 0) {
                        builder.append(" ")
                    }
                    builder.append("[")
                }
                val number = flatMatrix[i]

                if (shouldPrintColumn) {
                    builder.append("%${biggestNumberOfDecimalPlacesInEachColumn[currentColIndex] + 1}s"
                            .format(Formatting.valueOf(number)))
                    if (currentColIndex < nColumns - 1) {
                        builder.append(" ")
                    }
                } else if (currentColIndex == 7) {
                    builder.append(" ... ")
                }

                if (currentColIndex == nColumns - 1) {
                    builder.append("]")

                    if (currentRowIndex < nRows - 1) {
                        builder.append("\n")
                    }
                }
            } else if (currentRowIndex == 7 && currentColIndex == 0) {
                builder.append(" ...\n")
            }
        }
        builder.append("]")

        return builder.toString()
    }
}
