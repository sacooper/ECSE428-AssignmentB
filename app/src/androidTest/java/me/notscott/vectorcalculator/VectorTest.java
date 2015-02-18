package me.notscott.vectorcalculator;

import junit.framework.TestCase;

/**
 * Created by scott on 2/17/15.
 */
public class VectorTest extends TestCase {

    public VectorTest(){}

    /***
        Test creating a Vector from Cartesian coordinates
     */
    public void testCartesianCreation(){
        double x = 10.0, y = 10.0;
        Vector v = Vector.fromCartesian(x ,y);
        assert(v.getX() == x && v.getY() == y);
    }

    /***
     * Test creating a vector from Polar coordinates
     */
    public void testPolarCreation(){
        double r = 5.55, t = .15;
        Vector v = Vector.fromPolar(r, t);
        assert(v.getR() == r && v.getTheta() == t);
    }

    /***
     * Test getting output in cartesian coordinates
     */
    public void testCartesianOutput(){
        double x = 4.5, y = -5.2;
        Vector v = Vector.fromCartesian(x, y);
        String expected = String.format(Vector.CARTESIAN_FORMAT, x, y);
        assertEquals(v.asCartesian(), expected);
    }

    /***
     * Test getting output in polar coordinates
     */
    public void testPolarOutput(){
        double r = 5.1, t = .14;
        Vector v = Vector.fromPolar(r, t);
        String expected = String.format(Vector.POLAR_FORMAT, r, t);
        assertEquals(v.asPolar(), expected);
    }

    /***
     * Test to validate that a vector entered in polar coordinates results in what we would
     * expect if it had been entered as a cartesian vector. Allows us to test operations in a single
     * coordinate system.
     */
    public void testPolarToCartesian(){
        double r = 5.6, t = .67;
        double x_expected = r*Math.cos(t),
               y_expected = r*Math.sin(t);

        Vector v = Vector.fromPolar(r, t);
        assert(v.getX() == x_expected && v.getY() == y_expected);
    }

    /***
     * Test to validate that a vector entered as cartesian results in what we would
     * expect if it had been entered as a polar vector. Allows us to test operations in a single
     * coordinate system.
     */
    public void testCartesianToPolar(){
        double x = 14.5, y = 15.1;
        double r_expected = Math.sqrt(x*x + y*y),
               t_expected = Math.atan(y/x);
        Vector v = Vector.fromCartesian(x, y);
        assert(v.getR() == r_expected && v.getTheta() == t_expected);
    }

    /***
     * Test addition of 3 vectors
     */
    public void testAddVectors(){
        double x1 = 5.0, x2 = 3.6, x3 = 19.5;
        double y1 = 8.9, y2 = 7.2, y3 = 53.2;
        Vector v1 = Vector.fromCartesian(x1, y1),
                v2 = Vector.fromCartesian(x2, y2),
                v3 = Vector.fromCartesian(x3, y3);

        Vector result = v1.add(v2).add(v3);
        Vector expected = Vector.fromCartesian(x1 + x2 + x3, y1 + y2 + y3);
        assertEquals(result, expected);
    }

    /***
     * Test scalar product of 2 vectors
     */
    public void testScalarProduct(){
        double x1 = 4.8, x2 = 12.5,
               y1 = 1.8, y2 = 10.1;

        Vector v1 = Vector.fromCartesian(x1, y1),
               v2 = Vector.fromCartesian(x2, y2);

        double expected = x1*x2 +y1*y2,
               result   = v1.scalarProduct(v2);

        assertEquals(result, expected);
    }

    /***
     * Test cross product of 2 vectors
     */
    public void testVectorProduct(){
        double x1 = 14.5, x2 = 11.5,
               y1 = 19.3, y2 = 17.2;

        Vector v1 = Vector.fromCartesian(x1, y1),
               v2 = Vector.fromCartesian(x2, y2);

        double expected = x1*y2 -x2*y1,
               result = v1.crossProduct(v2);

        assertEquals(expected, result);

    }
}
