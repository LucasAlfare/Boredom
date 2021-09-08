package samueltm.boredom.math.linearalgebra

import kotlin.math.sqrt

/**
 * Simple abstraction for three-valued vector.
 *
 * This class represents the most used(ful) operation that can be done with
 * vectors over the context of linear algebra. Also, as convention, the first
 * value of the vector triplet is called "x" and the second is called "y" and "z".
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
class Vector3(var x: Double, var y: Double, var z: Double, var createNewInstances: Boolean = false) {

  /**
   * Returns the Euclidean Length of this vector.
   */
  fun euclideanLength() = sqrt((x * x) + (y * y) + (z * z))

  /**
   * Returns the dot product between this vector and the param {@code v}.
   */
  fun dotProduct(v: Vector3) = (x * v.x) + (y * v.y) + (z * v.z)

  /**
   * Returns the cross product between this vector and the param {@code v}.
   */
  fun crossProduct(v: Vector3) = set(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x, createNewInstances)

  /**
   * Forces this vector to have its values as [0, 0] and returns itself.
   */
  fun zero() = set(0.0, 0.0, 0.0, createNewInstances)

  /**
   * Performs vector addition operation between this vector and the param {@code v} and,
   * stores the result in this instance and returns itself.
   */
  fun add(v: Vector3) = set(x + v.x, y + v.y, z + v.z, createNewInstances)

  /**
   * Performs vector subtraction operation between this vector and the param {@code v}
   * and, stores the result in this instance and returns itself.
   */
  fun subtract(v: Vector3) = set(x - v.x, y - v.y, z - v.z, createNewInstances)

  /**
   * Scales the element x of this vector by the scale factor {@code scaleX} and the element
   * y of this vector by the scale factor {@code scaleY}. The result is stored in this instance
   * and returned.
   */
  fun scale(scaleX: Double = 1.0, scaleY: Double = 1.0, scaleZ: Double = 1.0) =
    set(x * scaleX, y * scaleY, z * scaleZ, createNewInstances)

  /**
   * Scales each element of this vector with the same scale factor. The result is stored in
   * this instance and returned.
   */
  fun scale(scalar: Double) = set(x * scalar, y * scalar, z * scalar, createNewInstances)

  /**
   * Takes a matrix of dimensions 3x3 and multiplies it by this vector.
   */
  fun multiply(m: Array<DoubleArray>): Vector3 {
    return set(
      (m[0][0] * x) + (m[0][1] * y) + (m[0][2] * z),
      (m[1][0] * x) + (m[1][1] * y) + (m[1][2] * z),
      (m[2][0] * x) + (m[2][1] * y) + (m[2][2] * z),
      createNewInstances
    )
  }

  /**
   * Normalizes this vector and returns itself.
   *
   * A normalized vector is a vector with its values padded in order to match Euclidean Length
   * of 1.
   */
  fun normalized(): Vector3 {
    val len2 = euclideanLength()
    if (len2 == 0.0 || len2 == 1.0) return this
    return scale(1.0 / sqrt(len2))
  }

  /**
   * Returns the distance between this vector and the vector of the param.
   */
  fun distance(v: Vector3): Double {
    val deltaX = v.x - x
    val deltaY = v.y - y
    val deltaZ = v.z - z
    return sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ))
  }

  /**
   * Operator overloading as a shortcut for the @{code add} function.
   */
  operator fun plus(v: Vector3): Vector3 {
    return add(v)
  }

  /**
   * Operator overloading as a shortcut for the @{code subtract} function.
   */
  operator fun minus(v: Vector3): Vector3 {
    return subtract(v)
  }

  /**
   * Operator overloading as a shortcut for the @{code scale(scalar)} function.
   */
  operator fun times(scalar: Double): Vector3 {
    return scale(scalar)
  }

  /**
   * Helper function to set values and return a instance with the values
   * updated.
   */
  fun set(x: Double, y: Double, z: Double, newInstance: Boolean = false): Vector3 {
    this.x = x
    this.y = y
    this.z = z
    return if (newInstance) Vector3(this.x, this.y, this.z) else this
  }

  override fun toString() = "[$x, $y, $z]"
}
