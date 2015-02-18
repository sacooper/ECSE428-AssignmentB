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
        assertEquals("Error: cartesian -> cartesian", v.asCartesian(), String.format(Vector.CARTESIAN_FORMAT, x, y));
    }

    /****
     * Test creating a Vector from Polar coordinates
     */
    public void testPolarCreation(){
        double r = 10.0, theta = .67;
        Vector v = Vector.fromPolar(r ,theta);
        assertEquals("Error polar -> polar", v.asPolar(), String.format(Vector.POLAR_FORMAT, r, theta));
    }

    /***
     * Unnecessary?
     */
    public void testCartesianToPolar(){
        double x = 10.0, y = 10.0;
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(y/x);
        Vector v = Vector.fromCartesian(x ,y);
        assertEquals("Error cartesian -> polar", v.asPolar(), String.format(Vector.POLAR_FORMAT, r, theta));
    }

    /***
     * Unnecessary?
     */
    public void testPolarToCartesian(){
        double r = 10, theta = 0.67;
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        Vector v = Vector.fromPolar(r, theta);
        assertEquals("Error polar -> cartesian", v.asCartesian(), String.format(Vector.CARTESIAN_FORMAT, x, y));
    }

    /***
     * Test adding 3 vectors created using polar coordinates
     */
    public void testPolarAddition(){
        double r1 = 5, r2 = 3, r3 = 7;
        double t1 = .3, t2 = .09, t3 = .23;

        Vector v1 = Vector.fromPolar(r1, t1),
               v2 = Vector.fromPolar(r2, t2),
               v3 = Vector.fromPolar(r3, t3);
    }

    /***
     * Test adding 3 vectors created using cartesian coordinates
     */
    public void testCartesianAddition(){
        double x1 = 5.0, x2 = 3.6, x3 = 19.5;
        double y1 = 8.9, y2 = 7.2, y3 = 53.2;
        Vector v1 = Vector.fromCartesian(x1, y1),
               v2 = Vector.fromCartesian(x2, y2),
               v3 = Vector.fromCartesian(x3, y3);

        Vector result = v1.add(v2).add(v3);
        Vector expected = Vector.fromCartesian(x1 + x2 + x3, y1 + y2 + y3);
        assertEquals("Error: Addition of Cartesian Vectors", result, expected);
    }

    /***
     * Test taking the scalar product of 2 vectors created using polar coordinates
     */
    public void testPolarScalarProduct(){
        fail("Unimplemented");
    }

    /***
     * Test taking the scalar product of 2 vectors created using cartesian coordinates
     */
    public void testCartesianScalarProduct(){
        fail("Unimplemented");
    }

    /****
     * Test taking the vector product of 2 vectors created using polar coordinates
     */
    public void testPolarVectorProduct(){
        fail("Unimplemented");
    }

    /****
     * Test taking the vector product of 2 vectors created using cartesian coordinates
     */
    public void testCartesianVectorProduct(){
        fail("Unimplemented");
    }
}
