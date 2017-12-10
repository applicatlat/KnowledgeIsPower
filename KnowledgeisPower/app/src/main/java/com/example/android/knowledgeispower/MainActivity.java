package com.example.android.knowledgeispower;

import android.Manifest;
import android.app.AlertDialog;

import android.bluetooth.le.ScanRecord;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;


import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public String sUsername;
    public String sEmail;
    public String answer1;
    public String answer2;
    public String answer3;
    public String answer4;
    public String answer5;
    public String lang;


    //In order to use the mediaPlayer object I created a static one i looked the explanation from the stackflow.


// for each answer

    int score1;
    int score2;
    int score3;
    int score4;
    int score5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for music file I opened a raw file under res. then copied this code from developer.android.com.
        //I changed the code with the help of static global object

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);


    }

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon,Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    public void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mServ.stopMusic();

        doUnbindService();
        finish();

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        //When I rotated screen the selected language was changing. I solved it by setLocale(); I checked it from stackflow
    setLocale();
    }
    // However I found the solution of saving language with a string. I found it by myself.
    public void setLocale() {


            Locale locale = new Locale(lang); //Here i wrote lang instead of any language.
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    public void langbut1(View view) {
        Button cont = (Button) findViewById(R.id.nextPage);
        RadioButton turko = (RadioButton) findViewById(R.id.turkBut);
        Button cont1 = (Button) findViewById(R.id.nextPage1);

        boolean checked = ((RadioButton) view).isChecked();

        if (((RadioButton) view).isChecked()) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.engBut:
                    if (checked) {

                        cont.setVisibility(View.VISIBLE);
                        Locale locale = new Locale("en");
                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

                        // Here i defined lang as english if the button selected i did same for the turkish
                        lang = "en";
                        Toast.makeText(this, getResources().getString(R.string.language_toast), Toast.LENGTH_SHORT).show();
                        turko.setChecked(false);
                        cont1.setVisibility(View.INVISIBLE);
                        break;
                    }
            }
        }
    }
            public void langbut2(View view) {
                RadioButton engo = (RadioButton) findViewById(R.id.engBut);
                Button cont = (Button) findViewById(R.id.nextPage);

                Button cont1 = (Button) findViewById(R.id.nextPage1);
                boolean checked = ((RadioButton) view).isChecked();

                if (((RadioButton) view).isChecked()) {
                    // Check which radio button was clicked
                    switch (view.getId()) {
                        case R.id.turkBut:
                            if (checked) {
                                cont1.setVisibility(View.VISIBLE);
                                Locale locale = new Locale("tr");
                                Locale.setDefault(locale);
                                Configuration config = new Configuration();
                                config.locale = locale;
                                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                                lang = "tr";
                                Toast.makeText(this, getResources().getString(R.string.language_toast), Toast.LENGTH_SHORT).show();
                                engo.setChecked(false);
                                cont.setVisibility(View.INVISIBLE);

                                break;

                            }
                    }
                }
            }



    // code for pause button to pause music and play it again

// FOR ROTATE SCREEN RESTART PROBLEM
    public void next(View view) {
        setContentView(R.layout.introduction);

    }

    public void pause(MenuItem item){
        mServ.pauseMusic();

    }

    public void stop(MenuItem item){
        mServ.stopMusic();

    }

    public void play(MenuItem item){

        mServ.resumeMusic();

    }



// Code for menu buttons to appear on action bar

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.playbutton, menu);
        return true;
    }


// code to make application work in english. I copied from
    // https://stackoverflow.com/questions/39158164/android-change-app-locale-manually-on-button-click
    // and did




    // code for going back to home page

    public void geri(View view) {
        setContentView(R.layout.activity_main);
    }

    // code on check box to make the continue button appear and disappear if unchecked


    public void checkbox_understood(View view) {
        Button contbut = (Button) findViewById(R.id.contbut);
        if (((CheckBox) view).isChecked()) {
            contbut.setVisibility(View.VISIBLE);
        } else {
            contbut.setVisibility(View.INVISIBLE);

        }
    }

    //CODES FOR QUIZ ANSWERS
    //code on make continue button appear in quiz
    //radio button 1-5
    // I just wanted to try a different way, it seems more neat but I did most in a different way.

    public void a11(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        boolean checked = ((RadioButton) view).isChecked();

        if (((RadioButton) view).isChecked()) {
            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.answer1:
                    if (checked) {
                        contans1.setVisibility(View.VISIBLE);

                        answer1 = getString(R.string.an11);
                        score1=0;

                        break;
                    } else {
                        contans1.setVisibility(View.INVISIBLE);
                        answer1 = getString(R.string.durum);
                        score1=0;
break;
                    }
                case R.id.answer2:
                    if (checked) {
                        contans1.setVisibility(View.VISIBLE);
                        answer1 = getString(R.string.an12);
                        score1=0;

                        break;

                    } else {
                        contans1.setVisibility(View.INVISIBLE);
                        answer1 = getString(R.string.durum);
                        score1=0;
                        break;
                    }

                case R.id.answer3:
                    if (checked) {
                        contans1.setVisibility(View.VISIBLE);
                        answer1 = getString(R.string.an13);
                        score1=0;

                        break;
                    } else {
                        contans1.setVisibility(View.INVISIBLE);
                        answer1 = getString(R.string.durum);
                        score1=0;
                        break;
                    }
                case R.id.answer4:
                    if (checked) {
                        contans1.setVisibility(View.VISIBLE);
                    answer1 = getString(R.string.an14);
                        score1=0;

                        break;
            } else{
                    contans1.setVisibility(View.INVISIBLE);
                    answer1 = getString(R.string.durum);
                    score1=0;
                        break;
                }
                case R.id.answer5:
                    if (checked) {
                        contans1.setVisibility(View.VISIBLE);
                        answer1 = getString(R.string.an15);
                        score1 = 20;
                        break;
                    }           else{
                        contans1.setVisibility(View.INVISIBLE);
                        answer1 = getString(R.string.durum);
                        break;
                    }
            }
        }
        // gets the string of this question if selected and transfer it to public
    }



    //radio button 2-5
    public void a21(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer2 = getString(R.string.an21);
            score2=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer2 = getString(R.string.durum);
            score2=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a22(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer2 = getString(R.string.an22);
            score2 = 20;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer2 = getString(R.string.durum);
            score2=0;

        }
        // gets the string of this question if selected and transfer it to public
        //correct answer


    }

    public void a23(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer2 = getString(R.string.an23);
            score2=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer2 = getString(R.string.durum);
            score2=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a24(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer2 = getString(R.string.an24);
            score2=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer2 = getString(R.string.durum);
            score2=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a25(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer2 = getString(R.string.an25);
            score2=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer2 = getString(R.string.durum);
            score2=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    //radio button 3-5

    public void a31(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer3 = getString(R.string.an31);
            score3=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer3 = getString(R.string.durum);
            score3=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a32(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer3 = getString(R.string.an32);
            score3=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer3 = getString(R.string.durum);
            score3=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a33(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer3 = getString(R.string.an33);
            score3=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer3 = getString(R.string.durum);
            score3=0;

        }

        // gets the string of this question if selected and transfer it to public
    }

    public void a34(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer3 = getString(R.string.an34);
            score3=0;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer3 = getString(R.string.durum);
            score3=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a35(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer3 = getString(R.string.an35);
            score3 = 20;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer3 = getString(R.string.durum);
            score3=0;

        }
        // gets the string of this question if selected and transfer it to public


    }

    //radio button 4-5

    public void a41(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer4 = getString(R.string.an41);
            score4=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer4 = getString(R.string.durum);
            score4=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a42(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer4 = getString(R.string.an42);
            score4=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer4 = getString(R.string.durum);
            score4=0;

        }
        // gets the string of this question if selected and transfer it to public
    }


    public void a43(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer4 = getString(R.string.an43);
            score4=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer4 = getString(R.string.durum);
            score4=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a44(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer4 = getString(R.string.an44);
            score4=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer4 = getString(R.string.durum);
            score4=0;

        }
        // 4-5 both correct
        // gets the string of this question if selected and transfer it to public
    }

    public void a45(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer4 = getString(R.string.an45);

            score4 = 20;

        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer4 = getString(R.string.durum);
            score4=0;

        }
        //4-5 both correct
        // gets the string of this question if selected and transfer it to public


    }
    //radio button 5-5

    public void a51(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer5 = getString(R.string.an51);
            score5=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer5 = getString(R.string.durum);
            score5=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a52(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer5 = getString(R.string.an52);
            score5=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer5 = getString(R.string.durum);
            score5=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a53(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer5 = getString(R.string.an53);
            score5=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer5 = getString(R.string.durum);
            score5=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a54(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer5 = getString(R.string.an54);
            score5=0;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer5 = getString(R.string.durum);
            score5=0;

        }
        // gets the string of this question if selected and transfer it to public
    }

    public void a55(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
            answer5 = getString(R.string.an55);
            score5 = 20;


        } else {
            contans1.setVisibility(View.INVISIBLE);
            answer5 = getString(R.string.durum);
            score5=0;

        }
        //correct answer
        // gets the string of this question if selected and transfer it to public

    }

    // it will ask if you are sure of the answer
    // it will open the next page
    // question 1 contine button
    public void q1cont(View view) {
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String answerMes = getResources().getString(R.string.answerMes);


        AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
        yesNo.setMessage(answerMes).setCancelable(false)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    //I figured it out by myself
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.quizpage2);


                    }
                });

        AlertDialog alert = yesNo.create();
        alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
        alert.show();


    }

    // question 2 continue button
    public void q2cont(View view) {
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String answerMes = getResources().getString(R.string.answerMes);


        AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
        yesNo.setMessage(answerMes).setCancelable(false)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    //I figured it out by myself
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.quizpage3);

                    }
                });

        AlertDialog alert = yesNo.create();
        alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
        alert.show();

    }

    // question 3 continue button

    public void q3cont(View view) {
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String answerMes = getResources().getString(R.string.answerMes);


        AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
        yesNo.setMessage(answerMes).setCancelable(false)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    //I figured it out by myself
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.quizpage4);

                    }
                });

        AlertDialog alert = yesNo.create();
        alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
        alert.show();

    }

    // question 4 continue button

    public void q4cont(View view) {
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String answerMes = getResources().getString(R.string.answerMes);


        AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
        yesNo.setMessage(answerMes).setCancelable(false)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    //I figured it out by myself
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.quizpage5);

                    }
                });

        AlertDialog alert = yesNo.create();
        alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
        alert.show();

    }

    // question 5 continue button

    public void q5cont(View view) {
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String answerMes = getResources().getString(R.string.answerMes);


        AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
        yesNo.setMessage(answerMes).setCancelable(false)
                .setNegativeButton(no, new DialogInterface.OnClickListener() {
                    @Override
                    //I figured it out by myself
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            setContentView(R.layout.resultpage);
                        Thread t = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    while (!isInterrupted()) {
                                        Thread.sleep(1000);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                TextView tdate = (TextView) findViewById(R.id.date);
                                                long date = System.currentTimeMillis();
                                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy\nhh:mm:ss");
                                                String dateString = sdf.format(date);
                                                tdate.setText(dateString);
                                            }
                                        });
                                    }
                                } catch (InterruptedException e) {
                                }
                            }
                        };
                        t.start();
                        displayScore(score1+score2+score3+score4+score5);
                        displayName(sUsername);
                        displayEmail(sEmail);
                        TextView correct1 = (TextView) findViewById(R.id.correct1);
                        TextView answer1t = (TextView) findViewById(R.id.your_answer1);
                        TextView correct2 = (TextView) findViewById(R.id.correct2);
                        TextView answer2t = (TextView) findViewById(R.id.your_answer2);
                        TextView correct3 = (TextView) findViewById(R.id.correct3);
                        TextView answer3t = (TextView) findViewById(R.id.your_answer3);
                        TextView correct4 = (TextView) findViewById(R.id.correct4);
                        TextView answer4t = (TextView) findViewById(R.id.your_answer4);
                        TextView correct5 = (TextView) findViewById(R.id.correct5);
                        TextView answer5t = (TextView) findViewById(R.id.your_answer5);
                        displayAnswer1(answer1);
                        displayAnswer2(answer2);
                        displayAnswer3(answer3);
                        displayAnswer4(answer4);
                        displayAnswer5(answer5);

                        String true1 = getString(R.string.Correct);
                        String false1= getString(R.string.False);
                        String durum= getString(R.string.durum);


                        if (correct1.getText().equals(answer1t.getText())) {
                            answer1t.setTextColor(Color.parseColor("#1B5E20"));
                            answer1t.setText(true1 + "  " + answer1);

                        }

                        else {            if (answer1t.getText().equals("null")) {
                            answer1t.setTextColor(Color.parseColor("#263238"));
                            answer1t.setText(durum);

                        } else {
                            answer1t.setTextColor(Color.parseColor("#DD2C00"));
                            answer1t.setText(false1 + "  " + answer1);
                        }
                        }


                        if (correct2.getText().equals(answer2t.getText())) {
                            answer2t.setTextColor(Color.parseColor("#1B5E20"
                            ));
                            answer2t.setText(true1 + "  " + answer2);

                        }

                        else {            if (answer2t.getText().equals("null")) {
                            answer2t.setTextColor(Color.parseColor("#263238"));
                            answer2t.setText(durum);

                        } else {
                            answer2t.setTextColor(Color.parseColor("#DD2C00"));
                            answer2t.setText(false1 + "  " + answer2);
                        }

                        }

                        if (correct3.getText().equals(answer3t.getText())) {
                            answer3t.setTextColor(Color.parseColor("#1B5E20"
                            ));
                            answer3t.setText(true1 + "  " + answer3);

                        }


                        else {            if (answer3t.getText().equals("null")) {
                            answer3t.setTextColor(Color.parseColor("#263238"));
                            answer3t.setText(durum);

                        } else {
                            answer3t.setTextColor(Color.parseColor("#DD2C00"));
                            answer3t.setText(false1 + "  " + answer3);
                        }
                        }

                        if (correct4.getText().equals(answer4t.getText())) {
                            answer4t.setTextColor(Color.parseColor("#1B5E20"
                            ));
                            answer4t.setText(true1 + "  " + answer4);

                        }

                        else  {            if (answer4t.getText().equals("null")) {
                            answer4t.setTextColor(Color.parseColor("#263238"));
                            answer4t.setText(durum);

                        } else {
                            answer4t.setTextColor(Color.parseColor("#DD2C00"));
                            answer4t.setText(false1 + "  " + answer4);
                        }
                        }

                        if (correct5.getText().equals(answer5t.getText())) {
                            answer5t.setTextColor(Color.parseColor("#1B5E20"
                            ));
                            answer5t.setText(true1 + "  " + answer5);


                        }

                        else {
                            if (answer5t.getText().equals("null")) {
                                answer5t.setTextColor(Color.parseColor("#263238"));
                                answer5t.setText(durum);

                            } else {
                                answer5t.setTextColor(Color.parseColor("#DD2C00"));
                                answer5t.setText(false1 + "  " + answer5);

                            }
                        }


                    }
                });

        AlertDialog alert = yesNo.create();
        alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
        alert.show();

    }

    // code of continue button in the introduction page to continue in app

    public void contbut1(View view) {
        setContentView(R.layout.submitpage);
    }

    // code of submit page go back link

    public void goback(View view) {
        setContentView(R.layout.activity_main);
    }


    // code of submit page. Change it. Transfers name to result page.
    //Submit tuşuna eklenmesi gerekenler.
    //3- İsim ve e-mail kısımlarına yazılan yazıları result page e aktarsın

    public void submit(View view) {

        EditText submitName = (EditText) findViewById(R.id.editname); //submitpage name section edittext data
           sUsername = submitName.getText().toString();             //gets the edittext data

        String nameError = getResources().getString(R.string.error_submit_name); //string from the string.xml error name down email

        if (sUsername.matches("")) {
            Toast.makeText(this, nameError, Toast.LENGTH_SHORT).show(); // message appears if empty
            return;
        }
        String emailError = getResources().getString(R.string.error_submit_email);

        EditText submitEmail = (EditText) findViewById(R.id.edit_posta);
            sEmail = submitEmail.getText().toString();

        if (sEmail.matches("")) {
            Toast.makeText(this, emailError, Toast.LENGTH_SHORT).show();
            return;
        }

        String control = getResources().getString(R.string.control);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);


        if ((sEmail.matches("") == false) && (sUsername.matches("") == false))
        {
            AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
            yesNo.setMessage(control).setCancelable(false)
                    .setNegativeButton(no, new DialogInterface.OnClickListener() {
                        @Override
                        //I figured it out by myself
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })
                    .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setContentView(R.layout.quizpage1);


                        }
                    });

            AlertDialog alert = yesNo.create();
            alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
            alert.show();

        }


    }


    //Question 1, 5 answer - yapılması gerekenler: 1-Herhangi

// Result page codes

    //Displays the score in the result page

    public void displayScore(int score) {
        TextView scoreView = (TextView) findViewById(R.id.result_score);
        scoreView.setText(String.valueOf(score));

    }



    //display name and email
    public void displayName(String sUsername) {
        TextView Username = (TextView) findViewById(R.id.result_name);
        Username.setText(String.valueOf(sUsername));

    }
    public void displayEmail(String email) {
        TextView Email = (TextView) findViewById(R.id.result_email);
        Email.setText(String.valueOf(email));

    }

    //set answers 1 to 5
    public void displayAnswer1(String answers) {
        TextView answer1t = (TextView) findViewById(R.id.your_answer1);

        answer1t.setText(String.valueOf(answers));

    }
    public void displayAnswer2(String answers) {
        TextView answer2t = (TextView) findViewById(R.id.your_answer2);
        answer2t.setText(String.valueOf(answers));

    }
    public void displayAnswer3(String answers) {
        TextView answer3t = (TextView) findViewById(R.id.your_answer3);
        answer3t.setText(String.valueOf(answers));

    }
    public void displayAnswer4(String answers) {
        TextView answer4t = (TextView) findViewById(R.id.your_answer4);
        answer4t.setText(String.valueOf(answers));
    }

    public void displayAnswer5(String answers) {
        TextView answer5t = (TextView) findViewById(R.id.your_answer5);
        answer5t.setText(String.valueOf(answers));
    }
    // SENDS EMAIL
    public void sendEmail(View v) {


        TextView Email = (TextView) findViewById(R.id.result_email);
        String email = Email.getText().toString();

        TextView Name = (TextView) findViewById(R.id.result_name);
        String name = Name.getText().toString();



        TextView Date = (TextView) findViewById(R.id.date);
        String date = Date.getText().toString();

        TextView YourScore = (TextView) findViewById(R.id.yourScore);
        String yourScore = YourScore.getText().toString();

        TextView ResultScore = (TextView) findViewById(R.id.result_score);
        String resultScore = ResultScore.getText().toString();

        TextView CorrectAnswers = (TextView) findViewById(R.id.correctAnswers);
        String correctAnswers = CorrectAnswers.getText().toString();

        TextView Quest1 = (TextView) findViewById(R.id.quest1);
        String quest1 = Quest1.getText().toString();

        TextView A1 = (TextView) findViewById(R.id.a1); // 1
        String a1 = A1.getText().toString();

        TextView B1 = (TextView) findViewById(R.id.b1);
        String b1 = B1.getText().toString();

        TextView C1 = (TextView) findViewById(R.id.c1);
        String c1 = C1.getText().toString();

        TextView D1 = (TextView) findViewById(R.id.d1);
        String d1 = D1.getText().toString();

        TextView E1 = (TextView) findViewById(R.id.e1);
        String e1 = E1.getText().toString();

        TextView A2 = (TextView) findViewById(R.id.a2); //1
        String a2 = A2.getText().toString();

        TextView B2 = (TextView) findViewById(R.id.correct2);
        String b2 = B2.getText().toString();

        TextView C2 = (TextView) findViewById(R.id.c2);
        String c2 = C2.getText().toString();

        TextView D2 = (TextView) findViewById(R.id.d2);
        String d2 = D2.getText().toString();

        TextView E2 = (TextView) findViewById(R.id.e2);
        String e2 = E2.getText().toString();

        TextView A3 = (TextView) findViewById(R.id.a3); // 1
        String a3 = A3.getText().toString();

        TextView B3 = (TextView) findViewById(R.id.b3);
        String b3 = B3.getText().toString();

        TextView C3 = (TextView) findViewById(R.id.c3);
        String c3 = C3.getText().toString();

        TextView D3 = (TextView) findViewById(R.id.d3);
        String d3 = D3.getText().toString();

        TextView E3 = (TextView) findViewById(R.id.e3);
        String e3 = E3.getText().toString();


        TextView A4 = (TextView) findViewById(R.id.a4); // 1
        String a4 = A4.getText().toString();

        TextView B4 = (TextView) findViewById(R.id.b4);
        String b4 = B4.getText().toString();

        TextView C4 = (TextView) findViewById(R.id.c4);
        String c4 = C4.getText().toString();

        TextView D4 = (TextView) findViewById(R.id.d4);
        String d4 = D4.getText().toString();

        TextView E4 = (TextView) findViewById(R.id.e4);
        String e4 = E4.getText().toString();

        TextView A5 = (TextView) findViewById(R.id.correct1); // 1
        String a5 = A5.getText().toString();

        TextView B5 = (TextView) findViewById(R.id.b5);
        String b5 = B5.getText().toString();

        TextView C5 = (TextView) findViewById(R.id.correct3);
        String c5 = C5.getText().toString();

        TextView D5 = (TextView) findViewById(R.id.correct4);
        String d5 = D5.getText().toString();

        TextView E5 = (TextView) findViewById(R.id.correct5);
        String e5 = E5.getText().toString();


        TextView Source1 = (TextView) findViewById(R.id.source1);
        String source1 = Source1.getText().toString();

        TextView Source2 = (TextView) findViewById(R.id.source2);
        String source2 = Source2.getText().toString();

        TextView Source3 = (TextView) findViewById(R.id.source3);
        String source3 = Source3.getText().toString();

        TextView Source4 = (TextView) findViewById(R.id.source4);
        String source4 = Source4.getText().toString();

        TextView Source5 = (TextView) findViewById(R.id.source5);
        String source5 = Source5.getText().toString();


        TextView yourAnswerTit = (TextView) findViewById(R.id.YA);
        String yourAnswerTitle = yourAnswerTit.getText().toString();

        TextView yourAnswer1 = (TextView) findViewById(R.id.your_answer1);
        String yourAnswer1a = yourAnswer1.getText().toString();

        TextView yourAnswerb = (TextView) findViewById(R.id.your_answer2);
        String yourAnswer1b = yourAnswerb.getText().toString();

        TextView yourAnswerc = (TextView) findViewById(R.id.your_answer3);
        String yourAnswer1c = yourAnswerc.getText().toString();

        TextView yourAnswerd = (TextView) findViewById(R.id.your_answer4);
        String yourAnswer1d = yourAnswerd.getText().toString();

        TextView yourAnswere = (TextView) findViewById(R.id.your_answer5);
        String yourAnswer1e = yourAnswere.getText().toString();

        TextView explain = (TextView) findViewById(R.id.explain);
        String explainTitle = explain.getText().toString();

        TextView Quest2 = (TextView) findViewById(R.id.quest2);
        String quest2 = Quest2.getText().toString();

        TextView Quest3 = (TextView) findViewById(R.id.quest3);
        String quest3 = Quest3.getText().toString();

        TextView Quest4 = (TextView) findViewById(R.id.quest4);
        String quest4 = Quest4.getText().toString();

        TextView Quest5 = (TextView) findViewById(R.id.quest5);
        String quest5 = Quest5.getText().toString();



        String message = date + "\n" + "\n" + name + "\n" + "\n" + email + "\n" + "\n" +
                yourScore + "  " + resultScore + "\n" + "\n" + correctAnswers + "\n" + "\n" +
                quest1 + "\n" + "\n" + "  A) " + a1 + "\n" +  "  B) " + a2  + "\n" + "  C) " + a3  + "\n" + "  D) " + a4
                + "\n" +  "  E) " + a5 + "\n" + "\n" + yourAnswerTitle + "\n" + yourAnswer1a
                + "\n" + "\n" + explainTitle + "\n" + "\n" + source1 + "\n" + "\n" +
                quest2 + "\n" + "\n" + "  A) "+ b1 + "\n" + "  B) " + b2  + "\n" + "  C) " + b3  + "\n" + "  D) " + b4
                + "\n" + "  E) " + b5 + "\n" + "\n" + yourAnswerTitle + "\n" + "\n" + yourAnswer1b
                + "\n" + "\n" + explainTitle + "\n" + "\n" + source2 + "\n" + "\n"  +
                quest3 + "\n" + "\n" + "  A) " + c1 + "\n" + "  B) " + c2  + "\n"  + "  C) " + c3  + "\n" + "  D) " + c4
                + "\n" +  "  E) " + c5 + "\n" + "\n" + yourAnswerTitle + "\n" + "\n" + yourAnswer1c
                + "\n" + "\n" + explainTitle + "\n" + "\n" + source3 + "\n" + "\n"  +
                quest4 + "\n" + "\n" + "  A) " + d1 + "\n" + "  B) " + d2  + "\n" + "  C) " + d3  + "\n" + "  D) " + d4
                + "\n"  + "  E) " + d5 + "\n" + "\n" + yourAnswerTitle + "\n" + "\n" + yourAnswer1d
                + "\n" + "\n" + explainTitle + "\n" + "\n" + source4 + "\n" + "\n"  +
                quest5 + "\n" + "\n" + "  A) " + e1 + "\n" + "  B) " + e2  +  "\n" + "  C) " + e3  + "\n" + "  D) " + e4
                + "\n" + "  E) " + e5 + "\n" + "\n" + yourAnswerTitle + "\n" + "\n" + yourAnswer1e
                + "\n" + "\n" + explainTitle + "\n" + "\n" + source5 + "\n" + "\n"
                ;


        String subject = getString(R.string.emailSubject);

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);
        sm.execute();
    }
    public void goHome (View v){

        String control = getResources().getString(R.string.goBackMes);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);

            AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
            yesNo.setMessage(control).setCancelable(false)
                    .setNegativeButton(no, new DialogInterface.OnClickListener() {
                        @Override
                        //I figured it out by myself
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    })
                    .setPositiveButton(yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);

                        }
                    });

            AlertDialog alert = yesNo.create();
            alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
            alert.show();

        }

    String mailChecker ="";
    public void checkMail(View view) {
        TextView Email = (TextView) findViewById(R.id.result_email);
        String mail = Email.getText().toString();
        if (((CheckBox) view).isChecked()) {
            String mailTitle = getResources().getString(R.string.emailsubmit);

            mailChecker = "\n" + mailTitle +"  "+ mail;
        }
        if (!((CheckBox) view).isChecked()) {
            mailChecker = " ";
        }
    }
    String resultChecker ="";
    public void checkResult(View view) {
        TextView scoreView = (TextView) findViewById(R.id.result_score);
        String result = scoreView.getText().toString();
        if (((CheckBox) view).isChecked()) {
            String yourScr = getResources().getString(R.string.yourScore);

            resultChecker = "\n" + yourScr + " " + result;

        } if (!((CheckBox) view).isChecked()) {
            resultChecker = " ";
        }
    }
    String dateChecker ="";
    public void checkDate(View view) {

        TextView Date = (TextView) findViewById(R.id.date);
        String date = Date.getText().toString();
        if (((CheckBox) view).isChecked()) {
            String dateN = getResources().getString(R.string.date);

            dateChecker = "\n" + dateN + " " + date;
        } if (!((CheckBox) view).isChecked()) {
            dateChecker = " ";
        }
    }

    public void correctOnes(View view) {
        CheckBox date1 = (CheckBox) findViewById(R.id.dateAndTime);
        CheckBox email = (CheckBox) findViewById(R.id.emailCheck);
        CheckBox result1 = (CheckBox) findViewById(R.id.scoreResult);

        TextView scoreView = (TextView) findViewById(R.id.result_score);
        String result = scoreView.getText().toString();

        TextView Email = (TextView) findViewById(R.id.result_email);
        String mail = Email.getText().toString();

        TextView Date = (TextView) findViewById(R.id.date);
        String date = Date.getText().toString();

        if (((CheckBox) view).isChecked()) {
            String mailTitle = getResources().getString(R.string.emailsubmit);
            String yourScr = getResources().getString(R.string.yourScore);
            String dateN = getResources().getString(R.string.date);

            date1.setChecked(true);
            dateChecker = "\n" + dateN + " " + date;

            email.setChecked(true);
            mailChecker = "\n" + mailTitle +" "+ mail;

            result1.setChecked(true);
            resultChecker = "\n" + yourScr + " " + result;

            ((CheckBox) view).setChecked(false);
        }
    }


    public void share(View v) {
        String control = getResources().getString(R.string.chooseDude);
        String no = getResources().getString(R.string.OK);
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);
        CheckBox email = (CheckBox) findViewById(R.id.emailCheck);
        CheckBox date = (CheckBox) findViewById(R.id.dateAndTime);
        CheckBox result = (CheckBox) findViewById(R.id.scoreResult);
        CheckBox correct = (CheckBox) findViewById(R.id.correctOnes);

        if (!(email.isChecked()) && !(date.isChecked()) && !(result.isChecked()) && !(correct.isChecked())) {
            AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);


            yesNo.setMessage(control).setCancelable(false)
                    .setNegativeButton(no, new DialogInterface.OnClickListener() {

                        @Override
                        //I figured it out by myself
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }

                    });
            AlertDialog alert = yesNo.create();
            alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
            alert.show();
            return;

        }
                          String nameTit1 = getResources().getString(R.string.namesubmit);
                        String nameTitle = nameTit1 + " ";
                        String title = getResources().getString(R.string.emailSubject);
                        String picks = title +"\n";
                        String message = picks + nameTitle + sUsername + mailChecker + resultChecker + dateChecker;
                        String shareBody = message ;
                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,getString(R.string.emailSubject) );
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share)));

    }
    //save user info

    public void saveInfo(View view) {

        EditText username1 = (EditText) findViewById(R.id.editname); //submitpage name section edittext data
        EditText email1 = (EditText) findViewById(R.id.edit_posta); //submitpage name section edittext data

        String saveme = getResources().getString(R.string.saved);

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username1.getText().toString());
        editor.putString("email", email1.getText().toString());
        editor.apply();

        Toast.makeText(this, saveme, Toast.LENGTH_LONG).show();

    }


    }




