package com.example.gollect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import android.graphics.Color;
import android.os.Bundle;
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
import java.util.Random;

public class Graph extends AppCompatActivity {

    boolean getMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;


    private ListView listView;

    private FirebaseAuth mAuth;
    public ArrayList<Item> itemList = new ArrayList<>();
    private static final String TAG = "Graph";


    private static final int MAX_X_VALUE = 7;
    private static final int MAX_Y_VALUE = 50;
    private static final int MIN_Y_VALUE = 5;
    private static final String SET_LABEL = "App Downloads";
    private static final String[] DAYS = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

    private BarChart chart;

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


        //listView = (ListView) findViewById(R.id.itemList);
        //ItemAdapter adapter = new ItemAdapter(getApplicationContext(), 0, itemList);
//        listView.setAdapter(adapter);












        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference userItems = ref.child("user-collections").child(mAuth.getUid()).child(key).child(key);
        userItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapShot) {
                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapShot.getChildren()) {
                    //Log.d(TAG, itemSnapshot.child("itemName").getValue(String.class));

                    String cType = itemSnapshot.child("itemType").getValue(String.class);


                    String cName = itemSnapshot.child("itemName").getValue(String.class);

                    String cDate = itemSnapshot.child("itemDate").getValue(String.class);
                    String cDescription = itemSnapshot.child("itemDescription").getValue(String.class);
                    com.example.gollect.Item item = new com.example.gollect.Item("" + cName, "" + cType, "" + cDate, "" + cDescription);
                    // Log.d(TAG, item.itemName);
                    //itemList.add(item);

                    // adapter.notifyDataSetChanged();


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

        XAxis xAxis = chart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return DAYS[(int) value];
            }
        });
    }

    private BarData createChartData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = 0; i < MAX_X_VALUE; i++) {
            float x = i;
            Random random  = new Random();
            float y =  random.nextInt(MAX_Y_VALUE - MIN_Y_VALUE) + MIN_Y_VALUE;
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