package com.example.android.knowledgeispower;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by pc on 12/3/2017.
 */

public class ResultPage extends AppCompatActivity{
    public static MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultpage);


        Bundle bundle = getIntent().getExtras();
        Integer score = bundle.getInt("score");
        displayScore(score);

        String sUsername = bundle.getString("sUsername");
        displayName(sUsername);

        String sEmail = bundle.getString("sEmail");
        displayEmail(sEmail);
    }
    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.result_score);
        scoreView.setText(String.valueOf(score));

    }
   public void displayName(String sUsername) {
      TextView Username = (TextView) findViewById(R.id.result_name);
       Username.setText(String.valueOf(sUsername));

   }
    public void displayEmail(String sUsername) {
        TextView Email = (TextView) findViewById(R.id.result_email);
        Email.setText(String.valueOf(sUsername));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playbutton, menu);
        return true;
    }


    public void pause(MenuItem item) {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();

        }
    }

    //code for stopping music. after stop give the greensleeves again to reload so it can play later
    //automatically
    public void stop(MenuItem item) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, R.raw.greensleeves);



    }

    //code for playing music
    public void play(MenuItem item) {

        mediaPlayer = MediaPlayer.create(this, R.raw.greensleeves);

        mediaPlayer.start();
    }


}
