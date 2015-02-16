package me.notscott.vectorcalculator;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by scott on 2/16/15.
 */
public class UIFunctionalTest extends ActivityInstrumentationTestCase2<HomeActivity>
{
    public UIFunctionalTest(){
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }

    /***
     * Test that changing vector type to polar also changes hints correctly
     */
    public void testChangeToPolar(){
        TouchUtils.clickView(this, getActivity().findViewById(R.id.rdbPolar));

        final TextView v1_a = (TextView) getActivity().findViewById(R.id.vector1_a);
        final TextView v1_b = (TextView) getActivity().findViewById(R.id.vector1_b);
        final TextView v2_a = (TextView) getActivity().findViewById(R.id.vector2_a);
        final TextView v2_b = (TextView) getActivity().findViewById(R.id.vector2_b);
        final TextView v3_a = (TextView) getActivity().findViewById(R.id.vector3_a);
        final TextView v3_b = (TextView) getActivity().findViewById(R.id.vector3_b);
        assertSame("v1_a hint is wrong", v1_a.getHint(), getActivity().getString(R.string.hint_r));
        assertSame("v1_a hint is wrong", v1_b.getHint(), getActivity().getString(R.string.hint_theta));
        assertSame("v1_a hint is wrong", v2_a.getHint(), getActivity().getString(R.string.hint_r));
        assertSame("v1_a hint is wrong", v2_b.getHint(), getActivity().getString(R.string.hint_theta));
        assertSame("v1_a hint is wrong", v3_a.getHint(), getActivity().getString(R.string.hint_r));
        assertSame("v1_a hint is wrong", v3_b.getHint(), getActivity().getString(R.string.hint_theta));
    }

    /***
     * Test clicking change to cartesian also changes hints correctly
     */
    public void testChangeToCartesian(){
        TouchUtils.clickView(this, getActivity().findViewById(R.id.rdbCartesian));

        final TextView v1_a = (TextView) getActivity().findViewById(R.id.vector1_a);
        final TextView v1_b = (TextView) getActivity().findViewById(R.id.vector1_b);
        final TextView v2_a = (TextView) getActivity().findViewById(R.id.vector2_a);
        final TextView v2_b = (TextView) getActivity().findViewById(R.id.vector2_b);
        final TextView v3_a = (TextView) getActivity().findViewById(R.id.vector3_a);
        final TextView v3_b = (TextView) getActivity().findViewById(R.id.vector3_b);
        assertSame("v1_a hint is wrong", v1_a.getHint(), getActivity().getString(R.string.hint_x));
        assertSame("v1_a hint is wrong", v1_b.getHint(), getActivity().getString(R.string.hint_y));
        assertSame("v1_a hint is wrong", v2_a.getHint(), getActivity().getString(R.string.hint_x));
        assertSame("v1_a hint is wrong", v2_b.getHint(), getActivity().getString(R.string.hint_y));
        assertSame("v1_a hint is wrong", v3_a.getHint(), getActivity().getString(R.string.hint_x));
        assertSame("v1_a hint is wrong", v3_b.getHint(), getActivity().getString(R.string.hint_y));
    }

    /***
     * Test clicking Add Vector exposes third vector correctly and hides buttons
     */
    public void testExposeThirdVector(){
        ((Spinner)getActivity().findViewById(R.id.operation)).setSelection(0, true);
        TouchUtils.clickView(this, getActivity().findViewById(R.id.btn_add_vector));
        final View thirdVector = getActivity().findViewById(R.id.thirdVector);
        final Button addVector = (Button) getActivity().findViewById(R.id.btn_add_vector);
        assertEquals("Third Vector Not Hidden", thirdVector.getVisibility(), View.VISIBLE);
        assertEquals("Add Vector Hidden", addVector.getVisibility(), View.GONE);
    }

    /***
     * Test third vector, and option to add third vector are hidden with cross product
     */
    public void testHiddenOnScalarSelected(){

        final Spinner s = ((Spinner)getActivity().findViewById(R.id.operation));
        final View thirdVector = getActivity().findViewById(R.id.thirdVector);
        final Button addVector = (Button) getActivity().findViewById(R.id.btn_add_vector);

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        s.requestFocus();
                        s.setSelection(1, true);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();
        assertEquals("Third Vector Not Hidden", thirdVector.getVisibility(), View.GONE);
        assertEquals("Add Vector Not Hidden", addVector.getVisibility(), View.GONE);
    }

    /***
     * Test third vector, and option to add third vector are hidden with cross product
     */
    public void testHiddenOnCrossSelected(){
        final Spinner s = ((Spinner)getActivity().findViewById(R.id.operation));
        final View thirdVector = getActivity().findViewById(R.id.thirdVector);
        final Button addVector = (Button) getActivity().findViewById(R.id.btn_add_vector);

        assert(s.getOnItemSelectedListener() != null);

        getActivity().runOnUiThread(
                new Runnable() {
                    public void run() {
                        s.requestFocus();
                        s.setSelection(2, true);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();

        assertEquals("Third Vector Not Hidden", thirdVector.getVisibility(), View.GONE);
        assertEquals("Add Vector Not Hidden", addVector.getVisibility(), View.GONE);

    }
}
