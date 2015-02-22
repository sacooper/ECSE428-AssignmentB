package me.notscott.vectorcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by scott on 2/19/15.
 */
public class ComputeTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    public ComputeTest(){
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(true);
    }

    /***
     * Make sure the user can see the result of the computation.
     */
    public void testResultVisible(){
         double x1 = 10.4, x2 = 15.1,
           y1 = 19.5, y2 = 11.5;

        setOperation(Operation.ADDITION);
        setCoordinateSystem(CoordinateSystem.CARTESIAN);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();
        assertTrue(resultStringVisible());
    }

    /***
     * Test coputing addition of 2 vectors
     */
    public void testComputeAddition2(){
        double x1 = 10.4, x2 = 15.1,
               y1 = 19.5, y2 = 11.5;

        Vector v1 = Vector.fromCartesian(x1, y1),
               v2 = Vector.fromCartesian(x2, y2);

        Vector expected = v1.add(v2);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();
        String result = getResultString();

        assertEquals(String.format(HomeActivity.RESULT_FORMAT, expected.asCartesian()), result);
    }

    /***
     * Test computing addition of 3 vectors
     */
    public void testComputeAddition3(){
        double x1 = 10.4, x2 = 15.1, x3 = 19.4,
                y1 = 19.5, y2 = 11.5, y3 = 25.1;

        Vector v1 = Vector.fromCartesian(x1, y1),
                v2 = Vector.fromCartesian(x2, y2),
                v3 = Vector.fromCartesian(x3, y3);

        Vector expected = v1.add(v2).add(v3);

        setVector3Visible();

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        setVector3Text(x3 + "", y3 + "");

        compute();
        String result = getResultString();

        assertEquals(String.format(HomeActivity.RESULT_FORMAT, expected.asCartesian()), result);
    }

    /***
     * Test computing scalar product of 2 vectors
     */
    public void testComputeScalar(){
        double x1 = 10.4, x2 = 15.1,
                y1 = 19.5, y2 = 11.5;

        Vector v1 = Vector.fromCartesian(x1, y1),
                v2 = Vector.fromCartesian(x2, y2);

        double expected = v1.scalarProduct(v2);

        setOperation(Operation.SCALAR);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();

        String result = getResultString();

        assertEquals(String.format(HomeActivity.RESULT_FORMAT, expected), result);
    }

    /***
     * Test computing vector product of 2 vectors
     */
    public void testComputeVectorProduct(){
        double x1 = 10.4, x2 = 15.1,
                y1 = 19.5, y2 = 11.5;

        Vector v1 = Vector.fromCartesian(x1, y1),
                v2 = Vector.fromCartesian(x2, y2);

        double expected = v1.crossProduct(v2);

        setOperation(Operation.CROSS);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();

        String result = getResultString();

        assertEquals(String.format(HomeActivity.RESULT_FORMAT, expected), result);
    }

    /***
     * Test that result is in correct units based on user selection
     */
    public void testComputeWithPolar(){
        double r1 = 10.4, t1 = 0.14,
               r2 = 24.1, t2 = 0.27;

        Vector v1 = Vector.fromPolar(r1, t1),
               v2 = Vector.fromPolar(r2, t2);

        Vector expected = v1.add(v2);

        setCoordinateSystem(CoordinateSystem.POLAR);

        setVector1Text(r1 + "", t1 + "");
        setVector2Text(r2 + "", t2 + "");

        compute();

        String result = getResultString();

        assertEquals(String.format(HomeActivity.RESULT_FORMAT, expected.asPolar()), result);
    }

    /***
     * Test that user can choose to visualize vector after addition operation
     */
    public void testVisualizeVectorVisibleWhenAdd(){
        double x1 = 10.4, x2 = 15.1,
                y1 = 19.5, y2 = 11.5;

        setOperation(Operation.ADDITION);
        setCoordinateSystem(CoordinateSystem.CARTESIAN);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();
        assertTrue(viewVectorVisible());
    }

    /***
     * Test that a user cannot visualize the result of scalar product
     */
    public void testVisualizeVectorHiddenWhenNotAdd(){
        double x1 = 10.4, x2 = 15.1,
                y1 = 19.5, y2 = 11.5;

        setOperation(Operation.SCALAR);
        setCoordinateSystem(CoordinateSystem.CARTESIAN);

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        compute();
        assertFalse(viewVectorVisible());
    }

    /***
     * Set the text of vector 1. Will fail if the textview is not visible
     * @param x X coordinate of vector 1 (r in Polar)
     * @param y Y coordinate of vector 1 (theta in Polar)
     */
    private void setVector1Text(final String x, final String y){
        final TextView v1a = ((TextView) getActivity().findViewById(R.id.vector1_a));
        final TextView v1b = ((TextView) getActivity().findViewById(R.id.vector1_b));
        if (!v1a.isShown() || !v1b.isShown())
            fail();

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        v1a.requestFocus();
                        v1a.setText(x);
                        v1b.requestFocus();
                        v1b.setText(y);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();
    }

    /***
     * Set the text of vector 2. Will fail if the textview is not visible
     * @param x X coordinate of vector 1 (r in Polar)
     * @param y Y coordinate of vector 1 (theta in Polar)
     */
    private void setVector2Text(final String x, final String y){
        final TextView v2a = ((TextView) getActivity().findViewById(R.id.vector2_a));
        final TextView v2b = ((TextView) getActivity().findViewById(R.id.vector2_b));

        if (!v2a.isShown() || !v2b.isShown())
            fail();

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        v2a.requestFocus();
                        v2a.setText(x);
                        v2b.requestFocus();
                        v2b.setText(y);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();
    }

    /***
     * Set the text of vector 3
     * @param x X coordinate of vector 1 (r in Polar)
     * @param y Y coordinate of vector 1 (theta in Polar)
     */
    private void setVector3Text(final String x, final String y){
        final TextView v3a = ((TextView) getActivity().findViewById(R.id.vector3_a));
        final TextView v3b = ((TextView) getActivity().findViewById(R.id.vector3_b));
        if (!v3a.isShown() || !v3b.isShown())
            fail();

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {

                        v3a.requestFocus();
                        v3a.setText(x);
                        v3b.requestFocus();
                        v3b.setText(y);

                    }
                }
        );

        getInstrumentation().waitForIdleSync();
    }

    /***
     * Set the coordinate system to be in use
     * @param sys The coordinate system to use
     */
    private void setCoordinateSystem(final CoordinateSystem sys){
        final RadioButton rdbPolar = (RadioButton) getActivity().findViewById(R.id.rdbPolar);
        final RadioButton rdbCartesian = (RadioButton) getActivity().findViewById(R.id.rdbCartesian);

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        switch (sys){
                            case POLAR:
                                rdbPolar.performClick();
                                break;
                            case CARTESIAN:
                                rdbCartesian.performClick();
                                break;
                        }
                    }
                }
        );

        getInstrumentation().waitForIdleSync();
    }

    /***
     * Set the operation to be used
     * @param op The operation to use
     */
    private void setOperation(final Operation op){
        final Spinner s = ((Spinner)getActivity().findViewById(R.id.operation));

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        s.requestFocus();
                        s.setSelection(op.getValue(), true);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();
    }

    /***
     * Make vector 3 visible. Fails if the button to make vector 3 visible
     * is not visible itself
     */
    public void setVector3Visible(){
        final Button addThirdVector = (Button) getActivity().findViewById(R.id.btnAddVector);
        if (addThirdVector.isShown()){
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    addThirdVector.performClick();
                }
            });
            getInstrumentation().waitForIdleSync();
        } else fail();
    }

    /***
     * Execute the current computation in the current coordinate system as
     * seen by HomeActivity
     */
    public void compute(){
        final Button btnCompute = (Button) getActivity().findViewById(R.id.btnCompute);
        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        btnCompute.performClick();
                    }
                }
        );
        getInstrumentation().waitForIdleSync();
    }

    /***
     * Get the correct result string
     *
     * @return The result string
     */
    public String getResultString(){
        return ((TextView) getActivity().findViewById(R.id.lblResult)).getText().toString();
    }

    /***
     * Get whether the result is visible.
     * @return
     */
    private boolean resultStringVisible() {
        return getActivity().findViewById(R.id.lblResult).isShown();
    }

    /***
     * Check if the user can choose to visualize the vector
     */
    private boolean viewVectorVisible(){
        return (getActivity().findViewById(R.id.btnView)).isShown();
    }
}
