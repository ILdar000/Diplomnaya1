package com.example.diplomnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diplomnaya.DBHelper.DBHelper;
import com.example.diplomnaya.algoritm.Size;

public class ParamActivity extends AppCompatActivity {

    EditText editText,editChest,editWaist,editHips;
    Spinner spinner;
    Button buttonRes,buttonMerki;
    String spinnerRes;
    int height;
    int[] arrayObhvat = new int[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);

        editText = findViewById(R.id.editTextTextPersonName);
        editChest = findViewById(R.id.editTextTextChest);
        editWaist = findViewById(R.id.editTextTextWaist);
        editHips = findViewById(R.id.editTextTextHips);
        spinner = findViewById(R.id.spinnerSex);
        buttonRes = findViewById(R.id.buttonResult);
        buttonMerki = findViewById(R.id.buttonMerki);

        //адаптер для spinner
        ArrayAdapter<?> adapterSex =
                ArrayAdapter.createFromResource(this, R.array.sex,
                        android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Вызываем адаптер
        spinner.setAdapter(adapterSex);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                Toast.makeText(getBaseContext(),parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                spinnerRes =parent.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonMerki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog;
                dialog = new Dialog(ParamActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.show();


            }
        });

        buttonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerRes.equals("Выберите ваш пол")){
                    Toast.makeText(getBaseContext(),spinnerRes,Toast.LENGTH_LONG).show();
                }
                else {
                    if (editText.getText().toString().equals("")){
                        Toast.makeText(getBaseContext(),"Введите возраст",Toast.LENGTH_LONG).show();
                    }
                    else {
                        height = Integer.parseInt(editText.getText().toString());
                        if (editChest.getText().toString().equals("")){
                            Toast.makeText(getBaseContext(),"Введите обхват груди",Toast.LENGTH_LONG).show();
                        }
                        else {
                            arrayObhvat[0] = Integer.parseInt(editChest.getText().toString());
                            if (editWaist.getText().toString().equals("")){
                                Toast.makeText(getBaseContext(),"Введите обхват талии",Toast.LENGTH_LONG).show();
                            }
                            else {
                                arrayObhvat[1] = Integer.parseInt(editWaist.getText().toString());
                                if (editHips.getText().toString().equals("")){
                                    Toast.makeText(getBaseContext(),"Введите обхват бедер",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    arrayObhvat[2] = Integer.parseInt(editHips.getText().toString());

                                    String[] size = new String[4];
                                    size = Size.selectSize(spinnerRes, height, arrayObhvat[0], arrayObhvat[1], arrayObhvat[2]);

                                    DBHelper dbHelper;
                                    dbHelper = new DBHelper(ParamActivity.this);
                                    dbHelper.add(size[0],size[1],size[2],size[3],size[4],size[5],size[6],size[7],Integer.toString(height),spinnerRes);

                                    Dialog dialog;
                                    dialog = new Dialog(ParamActivity.this);
                                    dialog.setContentView(R.layout.dialog_result);
                                    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
                                    textView1 = dialog.findViewById(R.id.textViewRuResShirt);
                                    textView2 = dialog.findViewById(R.id.textViewIntResShirt);
                                    textView3 = dialog.findViewById(R.id.textViewEuResShirt);
                                    textView4 = dialog.findViewById(R.id.textViewUsResShirt);
                                    textView5 = dialog.findViewById(R.id.textViewRuResJeans);
                                    textView6 = dialog.findViewById(R.id.textViewIntResJeans);
                                    textView7 = dialog.findViewById(R.id.textViewEuResJeans);
                                    textView8 = dialog.findViewById(R.id.textViewUsResJeans);

                                    textView1.setText(size[0]);
                                    textView2.setText(size[1]);
                                    textView3.setText(size[2]);
                                    textView4.setText(size[3]);
                                    textView5.setText(size[4]);
                                    textView6.setText(size[5]);
                                    textView7.setText(size[6]);
                                    textView8.setText(size[7]);
                                    dialog.show();
                                }
                            }
                        }
                    }
                }


            }
        });
    }
}