package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.internal.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graph extends AppCompatActivity {

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;

    private FirebaseAuth mAuth;

    private static final String TAG = "Graph";

    private static final String SET_LABEL = "Item Types";
    private  String[] TYPES = new String [256];
    private  int[] TYPESNUMBER = new int [256];
    boolean found = false;
    private BarChart chart;

    private  int RealCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getMode) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.Theme_Gollect);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference userItems = ref.child("user-collections").child(mAuth.getUid()).child(key).child(key);
        userItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                for (DataSnapshot itemSnapshot : dataSnapShot.getChildren()) {

                    String cType = itemSnapshot.child("itemType").getValue(String.class);
                    if(TYPES != null){
                    for(String x : TYPES){
                        if(x != null)
                        {
                            if(x.equals(cType) ){
                                found = true;
                                break;
                            }
                        }

                    }
                    if(found){
                        TYPESNUMBER[Arrays.asList(TYPES).indexOf(cType)] += 1;
                    }
                  else{
                        TYPES[RealCount] = cType;
                        TYPESNUMBER[RealCount] += 1;
                      RealCount +=1;

                    }
                    }
                }


                chart = findViewById(R.id.fragment_horizontalbarchart_chart);

                BarData data = createChartData();
                configureChartAppearance();
                prepareChartData(data);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void configureChartAppearance() {
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);


        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularityEnabled(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {


                return TYPES[(int) value];
            }
        });
    }

    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < RealCount; i++) {
            float x = i;

            Random random  = new Random();
            float y =  (TYPESNUMBER[i]);

            Log.d(TAG, "Value Addedd : ");
            values.add(new BarEntry(x, y));
        }
        BarDataSet set1 = new BarDataSet(values, SET_LABEL);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        BarData data = new BarData(dataSets);

        return data;
    }

    private void prepareChartData(BarData data) {
        data.setValueTextSize(12f);
        chart.setData(data);
        chart.invalidate();
    }
}


// float y =  random.nextInt(MAX_Y_VALUE - MIN_Y_VALUE) + MIN_Y_VALUE;