package com.diffpath.hackday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.diffpath.hackday.gcm.GcmRegTask;
import com.diffpath.hackday.gcm.GcmUtils;
import com.diffpath.hackday.object.ActionObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements GcmRegTask.GcmTaskListener, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private ArrayList<ActionObject> action_lists;

    private static final String SENDER_ID = "870124306474";
    private SharedPreferences pref;

    //GCM SERVER KEY AIzaSyAEivQlbVQY3uSz1iuoq7p9gTAor8JBKpk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences("pref", MODE_PRIVATE);

        String regid = GcmUtils.getRegistrationId(this);

        if (regid.isEmpty()) {
            Log.d(TAG, "regid is empty");
            new GcmRegTask(this).execute(SENDER_ID);
        } else {
            Log.d(TAG, regid);
            Toast.makeText(this, regid, Toast.LENGTH_LONG).show();
        }

        setLists();

        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        DisplayItem();

    }

    @Override
    public void onClick(View v){
        int btn_no = 0;
        switch (v.getId())
        {
            case R.id.btn1:
                btn_no = 1;
                break;

            case R.id.btn2:
                btn_no = 2;
                break;

            case R.id.btn3:
                btn_no = 3;
                break;

            case R.id.btn4:
                btn_no = 4;
                break;

            case R.id.btn5:
                btn_no = 5;
                break;

            case R.id.btn6:
                btn_no = 6;
                break;

            case R.id.btn7:
                btn_no = 7;
                break;

            case R.id.btn8:
                btn_no = 8;
                break;

            default:
                break;
        }

        Intent intent = new Intent(MainActivity.this, DefineActionActivity.class);
        intent.putExtra("btn", btn_no);
        startActivityForResult(intent, 500);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode){
            case 500:
                int btn = intent.getExtras().getInt("btn");
                int action = intent.getExtras().getInt("action");

                SharedPreferences.Editor editor = pref.edit();
                editor.remove(btn+"");
                editor.putInt(btn+"", action);
                editor.commit();

                DisplayItem();
                break;
        }
    }

    private void DisplayItem()
    {
        TextView tv1 = (TextView) findViewById(R.id.explain1);
        TextView tv2 = (TextView) findViewById(R.id.explain2);
        TextView tv3 = (TextView) findViewById(R.id.explain3);
        TextView tv4 = (TextView) findViewById(R.id.explain4);
        TextView tv5 = (TextView) findViewById(R.id.explain5);
        TextView tv6 = (TextView) findViewById(R.id.explain6);
        TextView tv7 = (TextView) findViewById(R.id.explain7);
        TextView tv8 = (TextView) findViewById(R.id.explain8);

        if(pref.getInt("1", -1) != -1)
        {
            tv1.setText(action_lists.get(pref.getInt("1", -1)).getName());
        }

        if(pref.getInt("2", -1) != -1)
        {
            tv2.setText(action_lists.get(pref.getInt("2", -1)).getName());
        }

        if(pref.getInt("3", -1) != -1)
        {
            tv3.setText(action_lists.get(pref.getInt("3", -1)).getName());
        }

        if(pref.getInt("4", -1) != -1)
        {
            tv4.setText(action_lists.get(pref.getInt("4", -1)).getName());
        }

        if(pref.getInt("5", -1) != -1)
        {
            tv5.setText(action_lists.get(pref.getInt("5", -1)).getName());
        }

        if(pref.getInt("6", -1) != -1)
        {
            tv6.setText(action_lists.get(pref.getInt("6", -1)).getName());
        }

        if(pref.getInt("7", -1) != -1)
        {
            tv7.setText(action_lists.get(pref.getInt("7", -1)).getName());
        }

        if(pref.getInt("8", -1) != -1)
        {
            tv1.setText(action_lists.get(pref.getInt("8", -1)).getName());
        }
    }

    private void setLists()
    {
        action_lists = new ArrayList<ActionObject>();
        ActionObject obj = new ActionObject();
        obj.setId(0);
        obj.setDescription("휴대전화의 위치를 찾습니다");
        obj.setName("위치찾기");
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(1);
        obj.setDescription("여자친구에게 문자를 전송합니다");
        obj.setName("문자전송");
        action_lists.add(obj);
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
