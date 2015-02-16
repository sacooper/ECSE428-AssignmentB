package me.notscott.vectorcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

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
                currentOperation = Operation.fromInt(position);
                changeVisibility(currentOperation);
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

    public void changeToPolarClick(View v){
        ((TextView)findViewById(R.id.vector1_a)).setHint(R.string.hint_r);
        ((TextView)findViewById(R.id.vector2_a)).setHint(R.string.hint_r);
        ((TextView)findViewById(R.id.vector3_a)).setHint(R.string.hint_r);
        ((TextView)findViewById(R.id.vector1_b)).setHint(R.string.hint_theta);
        ((TextView)findViewById(R.id.vector2_b)).setHint(R.string.hint_theta);
        ((TextView)findViewById(R.id.vector3_b)).setHint(R.string.hint_theta);
    }

    public void changeToCartesianClick(View v){
        ((TextView)findViewById(R.id.vector1_a)).setHint(R.string.hint_x);
        ((TextView)findViewById(R.id.vector2_a)).setHint(R.string.hint_x);
        ((TextView)findViewById(R.id.vector3_a)).setHint(R.string.hint_x);
        ((TextView)findViewById(R.id.vector1_b)).setHint(R.string.hint_y);
        ((TextView)findViewById(R.id.vector2_b)).setHint(R.string.hint_y);
        ((TextView)findViewById(R.id.vector3_b)).setHint(R.string.hint_y);
    }

    private void changeVisibility(Operation o){
        switch (o){
            case ADDITION:
                findViewById(R.id.btn_add_vector).setVisibility(View.VISIBLE);
                findViewById(R.id.thirdVector).setVisibility(View.GONE);
                break;
            default:
                findViewById(R.id.btn_add_vector).setVisibility(View.GONE);
                findViewById(R.id.thirdVector).setVisibility(View.GONE);
                break;
        }
    }

    public void addThirdVectorClick(View v){
        if(v.getId() == R.id.btn_add_vector){
            v.setVisibility(View.GONE);
            findViewById(R.id.thirdVector).setVisibility(View.VISIBLE);
        }
    }

    public void computeResult(View v){

    }
}
