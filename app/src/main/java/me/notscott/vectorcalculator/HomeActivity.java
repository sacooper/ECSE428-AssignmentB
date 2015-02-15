package me.notscott.vectorcalculator;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
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

    public void computeScalarProductClick(View v){

    }

    public void computeVectorProductClick(View v){

    }

    public void addThirdVectorClick(View v){
        if(v.getId() == R.id.btn_add_vector){
            v.setVisibility(View.GONE);
            findViewById(R.id.thirdVector).setVisibility(View.VISIBLE);
        }
    }
}
