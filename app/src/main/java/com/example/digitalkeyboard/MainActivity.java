package com.example.digitalkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private LogicCalculater logic = new LogicCalculater();
    private TextView textMission;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
        textMission = findViewById(R.id.textView);

    }

    public void initButton() {
        toTextMission(findViewById(R.id.button1));
        toTextMission(findViewById(R.id.button2));
        toTextMission(findViewById(R.id.button3));
        toTextMission(findViewById(R.id.button4));
        toTextMission(findViewById(R.id.button5));
        toTextMission(findViewById(R.id.button6));
        toTextMission(findViewById(R.id.button7));
        toTextMission(findViewById(R.id.button8));
        toTextMission(findViewById(R.id.button9));
        toTextMission(findViewById(R.id.button0));
        findViewById(R.id.button_equally).setOnClickListener(v -> equally());
        toTextMission(findViewById(R.id.button_addition));
        toTextMission(findViewById(R.id.button_subtraction));
        toTextMission(findViewById(R.id.button_divide));
        toTextMission(findViewById(R.id.button_multiply));
        toTextMission(findViewById(R.id.button_point));
    }

    private void toTextMission(Button button){
        String buttonText = button.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.addText(buttonText);
                logic.initNumbers(buttonText);
                textMission.setText(String.format(Locale.getDefault(),"%s",logic.text));
            }
        });
    }

    private void equally(){
            System.out.println("hi");
            textMission.setText(String.format(Locale.getDefault(),"%s",logic.res()));

    }
}