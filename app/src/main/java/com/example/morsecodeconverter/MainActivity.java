package com.example.morsecodeconverter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final HashMap<Character, String> textToMorse = new HashMap<>();
    private final HashMap<String, Character> morseToText = new HashMap<>();

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeMorseCodeMappings();

        EditText inputText = findViewById(R.id.inputText);
        Button convertToMorse = findViewById(R.id.convertToMorse);
        Button convertToText = findViewById(R.id.convertToText);
        TextView resultText = findViewById(R.id.resultText);
        Button copyButton = findViewById(R.id.copyButton);
        Button clearButton = findViewById(R.id.clearButton);
        Button guideButton = findViewById(R.id.guideButton);
        Button testButton = findViewById(R.id.testButton);

        convertToMorse.setOnClickListener(v -> {
            String input = inputText.getText().toString().toLowerCase();
            StringBuilder morseCode = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (textToMorse.containsKey(c)) {
                    morseCode.append(textToMorse.get(c)).append(" ");
                } else if (c == ' ') {
                    morseCode.append("/ ");
                } else {
                    Toast.makeText(this, "Invalid character: " + c, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            resultText.setText(morseCode.toString().trim());
            copyButton.setVisibility(resultText.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        });

        convertToText.setOnClickListener(v -> {
            String input = inputText.getText().toString().trim();
            StringBuilder text = new StringBuilder();
            for (String morse : input.split(" ")) {
                if (morse.equals("/")) {
                    text.append(" ");
                } else if (morseToText.containsKey(morse)) {
                    text.append(morseToText.get(morse));
                } else {
                    Toast.makeText(this, "Invalid Morse code: " + morse, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            resultText.setText(text.toString());
            copyButton.setVisibility(resultText.getText().toString().isEmpty() ? View.GONE : View.VISIBLE);
        });

        copyButton.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Morse Code Result", resultText.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(MainActivity.this, "Copied to Clipboard!", Toast.LENGTH_SHORT).show();
        });

        clearButton.setOnClickListener(v -> {
            resultText.setText("");
            copyButton.setVisibility(View.GONE);
        });

        guideButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MorseCodeGuideActivity.class);
            startActivity(intent);
        });

        testButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
        });
    }

    private void initializeMorseCodeMappings() {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-",
                "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
                "-....", "--...", "---..", "----.", "-----"};
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            textToMorse.put(alphabet[i], morse[i]);
            morseToText.put(morse[i], alphabet[i]);
        }
    }
}
