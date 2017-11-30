package com.gcm.haoye.goodclassmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
    protected Button insertbtnobj, clsbtnobj;
    protected ListView reloglvobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private View.OnClickListener ClsBtn = new View.OnClickListener(){
        public void onClick(View v){
            MainActivity.this.onDestory();
        }
    };

    private View.OnClickListener InsertBtn = new View.OnClickListener(){
        public void onClick(View v){
            Intent IntentObj = new Intent();
            IntentObj.setClass(MainActivity.this, Main2Activity.class);
            MainActivity.this.startActivity(IntentObj);
            MainActivity.this.finish();
        }
    };

    protected void findViews() {

        insertbtnobj = findViewById(R.id.insertbtn);
        clsbtnobj = findViewById(R.id.clsbtn);
        reloglvobj = findViewById(R.id.reloglv);

        clsbtnobj.setOnClickListener(ClsBtn);
        insertbtnobj.setOnClickListener(InsertBtn);



    }

    private void onDestory() {
        super.onDestroy();
        System.exit(0);
    }
}
