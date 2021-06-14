package com.example.digitalkeyboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Logic logic = new Logic();
    private TextView textMission;

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
        textMission.setText(String.format(Locale.getDefault(),"%s", logic.text));
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.ThemeLight));//2000463
        setContentView(R.layout.activity_main);
        initButton();
        textMission = findViewById(R.id.textView);
    }


    // Имя настроек
    private static final String NameSharedPreference = "LOGIN";

    // Имя параметра в настройках
    private static final String AppTheme = "APP_THEME";
    private static final int ThemeLight = 0;
    private static final int ThemeLightDark = 1;

    private int count = 0;

    private int getAppTheme(int codeStyle) { //2000463
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int codeStyleToStyleId(int codeStyle) {
        if (codeStyle%2== 0)
            return R.style.ThemeLight;
        return R.style.ThemeTwo;
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle){
        // Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        //Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
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
            logic.incrementCountButtonTheme();
            setAppTheme(logic.getCountButtonTheme() % 2);
            recreate();
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

    private void equally(){
            textMission.setText(String.format(Locale.getDefault(),"%s", logic.res()));

    }
}