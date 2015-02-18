package me.notscott.vectorcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.TextView;

/**
 * Created by scott on 2/18/15.
 */
public class VectorInputTest extends ActivityInstrumentationTestCase2<HomeActivity> {

    /****
     * Constructor for VectorInputTest - Initializes ActivityInstrumentationTestCase2 with
     * the class it will be performing tests on (HomeActivity)
     */
    public VectorInputTest(){
        super(HomeActivity.class);
    }

    @Override
    /***
     * Set up activity for testing
     */
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }


    /***
     * Test to verify that vector1a is checked
     */
    public void testMissingInput() {
        final TextView v1_a = (TextView) getActivity().findViewById(R.id.vector1_a);
        final TextView v1_b = (TextView) getActivity().findViewById(R.id.vector1_b);
        final TextView v2_a = (TextView) getActivity().findViewById(R.id.vector2_a);
        final TextView v2_b = (TextView) getActivity().findViewById(R.id.vector2_b);
        final TextView v3_a = (TextView) getActivity().findViewById(R.id.vector3_a);
        final TextView v3_b = (TextView) getActivity().findViewById(R.id.vector3_b);

        v1_a.setText("");
        v1_b.setText("2.0");
        v2_a.setText("-4.5");
        v2_a.setText("-3.5");
        v2_b.setText("5.5");



    }


}
