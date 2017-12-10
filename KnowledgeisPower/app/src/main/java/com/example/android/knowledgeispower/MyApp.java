package com.example.android.knowledgeispower;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MyApp extends Application {

    private int i = 0;
    public int inc() {
        return ++i;

    }


}