package com.example.android_change_theme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String theme  = data.getString("theme", "AppTheme" );
        if(theme.equals("AppTheme_Dark"))
        {
            setTheme(R.style.AppTheme_Dark);
        }
        else
        {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);

    }

    public void onClickthemeButton(View view)
    {
        String theme = "AppTheme_Dark";

        // 選択したテーマの保存処理
        changeTheme(theme);
    }

    public void changeTheme(String theme)
    {
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = data.edit();
        e.putString("theme", "AppTheme_Dark");
        e.apply();
    }
}
