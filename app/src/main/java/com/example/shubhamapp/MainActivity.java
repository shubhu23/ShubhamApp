package com.example.shubhamapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button slidebutton,infobutton;
    MediaPlayer am;
    Thread timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createonobject();
        operationonobject();

        //background music
        am= MediaPlayer.create(MainActivity.this,R.raw.soundtrack);
        am.start();
        timer=new Thread(){
            public void run()
            {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        am.release();
        finish();
    }


    private void operationonobject() {
        slidebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent slider=new Intent(MainActivity.this,second_activity.class);
                startActivity(slider);
            }
        });
        infobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addinfo=new Intent(MainActivity.this,third_activity.class);
                startActivity(addinfo);
            }
        });
    }

    private void createonobject() {
        slidebutton=findViewById(R.id.slider);
        infobutton=findViewById(R.id.info);
    }

}
