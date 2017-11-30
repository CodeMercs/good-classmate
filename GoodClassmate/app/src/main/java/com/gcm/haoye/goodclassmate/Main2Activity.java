package com.gcm.haoye.goodclassmate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends Activity {
    protected Button submitbtnobj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViews();
    }
    private View.OnClickListener SubmitBtn = new View.OnClickListener(){
        public void onClick(View v){
            Intent IntentObj = new Intent();
            IntentObj.setClass(Main2Activity.this, MainActivity.class);
            Main2Activity.this.startActivity(IntentObj);
            Main2Activity.this.finish();
        }
    };
    protected void findViews() {
        submitbtnobj = findViewById(R.id.submitbtn);
        submitbtnobj.setOnClickListener(SubmitBtn);

    }
}
