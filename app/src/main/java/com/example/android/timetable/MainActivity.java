package com.example.android.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void displayTable(int table) {
        TextView displayTextView = findViewById(R.id.display_textView);
        String output = "";
        for (int i = 1; i <= 10; i++) {
            output += String.format("%2d x %2d = %3d%n", table, i, table*i);
        }
        displayTextView.setText(output);
    }

    public void buttonPressed(View view) {
        displayTable(Integer.valueOf(view.getTag().toString()));
    }
}
