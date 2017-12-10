package com.example.android.knowledgeispower;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by pc on 12/7/2017.
 */

public class splash extends Activity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;

    public static long SPLASH_TIME_OUT= 7000;
    public static MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        mediaPlayer = MediaPlayer.create(this, R.raw.magic);
        mediaPlayer.start();

        imageView1 = (ImageView) findViewById(R.id.splash1);
        ((AnimationDrawable) imageView1.getDrawable()).start();

        imageView2 = (ImageView) findViewById(R.id.splash2);
        ((AnimationDrawable) imageView2.getDrawable()).start();

        imageView3 = (ImageView) findViewById(R.id.splash3);
        ((AnimationDrawable) imageView3.getDrawable()).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent (splash.this, MainActivity.class);
                startActivity(homeIntent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }
}