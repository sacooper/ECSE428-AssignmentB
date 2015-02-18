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
        return new Vector(x, y);
    }

    /*****
     * Create a vector from polar components
     * @param r Radius
     * @param theta Angle
     * @return The resulting vector
     */
    public static Vector fromPolar(double r, double theta){
        double x = r * Math.cos(theta),
                y = r * Math.sin(theta);
        return new Vector(x, y);
    }

    /****
     * Compute the scalar (dot) product of this vector with a second vector
     *
     * @param v2 The second vector
     * @return The value of the dot product of this and v2
     */
    public double scalarProduct(Vector v2){
        return this.getX() * v2.getX() + this.getY() * v2.getY();
    }

    /*****
     * Compute the cross product of this and v2
     *
     * @param v2 The second vector
     * @return The value of z component of the resuling vector
     */
    public double crossProduct(Vector v2){
        return this.getX() * v2.getY() - this.getY()*v2.getX();
    }

    /****
     * Add this vector to the others. This vector is not changed in the
     * @param v2 Other vector to add to this one
     * @return The resulting vector
     */
    public Vector add(Vector v2){
        return Vector.fromCartesian(this.getX() + v2.getX(), this.getY() + v2.getY());
    }

    /***
     * Return this vector as a string in cartesian coordinates
     * @return A string representation of this vector in cartesian coordinates
     */
    public String asCartesian(){
        return String.format(Vector.CARTESIAN_FORMAT, this.getX(), this.getY());
    }

    /***
     * Return this vector as a string in polar coordinates
     * @return This vector as a string in polar coordinates
     */
    public String asPolar(){
        return String.format(Vector.POLAR_FORMAT, this.getR(), this.getTheta());
    }

    public double getX() { return x; }

    public double getY() { return y; }

    public double getR() { return Math.sqrt(this.x * this.x + this.y * this.y); }

    public double getTheta() { return Math.atan(y/x); }

    @Override
    public boolean equals(Object o){
        if (o instanceof Vector){
            Vector v2 = (Vector) o;
            return this.getX() == v2.getX() && this.getY() == v2.getY();
        } else return false;
    }
}

