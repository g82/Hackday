package com.diffpath.hackday;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Heedeok on 15. 4. 20..
 */
public class DefineActionActivity extends ActionBarActivity {

    private int btn_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_no = getIntent().getIntExtra("btn", 0);

        ListView lv = (ListView) findViewById(R.id.defile_list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        
    }


}
