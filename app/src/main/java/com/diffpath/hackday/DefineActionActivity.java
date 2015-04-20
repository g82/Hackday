package com.diffpath.hackday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.diffpath.hackday.object.ActionObject;

import java.util.ArrayList;

/**
 * Created by Heedeok on 15. 4. 20..
 */
public class DefineActionActivity extends ActionBarActivity {

    private ArrayList<ActionObject> action_lists;
    private int btn_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define);

        btn_no = getIntent().getIntExtra("btn", 0);

        ListView lv = (ListView) findViewById(R.id.defile_list);
        setLists();

        ActionAdapter adapter = new ActionAdapter(this, android.R.layout.simple_list_item_2, action_lists);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendResult(i);
            }
        });


    }

    private void setLists()
    {
        action_lists = new ArrayList<ActionObject>();
        ActionObject obj = new ActionObject();
        obj.setId(0);
        obj.setDescription("휴대전화의 위치를 찾습니다");
        obj.setName("위치찾기");
        obj.setImg(R.drawable.transmission3);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(1);
        obj.setDescription("여자친구에게 문자를 전송합니다");
        obj.setName("문자전송");
        obj.setImg(R.drawable.message20);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(2);
        obj.setDescription("수지 영상이 나와요");
        obj.setName("영상재생");
        obj.setImg(R.drawable.television24);
        action_lists.add(obj);

        obj = new ActionObject();
        obj.setId(3);
        obj.setDescription("위치정보를 올릴겁니");
        obj.setName("페이스북 작성");
        obj.setImg(R.drawable.pencil108);
        action_lists.add(obj);
    }

    private void sendResult(int i)
    {
        Bundle extra = new Bundle();
        Intent intent = new Intent();

        extra.putInt("btn", btn_no);
        extra.putInt("action", i);
        intent.putExtras(extra);
        this.setResult(RESULT_OK, intent);
        this.finish();
    }

    public class ActionAdapter extends ArrayAdapter<ActionObject>{

        public ArrayList<ActionObject> items;
        private Context mcontext;

        public ActionAdapter(Context context, int textViewResourceId, ArrayList<ActionObject> items) {
            super(context, textViewResourceId, items);
            mcontext = context;
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(android.R.layout.simple_list_item_2, null);
            }

            ActionObject areaInfo = items.get(position);

            if (areaInfo != null) {

                TextView title1 = (TextView) v.findViewById(android.R.id.text1);
                TextView title2 = (TextView) v.findViewById(android.R.id.text2);

                title1.setText(areaInfo.getName());
                title2.setText(areaInfo.getDescription());
            }

            return v;
        }

    }


}
