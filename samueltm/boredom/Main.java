package samueltm.boredom;


import samueltm.boredom.math.linearalgebra.Matrix2D;
import samueltm.boredom.miscellaneous.RandomStuff;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static Matrix2D generateMatrix(int nRows, int nColumns) {
        Matrix2D matrix = new Matrix2D(new double[nRows * nColumns], nRows, nColumns);
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                matrix.getArray()[i][j] = ThreadLocalRandom.current().nextInt(1, 11);
            }
        }

        return matrix;
    }

    private static void testMatrixMultiplicationTime() {
        Matrix2D a = generateMatrix(1000, 1000);
        Matrix2D b = generateMatrix(1000, 1000);
        long startTime = System.nanoTime();
        a.multiply(b);
        double timeElapsed = (System.nanoTime() - startTime) / 1e6;
        System.out.println("Time elapsed: " + timeElapsed + " ms");
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(RandomStuff.generateRandomIntegersBetween(10, 1, 100)));
    }
}
