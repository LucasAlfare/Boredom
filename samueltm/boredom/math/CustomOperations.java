package samueltm.boredom.math;

public class CustomOperations {

    public static double nthRoot(double base, double root) {
        return Math.pow(base, 1.0 / root);
    }

    public static double geometricMean(double[] numbers) {
        double product = 1;
        for (double n : numbers) {
            product *= n;
        }
        return nthRoot(product, numbers.length);
    }

    public static double arithmeticMean(double[] numbers) {
        double sum = 0;
        for(double n : numbers) {
            sum += n;
        }
        return sum / numbers.length;
    }
}
