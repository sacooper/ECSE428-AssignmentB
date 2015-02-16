package me.notscott.vectorcalculator;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by scott on 2/16/15.
 */
public class UIUnitTest extends ActivityUnitTestCase<HomeActivity> {
    private Intent launchIntent;

    public UIUnitTest(){
        super(HomeActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchIntent = new Intent(getInstrumentation().getTargetContext(), HomeActivity.class);
    }

    /****
     * Testing of the radio button preconditions
     */
    public void testRadioButtonPreconditions() {
        startActivity(launchIntent, null, null);

        // Testing
        final RadioButton rdbCartesian = (RadioButton) getActivity().findViewById(R.id.rdbCartesian);
        final RadioButton rdbPolar = (RadioButton) getActivity().findViewById(R.id.rdbPolar);

        assertEquals("Cartesian Radio Button Is Not Checked", rdbCartesian.isChecked(), true);
        assertEquals("Polar Radio Button Is Checked", rdbPolar.isChecked(), false);
    }

    /****
     * Testing of the Input Text Area preconditions
     */
    public void testInputPreconditions(){
        startActivity(launchIntent, null, null);

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

    /****
     * Testing Add Vector Preconditions
     */
    public void testHiddenPreconditions(){
        startActivity(launchIntent, null, null);

        final View thirdVector = getActivity().findViewById(R.id.thirdVector);
        final Button addVector = (Button) getActivity().findViewById(R.id.btn_add_vector);

        assertEquals("Third Vector Not Hidden", thirdVector.getVisibility(), View.GONE);
        assertEquals("Add Vector Hidden", addVector.getVisibility(), View.VISIBLE);
    }

    /****
     * Testing initial operation
     */
    public void testOperationPreconditions(){
        startActivity(launchIntent, null, null);
        final Spinner operations = (Spinner) getActivity().findViewById(R.id.operation);
        assertEquals("Spinner not on first item", operations.getSelectedItemId(), 0);
    }
}
