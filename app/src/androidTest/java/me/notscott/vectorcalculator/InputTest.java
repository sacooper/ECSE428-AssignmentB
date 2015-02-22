package me.notscott.vectorcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Tests to check that the user can enter input and the expected action occurs
 */
public class InputTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    public InputTest(){
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(true);
    }

    /***
     * Test inputing two vectors
     */
    public void testInputTwoVectorsCartesian(){

        final double x1 = 15.2, x2 = 18.8,
                     y1 = 11.5, y2 = 22.9;

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");


        Vector v1_expected = Vector.fromCartesian(x1, y1),
               v2_expected = Vector.fromCartesian(x2, y2);

        ArrayList<Vector> result = null;
        try {
            result = getActivity().getInput();
        } catch (HomeActivity.MissingInputException e) {}

        assertEquals(v1_expected, result.get(0));
        assertEquals(v2_expected, result.get(1));
    }

    /***
     * Test inputting two vectors in polar coordinate system
     */
    public void testInputTwoVectorsPolar(){

        final double r1 = 15.2, r2 = 18.8,
                t1 = .45, t2 = .89;

        setCoordinateSystem(CoordinateSystem.POLAR);
        setVector1Text(r1 + "", t1 + "");
        setVector2Text(r2 + "", t2 + "");

        Vector v1_expected = Vector.fromPolar(r1, t1),
               v2_expected = Vector.fromPolar(r2, t2);

        ArrayList<Vector> result = null;
        try {
            result = getActivity().getInput();
        } catch (HomeActivity.MissingInputException e) {}

        assertEquals(v1_expected, result.get(0));
        assertEquals(v2_expected, result.get(1));
    }

    /***
     * Test input 3 vectors is possible when operations set to addition
     */
    public void testInputThreeVectors(){
        double x1 = 10.6, x2 = 12.5, x3 = 14.1,
               y1 = 10.7, y2 = 15.7, y3 = 19.3;

        setCoordinateSystem(CoordinateSystem.CARTESIAN);
        setOperation(Operation.ADDITION);

        setVector3Visible();

        setVector1Text(x1 + "", y1 + "");
        setVector2Text(x2 + "", y2 + "");
        setVector3Text(x3 + "", y3 + "");

        Vector v1_expected = Vector.fromCartesian(x1, y1),
               v2_expected = Vector.fromCartesian(x2, y2),
               v3_expected = Vector.fromCartesian(x3, y3);

        ArrayList<Vector> expected = new ArrayList<>();
        expected.add(v1_expected);
        expected.add(v2_expected);
        expected.add(v3_expected);

        ArrayList<Vector> results = null;
        try {
            results = getActivity().getInput();
        } catch (HomeActivity.MissingInputException e) {}

        for (int i = 0; i < 3; i++){
            assertEquals(expected.get(i), results.get(i));
        }

    }

    /***
     * Test that setting the operation to addition from UI changes operation.
     */
    public void testSetOperationAddition(){
        setOperation(Operation.ADDITION);
        assertEquals(Operation.ADDITION, getActivity().getCurrentOperation());
    }

    /***
     * Test that setting the operation to scalar from UI changes operation.
     */
    public void testSetOperationScalar(){
        setOperation(Operation.SCALAR);
        assertEquals(Operation.SCALAR, getActivity().getCurrentOperation());
    }

    /***
     * Test that setting the operation from UI changes operation. Works
     * from changes made in previous test
     */
    public void testSetOperationVectorProduct(){
        setOperation(Operation.CROSS);
        assertEquals(Operation.CROSS, getActivity().getCurrentOperation());
    }

    /***
     * Test that the user can only enter 2 vectors when changing operation
     * to Scalar Product
     */
    public void testOperationScalarOnlyTwoVectorsVisible(){
        setOperation(Operation.SCALAR);
        assertFalse(this.vector3Visible() || this.addThirdVectorVisible());
    }

    /***
     * Test that the user can only enter 2 vectors when changing operation
     * to Cross Product
     */
    public void testOperationCrossOnlyTwoVectorsVisible(){
        setOperation(Operation.CROSS);
        assertFalse(this.vector3Visible() || this.addThirdVectorVisible());
    }

    /***
     * Test that after changing the operation to scalar, and
     * making it impossible to enter data into third vector,
     * the user can enter 3 vectors after changing back to addition.
     */
    public void testChangeBackToAdditionThreeVectorsPossible(){
        setOperation(Operation.SCALAR);
        setOperation(Operation.ADDITION);
        assertTrue(addThirdVectorVisible());
    }

    /***
     * Test that an exception is raised if the user leaves all input blank.
     * getInput should throw an
     */
    public void testLeaveAllInputBlank(){
        setVector1Text("", "");
        setVector2Text("", "");
        setVector3Text("", "");

        Exception exception = null;
        ArrayList<Vector> input = null;

        try {
            input = getActivity().getInput();
        } catch (Exception e){
            exception = e;
        }

        assertNull(input);
        assertTrue(exception instanceof HomeActivity.MissingInputException);

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
            return;

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

    public void setVector3Visible(){
        final Button addThirdVector = (Button) getActivity().findViewById(R.id.btnAddVector);
        if (addThirdVector.isShown()){
            getActivity().runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    addThirdVector.performClick();
                }
            });
        } else fail();
    }

    private boolean vector3Visible(){
        return getActivity().findViewById(R.id.vector3_a).isShown()
               || getActivity().findViewById(R.id.vector3_b).isShown();
    }

    private boolean addThirdVectorVisible(){
        return getActivity().findViewById(R.id.btnAddVector).isShown();
    }
}
