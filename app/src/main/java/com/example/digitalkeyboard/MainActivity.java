package com.example.digitalkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textMission;
    private String text = "";
    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            textMission.setText(String.format(Locale.getDefault(),"%s","12"));
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        textMission = findViewById(R.id.textView);
        System.out.println("fd");

    }

    public void initButton() {
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonEqually = findViewById(R.id.button_equally);
        Button buttonAddition = findViewById(R.id.button_addition);
        Button buttonDivide = findViewById(R.id.button_divide);
        Button buttonMultiply = findViewById(R.id.button_multiply);
        Button buttonPoint = findViewById(R.id.button_point);
        Button buttonSubtrction = findViewById(R.id.button_subtraction);
        button1.setOnClickListener(listener);
        toTextMission(button1);
        toTextMission(button2);
        toTextMission(button3);
        toTextMission(button4);
        toTextMission(button5);
        toTextMission(button6);
        toTextMission(button7);
        toTextMission(button8);
        toTextMission(button9);
        toTextMission(button0);
        toTextMission(buttonEqually);
        toTextMission(buttonAddition);
        toTextMission(buttonSubtrction);
        toTextMission(buttonDivide);
        toTextMission(buttonMultiply);
        toTextMission(buttonPoint);
    }

    private void toTextMission(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text += button.getText().toString();
                textMission.setText(String.format(Locale.getDefault(),"%s",text));
            }
        });
    }
}