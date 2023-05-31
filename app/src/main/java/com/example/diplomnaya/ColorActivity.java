package com.example.diplomnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ColorActivity extends AppCompatActivity {

    ImageView colorPick;
    View colorView;
    Button buttonPick;

    Bitmap bitmap;
    int r,g,b;
    boolean pick = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        Intent intent = new Intent(this, ColorResultActivity.class);

        buttonPick = findViewById(R.id.buttonColorPick);
        colorPick = findViewById(R.id.color_pick);
        colorView = findViewById(R.id.color_res);

        colorPick.setDrawingCacheEnabled(true);
        colorPick.buildDrawingCache(true);

        colorPick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = colorPick.getDrawingCache();
                    int pixels = bitmap.getPixel((int)event.getX(),(int)event.getY());

                    r = Color.red(pixels);
                    g = Color.green(pixels);
                    b = Color.blue(pixels);

                    colorView.setBackgroundColor(Color.rgb(r,g,b));
                    System.out.println(Color.rgb(r,g,b));

                    pick = true;
                }
                return true;
            }
        });

        buttonPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pick){
                intent.putExtra("color",Color.rgb(r,g,b));
                startActivity(intent);
            } else{
                    Toast toast = Toast.makeText(getBaseContext(),"Выберите цвет",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });
    }
}