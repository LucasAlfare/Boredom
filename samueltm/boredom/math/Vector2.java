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
@SuppressWarnings("All")
public class Vector2 {

    public double x = 0d;
    public double y = 0d;

    public Vector2() {
        /* pass */
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

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

    public double eucLen() {
        return Math.sqrt((x * x) + (y * y));
    }

    public Vector2 normalized() {
        final double len = eucLen();

        if (len > 1d) {
            x /= len;
            y /= len;
        }

        return this;
    }

    public double dot(final Vector2 v) {
        return (this.x * v.x) + (this.y * v.y);
    }

    public double cross(final Vector2 v) {
        return (this.x * v.y) - (this.y * v.y);
    }

    public Vector2 add(final Vector2 v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    public Vector2 sub(final Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    public Vector2 scale(final double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2 scale(final Vector2 v) {
        this.x *= v.x;
        this.y *= v.y;
        return this;
    }

    public double distance(final Vector2 v) {
        final double xDiff = v.x - this.x;
        final double yDiff = v.y - this.y;
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    public Vector2 lInterp(final Vector2 v, final double percentile) {
        final double inverse = 1d - percentile;
        this.x = (this.x * inverse) + (v.x * percentile);
        this.y = (this.y * inverse) + (v.y * percentile);
        return this;
    }

    public double[] toDoubleArray() {
        return new double[]{x, y};
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
