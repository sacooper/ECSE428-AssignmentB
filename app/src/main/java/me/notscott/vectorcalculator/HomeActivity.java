package me.notscott.vectorcalculator;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class HomeActivity extends Activity {

    public class MissingInputException extends Exception {};

    private AdapterView.OnItemSelectedListener itemClickListener;
    private Operation currentOperation;
    private CoordinateSystem coordinateSystem;
    private Vector additionResult;

    public static final String RESULT_FORMAT = "Result: %s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        itemClickListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentOperation = Operation.fromInt(position);
                changeVisibility();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        Spinner operations = (Spinner) findViewById(R.id.operation);
        operations.setOnItemSelectedListener(itemClickListener);

        this.coordinateSystem = CoordinateSystem.CARTESIAN;

        this.currentOperation = Operation.ADDITION;
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
        this.coordinateSystem = CoordinateSystem.POLAR;

        TextView v1a = (TextView) findViewById(R.id.vector1_a),
                 v1b = (TextView) findViewById(R.id.vector1_b),
                 v2a = (TextView) findViewById(R.id.vector2_a),
                 v2b = (TextView) findViewById(R.id.vector2_b),
                 v3a = (TextView) findViewById(R.id.vector3_a),
                 v3b = (TextView) findViewById(R.id.vector3_b);

        String r = getString(R.string.hint_r),
               t = getString(R.string.hint_theta);

        v1a.setHint(r);
        v1b.setHint(t);
        v2a.setHint(r);
        v2b.setHint(t);
        v3a.setHint(r);
        v3b.setHint(t);
    }

    /***
     * User chooses units as "Cartesian"
     * @param v View that was clicked
     */
    public void changeToCartesianClick(View v){
        this.coordinateSystem = CoordinateSystem.CARTESIAN;

        TextView v1a = (TextView) findViewById(R.id.vector1_a),
                 v1b = (TextView) findViewById(R.id.vector1_b),
                 v2a = (TextView) findViewById(R.id.vector2_a),
                 v2b = (TextView) findViewById(R.id.vector2_b),
                 v3a = (TextView) findViewById(R.id.vector3_a),
                 v3b = (TextView) findViewById(R.id.vector3_b);

        String x = getString(R.string.hint_x),
               t = getString(R.string.hint_y);

        v1a.setHint(x);
        v1b.setHint(t);
        v2a.setHint(x);
        v2b.setHint(t);
        v3a.setHint(x);
        v3b.setHint(t);
    }

    /***
     * Change the visibility based on the current operation
     */
    private void changeVisibility(){
        if (currentOperation == Operation.SCALAR || currentOperation == Operation.CROSS){
            findViewById(R.id.thirdVector).setVisibility(View.GONE);
            findViewById(R.id.btnAddVector).setVisibility(View.GONE);
        } else {
            findViewById(R.id.btnAddVector).setVisibility(View.VISIBLE);
        }
    }

    public void addThirdVectorClick(View v){
        v.setVisibility(View.GONE);
        findViewById(R.id.thirdVector).setVisibility(View.VISIBLE);
    }

    /***
     * Get Vectors input by user
     *
     * @return An ArrayList of vectors input by the user (in order)
     * @throws InvalidParameterException Thrown on invalid input. Any UI changes should be handled by caller
     */
    public ArrayList<Vector> getInput() throws MissingInputException {
        final TextView v1_a = (TextView) findViewById(R.id.vector1_a);
        final TextView v1_b = (TextView) findViewById(R.id.vector1_b);
        final TextView v2_a = (TextView) findViewById(R.id.vector2_a);
        final TextView v2_b = (TextView) findViewById(R.id.vector2_b);
        final TextView v3_a = (TextView) findViewById(R.id.vector3_a);
        final TextView v3_b = (TextView) findViewById(R.id.vector3_b);

        if (v1_a.getText().toString().equals("") || v1_b.getText().toString().equals("")
            || v2_a.getText().toString().equals("") || v2_b.getText().toString().equals("")) {
            throw new MissingInputException();
        }

        Vector v1 = null, v2 = null, v3 = null;
        switch (this.coordinateSystem){
            case POLAR:
                v1 = Vector.fromPolar(
                    Double.parseDouble(v1_a.getText().toString()),
                    Double.parseDouble(v1_b.getText().toString()));
                v2 = Vector.fromPolar(
                    Double.parseDouble(v2_a.getText().toString()),
                    Double.parseDouble(v2_b.getText().toString()));
                if (!v3_a.getText().toString().equals("") && !v3_b.getText().toString().equals("")){
                    v3 = Vector.fromPolar(
                            Double.parseDouble(v3_a.getText().toString()),
                            Double.parseDouble(v3_b.getText().toString()));}
                break;
            case CARTESIAN:
                v1 = Vector.fromCartesian(
                    Double.parseDouble(v1_a.getText().toString()),
                    Double.parseDouble(v1_b.getText().toString()));
                v2 = Vector.fromCartesian(
                    Double.parseDouble(v2_a.getText().toString()),
                    Double.parseDouble(v2_b.getText().toString()));
                if (!v3_a.getText().toString().equals("") && !v3_b.getText().toString().equals("")){
                    v3 = Vector.fromCartesian(
                            Double.parseDouble(v3_a.getText().toString()),
                            Double.parseDouble(v3_b.getText().toString()));}
                break;
        }

        ArrayList<Vector> input = new ArrayList<>();
        input.add(v1);
        input.add(v2);
        if (v3 != null)
            input.add(v3);

        return input;
    }

    public void viewGraphClick(View v){
        Vector result = getResult();
        Dialog test = new Dialog(this);
        GraphView graph = new GraphView(this);
        graph.setLayoutParams(new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
        graph.setTitle(((TextView)findViewById(R.id.lblResult)).getText().toString());

        LineGraphSeries<DataPoint> series;
        if (result.getX() < 0){
            series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(result.getX(), result.getY()),
                    new DataPoint(0, 0)
            });
        } else {
            series = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 0),
                    new DataPoint(result.getX(), result.getY())
            });
        }

        series.setDrawDataPoints(true);
        graph.addSeries(series);

        test.requestWindowFeature(Window.FEATURE_NO_TITLE);
        test.setContentView(graph);
        test.show();
    }

    public void computeResult(View v){
        ArrayList<Vector> inputs;

        try {
            inputs = getInput();
        } catch (MissingInputException e) {
            return;
        }

        TextView lblResult = (TextView) findViewById(R.id.lblResult);
        lblResult.setVisibility(View.VISIBLE);

        String result = "";

        this.additionResult = null;

        switch (getCurrentOperation()){
            case ADDITION:
                Vector res = inputs.get(0).add(inputs.get(1));
                if (inputs.size() == 3)
                    res = res.add(inputs.get(2));

                this.additionResult = res;

                switch(this.coordinateSystem){
                    case POLAR:
                        result = res.asPolar();
                        break;
                    case CARTESIAN:
                        result = res.asCartesian();
                        break;
                }
                break;
            case SCALAR:
                result = "" + inputs.get(0).scalarProduct(inputs.get(1));
                break;
            case CROSS:
                result = "" + inputs.get(0).crossProduct(inputs.get(1));
                break;
        }

        if (getCurrentOperation() == Operation.ADDITION) {
            findViewById(R.id.btnView).setVisibility(View.VISIBLE);}
        else {
            findViewById(R.id.btnView).setVisibility(View.GONE);}

        lblResult.setText(String.format(this.RESULT_FORMAT, result));
    }

    public Operation getCurrentOperation(){
        return this.currentOperation;
    }

    private Vector getResult(){
        return this.additionResult;
    }
}
