package com.example.gollect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class Settings extends AppCompatActivity {

    public SwitchCompat themeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme((R.style.DarkTheme));
        }else {
            setTheme((R.style.Theme_Gollect));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeSwitch = findViewById(R.id.themeSwitchMode);

        Button returnHomeButton = (Button) findViewById(R.id.returnHomeBtn);
        returnHomeButton.setOnClickListener(view -> returnHome());

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            themeSwitch.setChecked(true);
        }
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Reset();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Reset();
                }
            }

            public void Reset() {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void returnHome(){
        Intent intent = new Intent(this, HomeOld.class);
        startActivity(intent);
    }
}