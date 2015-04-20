package com.diffpath.hackday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        LinearLayout btn1 = (LinearLayout) findViewById(R.id.btn1);
        LinearLayout btn2 = (LinearLayout) findViewById(R.id.btn2);
        LinearLayout btn3 = (LinearLayout) findViewById(R.id.btn3);
        LinearLayout btn4 = (LinearLayout) findViewById(R.id.btn4);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

        DisplayItem();

    }

    @Override
    public void onClick(View v){
        int btn_no = 0;

        switch (v.getId())
        {
            case R.id.btn1:
                btn_no = 0;
                break;

            case R.id.btn2:
                btn_no = 1;
                break;

            case R.id.btn3:
                btn_no = 2;
                break;

            case R.id.btn4:
                btn_no = 3;
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
        if(requestCode == 500) {
            if(resultCode == RESULT_OK) {
                int btn = intent.getExtras().getInt("btn");
                int action = intent.getExtras().getInt("action");

                SharedPreferences.Editor editor = pref.edit();
                editor.remove(btn + "");
                editor.putInt(btn + "", action);
                editor.commit();

                DisplayItem();
            }
        }


    }

    private void DisplayItem()
    {
        TextView tv1 = (TextView) findViewById(R.id.explain1);
        TextView tv2 = (TextView) findViewById(R.id.explain2);
        TextView tv3 = (TextView) findViewById(R.id.explain3);
        TextView tv4 = (TextView) findViewById(R.id.explain4);

        ImageView iv1 = (ImageView) findViewById(R.id.img1);
        ImageView iv2 = (ImageView) findViewById(R.id.img2);
        ImageView iv3 = (ImageView) findViewById(R.id.img3);
        ImageView iv4 = (ImageView) findViewById(R.id.img4);

        if(pref.getInt("1", -1) != -1)
        {
            tv1.setText(action_lists.get(pref.getInt("1", -1)).getName());
            iv1.setImageResource(action_lists.get(pref.getInt("1", -1)).getImg());
        }

        if(pref.getInt("2", -1) != -1)
        {
            tv2.setText(action_lists.get(pref.getInt("2", -1)).getName());
            iv2.setImageResource(action_lists.get(pref.getInt("2", -1)).getImg());
        }

        if(pref.getInt("3", -1) != -1)
        {
            tv3.setText(action_lists.get(pref.getInt("3", -1)).getName());
            iv3.setImageResource(action_lists.get(pref.getInt("3", -1)).getImg());
        }

        if(pref.getInt("4", -1) != -1)
        {
            tv4.setText(action_lists.get(pref.getInt("4", -1)).getName());
            iv4.setImageResource(action_lists.get(pref.getInt("4", -1)).getImg());
        }


    }

    private void setLists()
    {
        action_lists = new ArrayList<ActionObject>();
        ActionObject obj = new ActionObject();
        obj.setId(0);
        obj.setDescription("휴대전화의 위치를 찾습니다");
        obj.setName("나를 찾아줘");
        obj.setImg(R.drawable.transmission3);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(1);
        obj.setDescription("여자친구에게 문자를 전송합니다");
        obj.setName("간편한 사랑");
        obj.setImg(R.drawable.message20);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(2);
        obj.setDescription("그녀와 늘 함께합니다");
        obj.setName("수지플레이어");
        obj.setImg(R.drawable.television24);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(3);
        obj.setDescription("이젠 글올리기도 귀찮습니다");
        obj.setName("페이스북 작성");
        obj.setImg(R.drawable.pencil108);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(4);
        obj.setDescription("이젠 당신도 인기남녀!");
        obj.setName("까똑");
        obj.setImg(R.drawable.support);
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
