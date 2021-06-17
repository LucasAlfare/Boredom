package samueltm.boredom.math.linearalgebra

import kotlin.math.sqrt

/**
 * Simple abstraction for two-valued vector.
 *
 * This class represents the most used(ful) operation that can be done with
 * vectors over the context of linear algebra. Also, as convention, the first
 * value of the vector pair is called "x" and the second is called "y".
 *
 * The class also provides a basic mechanism to handle new instances. Once this
 * class could be used in some contexts of intense processing the creation of
 * new instances is not recommended because uses large amount of memory. In order
 * to avoid this, the user can switch the creation of new instances of each
 * operation just by assigning the field {@code createNewInstances} as false (or
 * leave as the standard, which is false). Doing this, the functions will return
 * always perform its operations, update the values of this instance and return
 * itself.
 */
@Suppress("MemberVisibilityCanBePrivate")
class Vector2(var x: Double, var y: Double, var createNewInstances: Boolean = false) {

    /**
     * Returns the Euclidean Length of this vector.
     */
    fun euclideanLength() = sqrt((x * x) + (y * y))

    /**
     * Returns the dot product between this vector and the param {@code v}.
     */
    fun dotProduct(v: Vector2) = (x * v.x) + (y * v.y)

    /**
     * Returns the cross product between this vector and the param {@code v}.
     */
    fun crossProduct(v: Vector2) = (x * v.y) - (y * v.y)

    /**
     * Forces this vector to have its values as [0, 0] and returns itself.
     */
    fun zero() = set(0.0, 0.0, createNewInstances)

    /**
     * Performs vector addition operation between this vector and the param {@code v} and,
     * stores the result in this instance and returns itself.
     */
    fun add(v: Vector2) = set(x + v.x, y + v.y, createNewInstances)

    /**
     * Performs vector subtraction operation between this vector and the param {@code v}
     * and, stores the result in this instance and returns itself.
     */
    fun subtract(v: Vector2) = set(x - v.x, y - v.y, createNewInstances)

    /**
     * Scales the element x of this vector by the scale factor {@code scaleX} and the element
     * y of this vector by the scale factor {@code scaleY}. The result is stored in this instance
     * and returned.
     */
    fun scale(scaleX: Double, scaleY: Double) = set(x * scaleX, y * scaleY, createNewInstances)

    /**
     * Scales each element of this vector with the same scale factor. The result is stored in
     * this instance and returned.
     */
    fun scale(scalar: Double) = set(x * scalar, y * scalar, createNewInstances)

    /**
     * Normalizes this vector and returns itself.
     *
     * A normalized vector is a vector with its values padded in order to match Euclidean Length
     * of 1.
     */
    fun normalized(): Vector2 {
        val len = euclideanLength()
        if (len != 0.0) {
            x /= len
            y /= len
        }
        return set(x, y, createNewInstances)
    }

    /**
     * Returns the distance between this vector and the vector of the param.
     */
    fun distance(v: Vector2): Double {
        val deltaX = v.x - x
        val deltaY = v.y - y
        return sqrt((deltaX * deltaX) + (deltaY * deltaY))
    }

    /**
     * Helper function to set values and return a instance with the values
     * updated.
     */
    fun set(x: Double, y: Double, newInstance: Boolean = false): Vector2 {
        this.x = x
        this.y = y
        return if (newInstance) Vector2(this.x, this.y) else this
    }
}
