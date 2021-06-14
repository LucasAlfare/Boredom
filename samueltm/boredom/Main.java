package samueltm.boredom;


import samueltm.boredom.math.linearalgebra.Matrix2D;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static Matrix2D generateMatrix(int nRows, int nColumns) {
        final double[] numbers = new double[nRows * nColumns];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(1, 11);
        }
        return new Matrix2D(numbers, nRows, nColumns);
    }

    public static void main(String[] args) {
        Matrix2D a = generateMatrix(2,3);
        Matrix2D b = generateMatrix(3,5);
        System.out.println(a);
        System.out.println(b);
        System.out.println(a.multiply(b));
    }
}
