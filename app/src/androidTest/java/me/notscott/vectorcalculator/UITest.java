package me.notscott.vectorcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Tests to verify UI functionality
 */
public class UITest extends ActivityInstrumentationTestCase2<HomeActivity> {

    public UITest(){
        super(HomeActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        this.setActivityInitialTouchMode(true);
    }

    /***
     * Test that the text hints change to polar
     * coordinates when user changes to polar coordinates
     */
    public void testHintsChangeToPolar(){
        setCoordinateSystem(CoordinateSystem.POLAR);
        assertTrue(UIInPolar());
    }

    /***
     * Test that the UI changes to cartesian coordinates when the
     * user changes back to cartesian coordinates from polar coordinate
     */
    public void testHintsChangeBackToCartesian(){
        testHintsChangeToPolar();
        setCoordinateSystem(CoordinateSystem.CARTESIAN);
        assertTrue(UIIntCartesian());
    }

    /***
     * Return whether the text boxes are in polar coordinates
     * @return
     */
    private boolean UIInPolar(){
        String r = getActivity().getString(R.string.hint_r),
               t = getActivity().getString(R.string.hint_theta);

        TextView v1a = (TextView) getActivity().findViewById(R.id.vector1_a),
                 v1b = (TextView) getActivity().findViewById(R.id.vector1_b),
                 v2a = (TextView) getActivity().findViewById(R.id.vector2_a),
                 v2b = (TextView) getActivity().findViewById(R.id.vector2_b),
                 v3a = (TextView) getActivity().findViewById(R.id.vector3_a),
                 v3b = (TextView) getActivity().findViewById(R.id.vector3_b);

        return v1a.getHint().toString().equals(r) && v1b.getHint().toString().equals(t) &&
               v2a.getHint().toString().equals(r) && v2b.getHint().toString().equals(t) &&
               v3a.getHint().toString().equals(r) && v3b.getHint().toString().equals(t);
    }

    /***
     * Return whether the text boxes are in cartesian coordinates
     * @return
     */
    private boolean UIIntCartesian(){
        String x = getActivity().getString(R.string.hint_x),
               y = getActivity().getString(R.string.hint_y);

        TextView v1a = (TextView) getActivity().findViewById(R.id.vector1_a),
                 v1b = (TextView) getActivity().findViewById(R.id.vector1_b),
                 v2a = (TextView) getActivity().findViewById(R.id.vector2_a),
                 v2b = (TextView) getActivity().findViewById(R.id.vector2_b),
                 v3a = (TextView) getActivity().findViewById(R.id.vector3_a),
                 v3b = (TextView) getActivity().findViewById(R.id.vector3_b);

        return v1a.getHint().toString().equals(x) && v1b.getHint().toString().equals(y) &&
               v2a.getHint().toString().equals(x) && v2b.getHint().toString().equals(y) &&
               v3a.getHint().toString().equals(x) && v3b.getHint().toString().equals(y);
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

}
