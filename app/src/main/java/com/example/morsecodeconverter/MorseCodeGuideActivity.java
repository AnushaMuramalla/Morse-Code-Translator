package com.example.morsecodeconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MorseCodeGuideActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morse_code_guide);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish()); // Return to the previous activity
    }
}
