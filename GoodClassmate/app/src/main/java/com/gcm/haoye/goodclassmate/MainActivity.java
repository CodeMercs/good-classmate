package com.gcm.haoye.goodclassmate;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Random;


public class MainActivity extends ListActivity {
    protected Button insertbtnobj, clsbtnobj;
    protected ListView reloglvobj;
    private CommentsDataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setReqOrientationFunc();
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

    protected void setReqOrientationFunc() {
        /* 設定螢幕不隨手機旋轉 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        /* 設定螢幕直向顯示 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    protected void findViews() {

        insertbtnobj = findViewById(R.id.insertbtn);
        clsbtnobj = findViewById(R.id.clsbtn);

        clsbtnobj.setOnClickListener(ClsBtn);
        insertbtnobj.setOnClickListener(InsertBtn);


        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        /* use the SimpleCursorAdapter to show the elements in a ListView */
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                R.layout.test, values);
         setListAdapter(adapter);


    }

    private void onDestory() {
        super.onDestroy();
        System.exit(0);
    }


    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
