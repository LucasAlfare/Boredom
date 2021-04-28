package samueltm.boredom.math.linearalgebra;

import java.util.Arrays;
import java.util.Objects;

public class Matrix2D {

    private final double[][] matrix;
    private final int nRows, nColumns;
    private final int[] biggestNumberOfDecimalPlacesInEachColumn;

    public Matrix2D(double[] array, int nRows, int nColumns) {
        boolean withinBounds = nRows > 0 && nColumns > 0 && nRows <= array.length
                && nColumns <= array.length;
        boolean distributable = nRows * nColumns == array.length;

        if (withinBounds && distributable) {
            this.nRows = nRows;
            this.nColumns = nColumns;
            matrix = new double[nRows][nColumns];
            biggestNumberOfDecimalPlacesInEachColumn = new int[nColumns];
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nColumns; j++) {
                    matrix[i][j] = array[i * nColumns + j];
                    int current = biggestNumberOfDecimalPlacesInEachColumn[j];
                    int potential = String.valueOf(matrix[i][j]).length();
                    biggestNumberOfDecimalPlacesInEachColumn[j] = Math.max(potential, current);
                }
            }

        } else {
            throw new IllegalArgumentException("Unable to build a matrix with the provided parameters");
        }
    }

    public int[] getShape() {
        return new int[]{nRows, nColumns};
    }

    public Matrix2D sum(Matrix2D b) {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            Matrix2D result = new Matrix2D(new double[nRows * nColumns], nRows, nColumns);
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nColumns; j++) {
                    result.matrix[i][j] = matrix[i][j] + b.matrix[i][j];
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public Matrix2D subtract(Matrix2D b) {
        if (nRows == b.nRows && nColumns == b.nColumns) {
            Matrix2D result = new Matrix2D(new double[nRows * nColumns], nRows, nColumns);
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < nColumns; j++) {
                    result.matrix[i][j] = matrix[i][j] - b.matrix[i][j];
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }
    }

    public Matrix2D multiply(Matrix2D b) {
        if (nColumns == b.nRows) {
            Matrix2D result = new Matrix2D(new double[nRows * b.nColumns], nRows, b.nColumns);
            for (int i = 0; i < nRows; i++) {
                for (int j = 0; j < b.nColumns; j++) {
                    double sum = 0;
                    for (int k = 0; k < b.nRows; k++) {
                        sum += matrix[i][k] * b.matrix[k][j];
                    }
                    result.matrix[i][j] = sum;
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Number of columns in A must be equal to the number of rows in B");
        }
    }

    public Matrix2D multiply(double scalar) {
        Matrix2D result = new Matrix2D(new double[nRows * nColumns], nRows, nColumns);
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                result.matrix[i][j] = matrix[i][j] * scalar;
            }
        }

        return result;
    }

    public Matrix2D transpose() {
        Matrix2D result = new Matrix2D(new double[nRows * nColumns], nColumns, nRows);
        for (int i = 0; i < nColumns; i++) {
            for (int j = 0; j < nRows; j++) {
                result.matrix[i][j] = matrix[j][i];
            }
        }

        return result;
    }

    public static Matrix2D identity(int n) {
        if (n > 0) {
            Matrix2D result = new Matrix2D(new double[n * n], n, n);
            for (int i = 0; i < result.nRows; i++) {
                for (int j = 0; j < result.nColumns; j++) {
                    if (i == j) {
                        result.matrix[i][j] = 1;
                    }
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Unable to create an identity matrix with the provided value");
        }
    }

    public Matrix2D zeroPad(int nLayers) {
        if (nLayers > 0) {
            int newNumberOfRows = nRows + (2 * nLayers);
            int newNumberOfColumns = nColumns + (2 * nLayers);
            Matrix2D result = new Matrix2D(new double[newNumberOfRows * newNumberOfColumns], newNumberOfRows,
                    newNumberOfColumns);

            for (int i = nLayers; i < nRows + nLayers; i++) {
                for (int j = nLayers; j < nColumns + nLayers; j++) {
                    result.matrix[i][j] = matrix[i - nLayers][j - nLayers];
                }
            }

            return result;
        } else {
            throw new IllegalArgumentException("Number of layers must be greater or equal to 1");
        }
    }

    public Matrix2D flatten() {
        final int newNumberOfColumns = nRows * nColumns;
        final Matrix2D result = new Matrix2D(new double[newNumberOfColumns],1,newNumberOfColumns);
        int counter = 0;
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                result.matrix[0][counter] = matrix[i][j];
            }
        }

        return result;
    }

    public Matrix2D getRow(int rowIndex) {
        final Matrix2D result = new Matrix2D(new double[nColumns],1,nColumns);
        if (rowIndex >= 0 && rowIndex < nRows) {
            for (int column = 0; column < nColumns; column++) {
                result.matrix[0][column] = matrix[rowIndex][column];
            }
            return result;
        } else {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }
    }

    public Matrix2D getColumn(int columnIndex) {
        final Matrix2D result = new Matrix2D(new double[nRows],nRows,1);
        if (columnIndex >= 0 && columnIndex < nColumns) {
            for (int row = 0; row < nRows; row++) {
                result.matrix[row][0] = matrix[row][columnIndex];
            }
            return result;
        } else {
            throw new IndexOutOfBoundsException("Column index out of bounds");
        }
    }

    public Matrix2D reshape(int numRows, int numColumns) {
        return new Matrix2D(flatten().getArray()[0], numRows, numColumns);
    }

    public double[][] getArray() {
        return matrix;
    }

    public Matrix2D copy() {
        return new Matrix2D(flatten().getArray()[0], nRows, nColumns);
    }

    public boolean symmetric() {
        if (nRows == nColumns) {
            for (int row = 0; row < nRows; row++) {
                for (int column = 0; column < nColumns; column++) {
                    if(matrix[row][column] != matrix[column][row]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix2D m = (Matrix2D) o;
        if (nRows == m.nRows && nColumns == m.nColumns) {
            for (int row = 0; row < nRows; row++) {
                for (int column = 0; column < nColumns; column++) {
                    if (m.matrix[row][column] != matrix[row][column]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nRows, nColumns);
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int row = 0; row < nRows; row++) {
            if (row > 0) {
                sb.append(" ");
            }
            sb.append("[");
            for (int column = 0; column < nColumns; column++) {
                final double number = matrix[row][column];
                String numberString;
                if (number == (int) number) {
                    numberString = "" + (int) number;
                } else {
                    numberString = "" + number;
                }
                sb.append(String.format("%" + biggestNumberOfDecimalPlacesInEachColumn[column] + "s", numberString));
                if (column < nColumns - 1) {
                    sb.append(" ");
                }
            }
            sb.append("]");
            if (row < nRows - 1) {
                sb.append("\n");
            }
        }
        sb.append("]");

        return sb.toString();
    }

}
