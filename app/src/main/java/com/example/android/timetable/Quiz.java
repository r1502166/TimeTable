package com.example.android.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class Quiz extends AppCompatActivity {

    int score_correct = 0;
    int score_total = 0;

    int question_a, question_b, answer_c, right_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        reset();
    }


    private void reset() {
        score_correct = 0;
        score_total = 0;
        updateScore();
        generateQuestion();
    }

    private void updateScore() {
        TextView score_view = findViewById(R.id.score_quiz);
        double score_percentage;
        if (score_total == 0){
            score_percentage = 0;
        } else {
            score_percentage = 100.0 * score_correct / score_total;
        }
        String score_message = String.format("Score: %d / %d (%.2f %%)", score_correct, score_total, score_percentage);
        score_view.setText(score_message);
    }

    private void generateQuestion() {
        Random random = new Random();
        question_a = random.nextInt(10)+1;
        question_b = random.nextInt(10)+1;
        answer_c = question_a * question_b;
        TextView view = findViewById(R.id.question_quiz);
        view.setText(String.format("%d x %d =", question_a, question_b));

        int[] answers = new int[4];
        for (int i = 0; i < 4; i++) {
            int r;
            boolean valid;
            do {
                r = (random.nextInt(10)+1) * (random.nextInt(10)+1);
                valid = true;
                if (r == answer_c) {
                    valid = false;
                } else {
                    for (int j = 0; j < i; j++) {
                        if (answers[j] == r) {
                            valid = false;
                            break;
                        }
                    }
                }
            } while (!valid);
            answers[i] = r;
        }

        // Hide solution into one of the four buttons
        right_button = random.nextInt(4)+1;
        answers[right_button - 1] = answer_c;

        view = findViewById(R.id.answer1);
        view.setText(String.format("%d", answers[0]));
        view = findViewById(R.id.answer2);
        view.setText(String.format("%d", answers[1]));
        view = findViewById(R.id.answer3);
        view.setText(String.format("%d", answers[2]));
        view = findViewById(R.id.answer4);
        view.setText(String.format("%d", answers[3]));
    }


    public void reset_quiz(View view) {
        reset();
    }

    public void answerPressed(View view) {
        score_total++;
        int button_pressed = Integer.valueOf(view.getTag().toString()) - 100;
        if (button_pressed == right_button) {
            score_correct++;
        }
        updateScore();
        generateQuestion();
    }
}
