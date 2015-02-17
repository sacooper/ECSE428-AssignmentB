package me.notscott.vectorcalculator;

import junit.framework.TestCase;

/**
 * Created by scott on 2/17/15.
 */
public class VectorTest extends TestCase {

    public VectorTest(){
    }

    public void testCartesianToCartesian(){
        double x = 10.0, y = 10.0;
        Vector v = Vector.fromCartesian(x ,y);
        assertEquals("Error cartesian -> cartesian", v.asCartesian(), String.format(Vector.CARTESIAN_FORMAT, x, y));
    }

    public void testPolarToPolar(){
        double r = 10.0, theta = .67;
        Vector v = Vector.fromPolar(r ,theta);
        assertEquals("Error polar -> polar", v.asPolar(), String.format(Vector.POLAR_FORMAT, r, theta));
    }

    public void testCartesianToPolar(){
        double x = 10.0, y = 10.0;
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(y/x);
        Vector v = Vector.fromCartesian(x ,y);
        assertEquals("Error cartesian -> polar", v.asPolar(), String.format(Vector.POLAR_FORMAT, r, theta));
    }

    public void testPolarToCartesian(){
        double r = 10, theta = 0.67;
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        Vector v = Vector.fromPolar(r, theta);
        assertEquals("Error polar -> cartesian", v.asCartesian(), String.format(Vector.CARTESIAN_FORMAT, x, y));
    }

    public void testPolarAddition(){
        double r1 = 5, r2 = 3, r3 = 7;
        double t1 = .3, t2 = .09, t3 = .23;

        Vector v1 = Vector.fromPolar(r1, t1),
               v2 = Vector.fromPolar(r2, t2),
               v3 = Vector.fromPolar(r3, t3);
    }

    public void testCartesianAddition(){
        fail("Unimplemented");
    }

    public void testPolarScalarProduct(){
        fail("Unimplemented");
    }

    public void testCartesianScalarProduct(){
        fail("Unimplemented");
    }

    public void testPolarVectorProduct(){
        fail("Unimplemented");
    }

    public void testCartesianVectorProduct(){
        fail("Unimplemented");
    }
}
