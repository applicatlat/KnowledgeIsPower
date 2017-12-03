package com.example.android.knowledgeispower;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public String sUsername;
    public String sEmail;

    //In order to use the mediaPlayer object I created a static one i looked the explanation from the stackflow.

    public static MediaPlayer mediaPlayer;

    //If no score points added the score will be 0

    int score;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //for music file I opened a raw file under res. then copied this code from developer.android.com.
        //I changed the code with the help of static global object


        mediaPlayer = MediaPlayer.create(this, R.raw.greensleeves);
        mediaPlayer.start();



    }

    // code for pause button to pause music and play it again


    public void pause(MenuItem item) {

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();

        }
    }

    //code for stopping music. after stop give the greensleeves again to reload so it can play later
    //automatically
    public void stop(MenuItem item) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer = MediaPlayer.create(this, R.raw.greensleeves);


        }
    }

    //code for playing music
    public void play(MenuItem item) {
        mediaPlayer.start();
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

    public void eng(View view) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Toast.makeText(this, getResources().getString(R.string.language_toast), Toast.LENGTH_SHORT).show();
        setContentView(R.layout.introduction);
    }


    // code to make application work in turkish


    public void turk(View v) {
        Locale locale = new Locale("tr");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        Toast.makeText(this, getResources().getString(R.string.language_toast), Toast.LENGTH_SHORT).show();

        setContentView(R.layout.introduction);
    }


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
    public void a11(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a12(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a13(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a14(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a15(View view) {
        Button contans1 = (Button) findViewById(R.id.q1cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);
        }
        // score + 20 will add to the 0 score because this is the correct answer

      score = score + 20;
    }

    //radio button 2-5
    public void a21(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a22(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
        //correct answer
   score = score + 20;

    }

    public void a23(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a24(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a25(View view) {
        Button contans1 = (Button) findViewById(R.id.q2cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    //radio button 3-5

    public void a31(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a32(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a33(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a34(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a35(View view) {
        Button contans1 = (Button) findViewById(R.id.q3cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
     score = score + 20;

    }

    //radio button 4-5

    public void a41(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a42(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a43(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a44(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
        // 4-5 both correct
      score = score + 20;

    }

    public void a45(View view) {
        Button contans1 = (Button) findViewById(R.id.q4cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
        //4-5 both correct
       score = score + 20;

    }
    //radio button 5-5

    public void a51(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a52(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a53(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a54(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
    }

    public void a55(View view) {
        Button contans1 = (Button) findViewById(R.id.q5cont);
        if (((RadioButton) view).isChecked()) {
            contans1.setVisibility(View.VISIBLE);
        } else {
            contans1.setVisibility(View.INVISIBLE);

        }
        //correct answer
     score = score + 20;

    }

    // on click it will make save the answer convert it to int and add to score
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

                        //The score from the quiz is transferred to Resultpage.java with this intent
                        Intent intent = new Intent(MainActivity.this, ResultPage.class);
                        intent.putExtra("score",score);
                        intent.putExtra("sUsername",sUsername);
                        intent.putExtra("sEmail",sEmail);

                        startActivity(intent);


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
        String sUsername = submitName.getText().toString();             //gets the edittext data

        String nameError = getResources().getString(R.string.error_submit_name); //string from the string.xml error name down email

        if (sUsername.matches("")) {
            Toast.makeText(this, nameError, Toast.LENGTH_SHORT).show(); // message appears if empty
            return;
        }
        String emailError = getResources().getString(R.string.error_submit_email);

        EditText submitEmail = (EditText) findViewById(R.id.edit_posta);
        String sEmail = submitEmail.getText().toString();

        if (sEmail.matches("")) {
            Toast.makeText(this, emailError, Toast.LENGTH_SHORT).show();
            return;
        }

        String control = getResources().getString(R.string.control);
        String yes = getResources().getString(R.string.yes);
        String no = getResources().getString(R.string.no);
        String dialogBoxTitle = getResources().getString(R.string.dialogTitle);


        if (sEmail.matches("") == false || (sUsername.matches("") == false)) ;
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




}

