package com.example.digitalkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;

public class SettingsActiviti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.ThemeLight));
        setContentView(R.layout.activiti_main_settings);
        initButton();
    }

    private void initButton() {
        findViewById(R.id.buttonOn).setOnClickListener(v -> {
            setAppTheme(ThemeLight);
            recreate();
        });
        findViewById(R.id.buttonOff).setOnClickListener(v -> {
            setAppTheme(ThemeTwo);
            recreate();
        });
        findViewById(R.id.button_return).setOnClickListener(v ->{
            Intent intentResult = new Intent();
            setResult(RESULT_OK, intentResult);
            finish();
        });
    }

    // Имя настроек
    private static final String NameSharedPreference = "LOGIN";

    // Имя параметра в настройках
    private static final String AppTheme = "APP_THEME";
    private static final int ThemeLight = R.style.ThemeLight;
    private static final int ThemeTwo = R.style.ThemeTwo;

    private int count = 0;

    private int getAppTheme(int codeStyle) { //2000463
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    private int codeStyleToStyleId(int codeStyle) {
        if ( codeStyle == R.style.ThemeLight)
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


}