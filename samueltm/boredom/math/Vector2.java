package samueltm.boredom.math;

import java.util.Objects;

/**
 * Class that encapsulates the behavior of a vector of fixed amount of items, in this case, two items.
 * <p>
 * The values of this vector are named {@code x} and {@code y}. Also, this class provides a variety of operations
 * that can be made using vectors.
 * <p>
 * Beyond this, the methods are written in chaining/builder patterns, where each method returns a reference for the same
 * instance itself after its calculations.
 * <p>
 * Finally, this class was created for study proposes and/or any kind of incoming graphic work.
 *
 * @author Francisco Lucas, 09 april 2021.
 */
public class Vector2 {

    /**
     * The field that holds the main x/left/first value of this vector.
     */
    public double x = 0d;

    /**
     * The field that holds the main y/right/second value of this vector.
     */
    public double y = 0d;

    /**
     * Creates a new Vector2 with values set to 0.
     */
    public Vector2() {
        /* pass */
    }

    /**
     * Creates a new Vector2 and initializes it with the passed arguments.
     *
     * @param x The initial value of the x field.
     * @param y The initial value of the y field.
     */
    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector based on values from another vector. This is equivalent to making a copy from the vector in
     * the argument.
     *
     * @param v The vector that have the initial values.
     */
    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Creates a new Vector2 based in the values from a double array.
     * <p>
     * This will work only if the array in arguments have length higher than 2, otherwise, a
     * {@code IllegalArgumentException} is thrown.
     * <p>
     * Also, is highly recommended use a array of length equals to 2, once if the length is high, {@code RuntimeException}
     * is thrown.
     * <p>
     * Notice that higher lengths doesn't break the execution of a application, once the x and y values will be assigned
     * to the first and second items from the array of the argument.
     *
     * @param arr The array containing the values to be assigned into x and y fields.
     */
    public Vector2(double[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Can't create a Vector2 using a null array.");
        }

        if (arr.length < 2) {
            throw new IllegalArgumentException("Can't create a Vector2 from a array with length < 2.");
        }

        if (arr.length > 2) {
            throw new RuntimeException(
                    "The array used to create a Vector have more than two elements. Only the values" +
                            "[" + arr[0] + ", " + arr[1] + "] will be used to create the Vector2 instance.");
        }

        this.x = arr[0];
        this.y = arr[1];
    }

    /**
     * Used to get the Euclidean Length of this vector.
     * <p>
     * Some reference: https://www.cs.utexas.edu/users/flame/laff/alaff/chapter01-vector-2-norm.html#:~:text=The%20length%20of%20a%20vector,discussed%20in%20the%20next%20unit.
     *
     * @return The value corresponding to the Euclidean Length.
     */
    public double eucLen() {
        return Math.sqrt((x * x) + (y * y));
    }

    /**
     * Gets the Euclidean Distance between the values in arguments and the values of this vector.
     *
     * @param x The x value to measure the distance.
     * @param y The y value to measure the distance.
     * @return The value corresponding to the Euclidean Distance from the arguments to this vector.
     */
    public double distance(double x, double y) {
        final double xDiff = x - this.x;
        final double yDiff = y - this.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    /**
     * Returns the Euclidean Distance from the vector of the argument to this vector.
     *
     * @param v The vector to measure the distance from.
     * @return The value corresponding to the Euclidean Distance from the vector of the argument and this vector.
     */
    public double distance(Vector2 v) {
        final double xDiff = v.x - this.x;
        final double yDiff = v.y - this.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    /**
     * Performs a Linear Interpolation between the values from arguments and this vector using the
     * {@code percentile} factor.
     * <p>
     * Note that percentile, essentially, should be in range 0 to 1, once this method doesn't automatically clamps this
     * number to that range.
     *
     * @return This same vector instance with updated values.
     */
    public Vector2 lInterp(double x, double y, double percentile) {
        final double inverse = 1d - percentile;
        this.x = (this.x * inverse) + (x * percentile);
        this.y = (this.y * inverse) + (y * percentile);
        return this;
    }

    /**
     * Performs a Linear Interpolation between the values of the vector from argument and this vector using the
     * {@code percentile} factor.
     * <p>
     * Note that percentile, essentially, should be in range 0 to 1, once this method doesn't automatically clamps this
     * number to that range.
     *
     * @return This same vector instance with updated values.
     */
    public Vector2 lInterp(Vector2 v, double percentile) {
        final double inverse = 1d - percentile;
        this.x = (this.x * inverse) + (v.x * percentile);
        this.y = (this.y * inverse) + (v.y * percentile);
        return this;
    }

    /**
     * This function normalizes this vector. If the length is equals to 0, nothing is made.
     * <p>
     * The resulting vector will be "pointing" to the same direction, however its values will be modified in a way to
     * the length match the value of 1.
     *
     * @return This same vector instance with updated values.
     */
    public Vector2 normalized() {
        final double len = eucLen();

        if (len != 0d) {
            x /= len;
            y /= len;
        }

        //TODO: check the case len equals to 1d?
        //TODO: create a property to mark if this was previously normalized to avoid recalculating?

        return this;
    }

    /**
     * Sums/adds the values from the arguments to the values in this vector.
     *
     * @param x The x value to be summed.
     * @param y The y value to be summed.
     * @return This same vector instance with updated values.
     */
    public Vector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Sums/adds the values from the vector of the argument to the values of this current vector.
     *
     * @return This same vector instance with updated values.
     */
    public Vector2 add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    /**
     * Subtracts the values from the arguments from the values in this vector.
     *
     * @param x The x value to be subtracted.
     * @param y The y value to be subtracted.
     * @return This same vector instance with updated values.
     */
    public Vector2 sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Subtracts the values from of the vector in argument from the values of this current vector.
     *
     * @return This same vector instance with updated values.
     */
    public Vector2 sub(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    /**
     * Returns the dot product between the values from arguments and the values of this vector.
     *
     * @param x The x value to be used in the dot product.
     * @param y The y value to be used in the dot product.
     * @return The value corresponding to the dot product.
     */
    public double dot(double x, double y) {
        return (this.x * x) + (this.y * y);
    }

    /**
     * Returns the dot product between the values of the vector of the arguments and the values of this vector.
     *
     * @param v The vector with the values to be used in dot product.
     * @return The dot product between the two related vectors.
     */
    public double dot(Vector2 v) {
        return (this.x * v.x) + (this.y * v.y);
    }

    /**
     * Scales this vector by the amount of {@code scalar} argument.
     *
     * @return This same vector instance with its values scaled.
     */
    public Vector2 scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    /**
     * Scales {@code x} and {@code y} individually by the amounts of the arguments.
     *
     * @param xScale The factor to scale the x value from this vector.
     * @param yScale The factor to scale the y value from this vector.
     * @return This same vector instance with its values scaled.
     */
    public Vector2 scale(double xScale, double yScale) {
        this.x *= xScale;
        this.y *= yScale;
        return this;
    }

    /**
     * Scales {@code x} and {@code y} individually by the amounts from the vector in the argument.
     *
     * @param v The vector that contains the values to scale this vector.
     * @return This same vector instance with its values scaled.
     */
    public Vector2 scale(Vector2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    /**
     * Returns the cross product between the values in arguments and the values of this vector.
     *
     * @param x The x value to be used in the cross product.
     * @param y The y value to be used in the cross product.
     * @return The value of the cross product between the values and this vector instance.
     */
    public double cross(double x, double y) {
        return (this.x * y) - (this.y * x);
    }

    /**
     * Returns the cross product between the values from the vector in the argument and this vector.
     *
     * @param v The vector that contains the values used in cross product.
     * @return The cross product between the two related vectors.
     */
    public double cross(Vector2 v) {
        return (this.x * v.y) - (this.y * v.y);
    }

    /**
     * Convenience function that converts this vector to a array object format, where each item from the new array
     * is x and y values, in the respectively indexes.
     *
     * @return a array of length 2 with the two fixed values from this vector.
     */
    public double[] toDoubleArray() {
        return new double[]{x, y};
    }

    /**
     * Compares two Vector2 instances.
     * <p>
     * This comparison method was automatically generated by the IntelliJ Idea IDE.
     *
     * @param o The other object to be compared to this Vector2 instance.
     * @return {@code true} if the two objects have x and y values equals and {@code false} if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Vector2 vector2 = (Vector2) o;
        return Double.compare(vector2.x, x) == 0 && Double.compare(vector2.y, y) == 0;
    }

    /**
     * Generates a Hashcode for this Vector2 instance, based in the x and y fields.
     * <p>
     * This comparison method was automatically generated by the IntelliJ Idea IDE.
     *
     * @return The hashcode corresponding to this instance state.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Custom string representation for this vector.
     *
     * @return The representation of this vector in the string format "(x, y)" (without the quotes, of course).
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
