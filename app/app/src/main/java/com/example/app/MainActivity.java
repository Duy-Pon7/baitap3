package com.example.app;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private int[] backgrounds;
    private RelativeLayout mainLayout;
    private int currentBackgroundIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Array of drawable resource IDs
        backgrounds = new int[]{
            R.drawable.background1,
            R.drawable.background2,
            R.drawable.background3,
            // Add more drawable resources as needed
        };

        // Set the initial background
        mainLayout = findViewById(R.id.main);
        setNextBackground();

        // Set up the Switch control
        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setNextBackground();
                    Toast.makeText(MainActivity.this, "Background changed", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Switch is off", Toast.LENGTH_LONG).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setNextBackground() {
        if (mainLayout != null) {
            currentBackgroundIndex = (currentBackgroundIndex + 1) % backgrounds.length;
            int nextBackground = backgrounds[currentBackgroundIndex];
            Log.d(TAG, "Selected background resource ID: " + nextBackground);
            mainLayout.setBackgroundResource(nextBackground);
            Log.d(TAG, "Background set successfully.");
        } else {
            Log.e(TAG, "Main layout is null. Check the layout resource ID.");
        }
    }
}