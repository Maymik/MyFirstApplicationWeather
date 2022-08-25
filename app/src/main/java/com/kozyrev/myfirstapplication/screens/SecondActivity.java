package com.kozyrev.myfirstapplication.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kozyrev.myfirstapplication.R;

public class SecondActivity extends AppCompatActivity {


@Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       Intent intent = getIntent();
       if (intent !=null){
           Bundle e = intent.getExtras();
           if (e != null){
               String mike = getIntent().getExtras().getString("mykey1");

           }
       }
       String mike = getIntent().getExtras().getString("mykey1");
        setContentView(R.layout.activity_second);
        TextView tv1 = findViewById(R.id.second_activity_btn_1);
        tv1.setBackgroundColor(getColor(android.R.color.holo_red_light));
       LinearLayout linearLayout = new LinearLayout(this);

        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(this);
       textView.setText("Linear");
        textView.setTextSize(30);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(100, 100, 0, 0);
       textView.setLayoutParams(layoutParams);

       linearLayout.addView(textView);

        //setContentView(linearLayout);
    }
}
