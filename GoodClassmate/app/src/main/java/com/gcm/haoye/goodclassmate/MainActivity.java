package com.gcm.haoye.goodclassmate;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class MainActivity extends ListActivity {
    public static final int REQUESTCODE = 123;
    private static final String BUNDLEID = "commit";
    protected Button insertbtnobj, clsbtnobj;
    private Context context;
    private CommentsDataSource datasource;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setReqOrientationFunc();
    }

    private View.OnClickListener ClsBtn = new View.OnClickListener(){
        public void onClick(View v){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    datasource = new CommentsDataSource(MainActivity.this);
                    datasource.open();
                    List<Comment> values = datasource.getAllComments();
                    if(values != null) {
                        System.out.println(values);
                        for(int i = 0; i < values.size(); i++){
                            int DataEndNum = Integer.parseInt(values.get(i).getDataend());
                            int WedtNum = Integer.parseInt(values.get(i).getWedt());
                            long DateStartNum = Long.parseLong(values.get(i).getDatestart());
                            Calendar c = Calendar.getInstance();
                            System.out.println(c.getTime().getTime());
                            c.getTime().getTime();
                            ;

                        }
                    }
                }
            };

            Thread t = new Thread(runnable);
            t.start();
        }
    };

    private View.OnClickListener InsertBtn = new View.OnClickListener(){
        public void onClick(View v){

            Intent IntentObj = new Intent();
            IntentObj.setClass(MainActivity.this, Main2Activity.class);
            MainActivity.this.startActivityForResult(IntentObj,REQUESTCODE);

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUESTCODE){
            adapter.list = datasource.getAllComments();
            adapter.notifyDataSetChanged();
        }

    }

    protected void setReqOrientationFunc() {
        /* 設定螢幕不隨手機旋轉 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        /* 設定螢幕直向顯示 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    protected void findViews() {
        this.context = this;
        insertbtnobj = findViewById(R.id.insertbtn);
        clsbtnobj = findViewById(R.id.clsbtn);

        clsbtnobj.setOnClickListener(ClsBtn);
        insertbtnobj.setOnClickListener(InsertBtn);


        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();
        if(values != null) {
        /* use the SimpleCursorAdapter to show the elements in a ListView */
            adapter = new MyAdapter(context,values);

            setListAdapter(adapter);
        }

    }



    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
