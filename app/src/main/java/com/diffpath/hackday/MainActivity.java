package com.diffpath.hackday;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diffpath.hackday.gcm.GcmRegTask;
import com.diffpath.hackday.gcm.GcmUtils;


public class MainActivity extends ActionBarActivity implements GcmRegTask.GcmTaskListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private static final String SENDER_ID = "870124306474";

    //GCM SERVER KEY AIzaSyAEivQlbVQY3uSz1iuoq7p9gTAor8JBKpk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String regid = GcmUtils.getRegistrationId(this);

        if (regid.isEmpty()) {
            Log.d(TAG, "regid is empty");
            new GcmRegTask(this).execute(SENDER_ID);
        } else {
            Log.d(TAG, regid);
            Toast.makeText(this, regid, Toast.LENGTH_LONG).show();
        }

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn1);
        Button btn3 = (Button) findViewById(R.id.btn1);
        Button btn4 = (Button) findViewById(R.id.btn1);
        Button btn5 = (Button) findViewById(R.id.btn1);
        Button btn6 = (Button) findViewById(R.id.btn1);
        Button btn7 = (Button) findViewById(R.id.btn1);
        Button btn8 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId())
        {
            case R.id.btn1:
                break;

            case R.id.btn2:
                break;

            case R.id.btn3:
                break;

            case R.id.btn4:
                break;

            case R.id.btn5:
                break;

            case R.id.btn6:
                break;

            case R.id.btn7:
                break;

            case R.id.btn8:
                break;

            default:
                break;
        }
    }

    @Override
    public void onRegistered(String regId) {

        if (!regId.isEmpty()){
            GcmUtils.storeRegistrationId(this, regId);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
