package me.notscott.vectorcalculator;

/**
 * Vector class
 */
public class Vector {
    public static final String POLAR_FORMAT = "<%.2f, ∠%.2f>";
    public static final String CARTESIAN_FORMAT = "<%.2f, %.2f>";

    private double x, y;

    /***
     * Create a new vector
     *
     * @param x X Component
     * @param y Y Component
     */
    private Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    /****
     * Create a vector from cartesian components
     *
     * @param x X Component
     * @param y Y Component
     * @return The resulting vector
     */
    public static Vector fromCartesian(double x, double y){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /*****
     * Create a vector from polar components
     * @param r Radius
     * @param theta Angle
     * @return The resulting vector
     */
    public static Vector fromPolar(double r, double theta){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /****
     * Compute the scalar (dot) product of this vector with a second vector
     *
     * @param v2 The second vector
     * @return The value of the dot product of this and v2
     */
    public double computeScalarProduct(Vector v2){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /*****
     * Compute the cross product of this and v2
     *
     * @param v2 The second vector
     * @return The value of z component of the resuling vector
     */
    public double computeCrossProduct(Vector v2){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /****
     * Add this vector to the others. This vector is not changed in the
     * @param v2 Other vector to add to this one
     * @return The resulting vector
     */
    public Vector add(Vector v2){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /***
     * Return this vector as a string in cartesian coordinates
     * @return A string representation of this vector in cartesian coordinates
     */
    public String asCartesian(){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /***
     * Return this vector as a string in polar coordinates
     * @return This vector as a string in polar coordinates
     */
    public String asPolar(){
        throw new UnsupportedOperationException("Not Implemented");
    }
}

