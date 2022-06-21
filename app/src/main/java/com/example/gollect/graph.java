package com.example.gollect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;

public class graph extends AppCompatActivity {

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
    }
}