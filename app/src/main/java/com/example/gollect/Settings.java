package com.example.gollect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class Settings extends AppCompatActivity {

    public Button returnButton;
    public Switch themeSwitch;

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        returnButton = (Button) findViewById(R.id.returnHomeBtn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeOld.class);
                startActivity(intent);
            }
        });

    }
}