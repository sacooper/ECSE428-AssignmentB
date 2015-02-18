package me.notscott.vectorcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class HomeActivity extends Activity {
    private AdapterView.OnItemSelectedListener itemClickListener;
    private Operation currentOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        itemClickListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        Spinner operations = (Spinner) findViewById(R.id.operation);
        operations.setOnItemSelectedListener(itemClickListener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /***
     * User chooses units as "Polar"
     * @param v View that was clicked
     */
    public void changeToPolarClick(View v){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /***
     * User chooses units as "Cartesian"
     * @param v View that was clicked
     */
    public void changeToCartesianClick(View v){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /***
     *
     * @param o
     */
    private void changeVisibility(Operation o){
        throw new UnsupportedOperationException("Not Implemented");
    }

    public void addThirdVectorClick(View v){
        throw new UnsupportedOperationException("Not Implemented");
    }

    /***
     * Get Vectors input by user
     *
     * @return An ArrayList of vectors input by the user (in order)
     * @throws InvalidParameterException Thrown on invalid input. Any UI changes should be handled by caller
     */
    public ArrayList<Vector> getInput() throws InvalidParameterException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    public void computeResult(View v){
        throw new UnsupportedOperationException("Not Implemented");
    }
}
