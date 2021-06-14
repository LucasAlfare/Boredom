package samueltm.boredom.math.linearalgebra;

import samueltm.boredom.miscellaneous.Formatting;

import java.util.Arrays;
import java.util.Objects;

public class Matrix2D {

    private final double[] flatMatrix;
    private final int nRows, nColumns;
    private final int[] biggestNumberOfDecimalPlacesInEachColumn;

    public Matrix2D(double[] flatMatrix, int nRows, int nColumns) {
        boolean withinBounds = nRows > 0 && nColumns > 0 && nRows <= flatMatrix.length
                && nColumns <= flatMatrix.length;
        boolean distributable = nRows * nColumns == flatMatrix.length;
        if (withinBounds && distributable) {
            this.nRows = nRows;
            this.nColumns = nColumns;
            this.flatMatrix = flatMatrix;
            this.biggestNumberOfDecimalPlacesInEachColumn = new int[nColumns];
            for (int i = 0; i < this.flatMatrix.length; i++) {
                int currentColIndex = i % this.nColumns;
                int currentBiggest = biggestNumberOfDecimalPlacesInEachColumn[currentColIndex];
                int potentialBiggest = Formatting.valueOf(flatMatrix[i]).length();
                biggestNumberOfDecimalPlacesInEachColumn[currentColIndex] = Math.max(potentialBiggest, currentBiggest);
            }
        } else {
            throw new IllegalArgumentException("Unable to build a matrix with the provided parameters");
        }
    }

    public int[] getShape() {
        return new int[]{nRows, nColumns};
    }

    public double getElement(int rowIndex, int colIndex) {
        if (rowIndex >= 0 && rowIndex < nRows && colIndex >= 0 && colIndex < nColumns) {
            return flatMatrix[rowIndex * nColumns + colIndex];
        } else {
            throw new IndexOutOfBoundsException("One of the provided matrix indices is out of bounds");
        }
    }

    public Matrix2D sum(Matrix2D b) {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            final double[] numbers = new double[flatMatrix.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = flatMatrix[i] + b.flatMatrix[i];
            }

            return new Matrix2D(numbers, nRows, nColumns);
        } else {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public Matrix2D subtract(Matrix2D b) {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            final double[] numbers = new double[flatMatrix.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = flatMatrix[i] - b.flatMatrix[i];
            }

            return new Matrix2D(numbers, nRows, nColumns);
        } else {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public Matrix2D haddamard(Matrix2D b) {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            final double[] numbers = new double[flatMatrix.length];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = flatMatrix[i] * b.flatMatrix[i];
            }

            return new Matrix2D(numbers, nRows, nColumns);
        } else {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public Matrix2D multiply(Matrix2D b) {
        if (nColumns == b.nRows) {
            final double[] numbers = new double[nRows * b.nColumns];
            for (int i = 0; i < numbers.length; i++) {
                final int row = i / b.nColumns;
                final int col = i % b.nColumns;
                double sum = 0;
                for (int currentDotProductMultiplication = 0; currentDotProductMultiplication < b.nRows;
                     currentDotProductMultiplication++) {
                    sum += getElement(row, currentDotProductMultiplication)
                            * b.getElement(currentDotProductMultiplication, col);
                }
                numbers[i] = sum;
            }
            return new Matrix2D(numbers, nRows, b.nColumns);
        } else {
            throw new IllegalArgumentException("Number of columns in A must be equal to the number of rows in B");
        }
    }

    public Matrix2D multiply(double scalar) {
        final double[] numbers = new double[flatMatrix.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = flatMatrix[i] * scalar;
        }

        return new Matrix2D(numbers, nRows, nColumns);
    }

    public Matrix2D transpose() {
        final double[] numbers = new double[flatMatrix.length];
        for (int i = 0; i < numbers.length; i++) {
            final int row = i / nRows;
            final int col = i % nRows;
            numbers[i] = getElement(col, row);
        }
        return new Matrix2D(numbers, nColumns, nRows);
    }

    public static Matrix2D identity(int n) {
        if (n > 0) {
            final double[] numbers = new double[n * n];
            for (int i = 0; i < numbers.length; i++) {
                final int row = i / n;
                final int col = i % n;
                if (row == col) {
                    numbers[i] = 1;
                }
            }
            return new Matrix2D(numbers, n, n);
        } else {
            throw new IllegalArgumentException("Unable to create an identity matrix with the provided value");
        }
    }

    public Matrix2D zeroPad(int nLayers) {
        if (nLayers > 0) {
            final int newNumberOfRows = nRows + (2 * nLayers);
            final int newNumberOfColumns = nColumns + (2 * nLayers);
            final double[] numbers = new double[newNumberOfRows * newNumberOfColumns];
            for (int rowIndex = nLayers; rowIndex < nRows + nLayers; rowIndex++) {
                for (int colIndex = nLayers; colIndex < nColumns + nLayers; colIndex++) {
                    numbers[rowIndex * newNumberOfColumns + colIndex] =
                            flatMatrix[(rowIndex - nLayers) * nColumns + (colIndex - nLayers)];
                }
            }

            return new Matrix2D(numbers, newNumberOfRows, newNumberOfColumns);
        } else {
            throw new IllegalArgumentException("Number of layers must be greater or equal to 1");
        }
    }

    public Matrix2D flatten() {
        return new Matrix2D(flatMatrix, 1, nRows * nColumns);
    }

    public Matrix2D getRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < nRows) {
            final double[] numbers = new double[nColumns];
            final int startIndex = rowIndex * nColumns;
            int counter = 0;
            for (int i = startIndex; i < startIndex + nColumns; i++) {
                numbers[counter] = flatMatrix[i];
                counter++;
            }

            return new Matrix2D(numbers, 1, nColumns);
        } else {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
    }

    public Matrix2D getColumn(int colIndex) {
        if (colIndex >= 0 && colIndex < nColumns) {
            final double[] numbers = new double[nRows];
            final int stopIndex =  (colIndex + (nColumns * (nRows - 1))) + 1;
            int counter = 0;
            for (int i = colIndex; i < stopIndex; i+=nColumns) {
                numbers[counter] = flatMatrix[i];
                counter++;
            }

            return new Matrix2D(numbers, nRows, 1);
        } else {
            throw new IndexOutOfBoundsException("Column index out of bounds");
        }
    }

    public Matrix2D reshape(int nRows, int nColumns) {
        return new Matrix2D(flatMatrix,nRows, nColumns);
    }

    public Matrix2D copy() {
        return new Matrix2D(flatMatrix, nRows, nColumns);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix2D m = (Matrix2D) o;
        return nRows == m.nRows && nColumns == m.nColumns
                && Arrays.equals(flatMatrix, m.flatMatrix)
                && Arrays.equals(biggestNumberOfDecimalPlacesInEachColumn, m.biggestNumberOfDecimalPlacesInEachColumn);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nRows, nColumns);
        result = 31 * result + Arrays.hashCode(flatMatrix);
        result = 31 * result + Arrays.hashCode(biggestNumberOfDecimalPlacesInEachColumn);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < flatMatrix.length; i++) {
            final int currentRowIndex = i / nColumns;
            final int currentColIndex = i % nColumns;
            final boolean shouldPrintColumn = nColumns < 20 || (currentColIndex < 3 || currentColIndex >= nColumns - 3);
            final boolean shouldPrintRow = nRows < 20 || (currentRowIndex < 3 || currentRowIndex >= nRows - 3);

            if (shouldPrintRow) {
                if (currentColIndex == 0) {
                    if (currentRowIndex > 0) {
                        sb.append(" ");
                    }
                    sb.append("[");
                }
                final double number = flatMatrix[i];

                if (shouldPrintColumn) {
                    sb.append(String.format("%" + (biggestNumberOfDecimalPlacesInEachColumn[currentColIndex] + 1) + "s",
                            Formatting.valueOf(number)));

                    if (currentColIndex < nColumns - 1) {
                        sb.append(" ");
                    }
                } else if (currentColIndex == 7) {
                    sb.append(" ... ");
                }

                if (currentColIndex == nColumns - 1) {
                    sb.append("]");

                    if (currentRowIndex < nRows - 1) {
                        sb.append("\n");
                    }
                }
            } else if (currentRowIndex == 7 && currentColIndex == 0) {
                sb.append(" ...\n");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
