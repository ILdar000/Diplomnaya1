package com.example.diplomnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startPhoto(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }

    public void startParam(View view) {
        Intent intent = new Intent(this, ParamActivity.class);
        startActivity(intent);
    }

    public void startHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void startColor(View view) {
        Intent intent = new Intent(this, ColorActivity.class);
        startActivity(intent);
    }


}