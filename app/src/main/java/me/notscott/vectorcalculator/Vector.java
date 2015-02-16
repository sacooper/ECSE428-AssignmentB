package me.notscott.vectorcalculator;

/**
 * Vector class
 */
public class Vector {
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
    public Vector fromCartesian(double x, double y){
        return new Vector(x, y);
    }

    /*****
     * Create a vector from polar components
     * @param r Radius
     * @param theta Angle
     * @return The resulting vector
     */
    public Vector fromPolar(double r, double theta){
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new Vector(x, y);
    }

    /****
     * Compute the scalar (dot) product of this vector with a second vector
     *
     * @param v2 The second vector
     * @return The value of the dot product of this and v2
     */
    public double computeScalarProduct(Vector v2){
        return this.getX() * v2.getX() + this.getY() * v2.getY();
    }

    /*****
     * Compute the cross product of this and v2
     *
     * @param v2 The second vector
     * @return The value of z component of the resuling vector
     */
    public double computeCrossProduct(Vector v2){
        return this.getX() * v2.getY() - this.getY() * v2.getX();
    }

    /****
     * Add this vector to the others. This vector is not changed in the
     * @param v2 Other vector to add to this one
     * @return The resulting vector
     */
    public Vector add(Vector v2){
        return new Vector(this.getX() + v2.getX(), this.getY() + v2.getY());
    }

    /***
     * Return this vector as a string in cartesian coordinates
     * @return A string representation of this vector in cartesian coordinates
     */
    public String asCartesian(){
        return "<" + this.getX() + ", " + this.getY() + ">";
    }

    /***
     * Return this vector as a string in polar coordinates
     * @return This vector as a string in polar coordinates
     */
    public String asPolar(){
        return "<" + this.getR() + ", ∠" + this.getTheta() + ">";
    }

    /***
     * Get the X value of this vector
     * @return The X value of this vector
     */
    public double getX(){
        return this.x;
    }

    /***
     * Get the Y value of this vector
     * @return The Y value of this vector
     */
    public double getY(){
        return this.y;
    }

    /***
     * Get the R value of this polar vector
     * @return The R value of this vector
     */
    public double getR(){
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /****
     * Get the Theta value of this polar vector
     * @return The Theta value of this vector
     */
    public double getTheta(){
        return Math.atan(this.y / this.x);
    }
}

