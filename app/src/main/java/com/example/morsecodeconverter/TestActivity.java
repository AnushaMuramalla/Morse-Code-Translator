package com.example.morsecodeconverter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Random;
public class TestActivity extends AppCompatActivity {
    private TextView testQuestion, scoreText;
    private EditText userAnswer;
    private Button checkButton, nextButton;
    private final HashMap<Character, String> textToMorse = new HashMap<>();
    private String currentQuestion;
    private String correctAnswer;
    private final String[] testCharacters = "abcdefghijklmnopqrstuvwxyz1234567890".split("");
    private int score = 0;
    private int totalQuestions = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testQuestion = findViewById(R.id.testQuestion);
        scoreText = findViewById(R.id.scoreText);
        userAnswer = findViewById(R.id.userAnswer);
        checkButton = findViewById(R.id.checkButton);
        nextButton = findViewById(R.id.nextButton);

        initializeMorseCodeMappings();
        generateNewQuestion();

        checkButton.setOnClickListener(v -> checkAnswer());
        nextButton.setOnClickListener(v -> generateNewQuestion());
    }

    private void initializeMorseCodeMappings() {
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-",
                "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", ".....",
                "-....", "--...", "---..", "----.", "-----"};
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            textToMorse.put(alphabet[i], morse[i]);
        }
    }

    private void generateNewQuestion() {
        Random random = new Random();
        String randomChar = testCharacters[random.nextInt(testCharacters.length)];
        currentQuestion = randomChar;
        correctAnswer = textToMorse.get(randomChar.charAt(0));

        testQuestion.setText("Convert: " + currentQuestion);
        userAnswer.setText("");
    }

    private void checkAnswer() {
        String userInput = userAnswer.getText().toString().trim();
        totalQuestions++;

        if (userInput.equals(correctAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong! Correct answer: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        updateScore();
    }

    private void updateScore() {
        scoreText.setText("Score: " + score + "/" + totalQuestions);
    }
}
