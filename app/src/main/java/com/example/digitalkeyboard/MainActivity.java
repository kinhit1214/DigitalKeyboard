package com.example.digitalkeyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SETTING_ACTIVITY = 1;
    private Logic logic = new Logic();
    private TextView textMission;
    private static final String AppTheme = "APP_THEME";
    private static final String NameSharedPreference = "LOGIN";

    private final static String KeyCounters = "Counters";

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(KeyCounters, logic);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        logic = instanceState.getParcelable(KeyCounters);
        if (logic.text == null)
            textMission.setText(String.format(Locale.getDefault(),"%s", "Результат"));
        else
            textMission.setText(String.format(Locale.getDefault(),"%s", logic.text));
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        setTheme(sharedPref.getInt(AppTheme, R.style.ThemeTwo));
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
        findViewById(R.id.buttonTheme).setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActiviti.class);
            startActivityForResult(intent, REQUEST_CODE_SETTING_ACTIVITY);
        });
    }

    private void toTextMission(Button button){
        String buttonText = button.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logic.addText(buttonText);
                logic.initNumbers(buttonText);
                textMission.setText(String.format(Locale.getDefault(),"%s", logic.text));
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        if (resultCode == RESULT_OK){
            recreate();
        }
    }

    private void equally(){
            textMission.setText(String.format(Locale.getDefault(),"%s", logic.res()));

    }
}