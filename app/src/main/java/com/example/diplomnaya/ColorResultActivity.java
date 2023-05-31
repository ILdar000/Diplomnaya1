package com.example.diplomnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ColorResultActivity extends AppCompatActivity {

    private static final int[] colorArray= {-345086,-288510,-175352,-121070,-5826229,-7994961,-12779100,-16627714,-16543282,-10047438,-3085781,-65997,-345086,-288510,-175352,-121070,-5826229,-7994961,-12779100,-16627714,-16543282,-10047438,-3085781,-65997};

    int colorMain;
    View view1_1,view1_2,view2_1,view2_2,view2_3,view3_1,view3_2,view3_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_result);
        view1_1 = findViewById(R.id.color_res1_1);
        view1_2 = findViewById(R.id.color_res1_2);
        view2_1 = findViewById(R.id.color_res2_1);
        view2_2 = findViewById(R.id.color_res2_2);
        view2_3 = findViewById(R.id.color_res2_3);
        view3_1 = findViewById(R.id.color_res3_1);
        view3_2 = findViewById(R.id.color_res3_2);
        view3_3 = findViewById(R.id.color_res3_3);
        Intent intent = getIntent();
        int color = intent.getIntExtra("color",0);
        System.out.println(color);
        for (int i = 1;i < 13;i++){
            if (color == colorArray[i]) {
            colorMain = i;
            }
        }
        view1_1.setBackgroundColor(colorArray[colorMain]);
        view1_2.setBackgroundColor(colorArray[colorMain + 6]);
        view2_1.setBackgroundColor(colorArray[colorMain]);
        view2_2.setBackgroundColor(colorArray[colorMain + 4]);
        view2_3.setBackgroundColor(colorArray[colorMain + 8]);
        view3_1.setBackgroundColor(colorArray[colorMain - 1]);
        view3_2.setBackgroundColor(colorArray[colorMain]);
        view3_3.setBackgroundColor(colorArray[colorMain + 1]);

    }
}