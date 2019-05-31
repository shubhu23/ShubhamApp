package com.example.shubhamapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class second_activity extends AppCompatActivity {
    //define the index and he array of images
    int index=0;
    int[] images=new int[]{R.drawable.imgone,R.drawable.imgtwo,R.drawable.imgthree,R.drawable.imgfour};
    ImageView imageView;
    ImageButton bckbtn;


    // Button backbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);

        bckbtn= findViewById(R.id.back_btn);
        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(second_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }




    public void slide(View view){
        //define the imageview slide
        imageView=findViewById(R.id.image_slider);
        //change the image when the button is click
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_animation);
        imageView.startAnimation(animation);
        imageView.setImageResource(images[index]);
        index++;
        //reset the index to 0
        if(index==images.length)
        {
            index=0;
        }
    }
}
