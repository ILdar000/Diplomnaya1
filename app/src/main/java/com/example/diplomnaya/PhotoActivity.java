package com.example.diplomnaya;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomnaya.DBHelper.DBHelper;
import com.example.diplomnaya.algoritm.Size;
import com.example.diplomnaya.backgroundremover.Backgrounder;

import java.io.IOException;

public class PhotoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView imageView1, imageView2;
    boolean image,image1,image2;
    int height;
    int[] arrayRes1,arrayRes2,arrayObhvat;
    Spinner spinner;
    String spinnerRes;
    Button buttonRes;
    Bitmap bitmapRes1,bitmapRes2;
    EditText editText;
    Backgrounder backgrounder = new Backgrounder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        Toast.makeText(getBaseContext(),"Нажмите на картинку чтобы вставить фото",Toast.LENGTH_LONG).show();

        editText = findViewById(R.id.editTextTextPersonName);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        spinner = findViewById(R.id.spinnerSex);
        buttonRes = findViewById(R.id.buttonResult);
        image1 = false;
        image2 = false;

        ArrayAdapter<?> adapterSex =
                ArrayAdapter.createFromResource(this, R.array.sex,
                        android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterSex);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
                Toast.makeText(getBaseContext(),parent.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
                spinnerRes =parent.getSelectedItem().toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = true;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), PICK_IMAGE_REQUEST);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = false;
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Выберите изображение"), PICK_IMAGE_REQUEST);
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
                        if ((!image1)||(!image2)){
                            Toast.makeText(getBaseContext(),"Выберите фотографию",Toast.LENGTH_LONG).show();
                        }
                         else{
                            bitmapRes1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();
                            arrayRes1 = new int[3];
                            arrayRes1 = Alg(height,bitmapRes1);
                            bitmapRes2 = ((BitmapDrawable)imageView2.getDrawable()).getBitmap();
                            arrayRes2 = new int[3];
                            arrayRes2 = Alg(height,bitmapRes2);
                            arrayObhvat = new int[3];
                            for (int i = 0; i < 3;i++){
                                arrayObhvat[i] = (int) (2 * Math.PI * Math.sqrt((Math.pow(arrayRes1[i],2)+Math.pow(arrayRes2[i],2))/8));
                            }
                            String[] size = Size.selectSize(spinnerRes, height, arrayObhvat[0], arrayObhvat[1], arrayObhvat[2]);

                            DBHelper dbHelper;
                            dbHelper = new DBHelper(PhotoActivity.this);
                            dbHelper.add(size[0],size[1],size[2],size[3],size[4],size[5],size[6],size[7],Integer.toString(height),spinnerRes);

                            Dialog dialog;
                            dialog = new Dialog(PhotoActivity.this);
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
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                if (image) {
                    image1 = true;
                    backgrounder.removeBg(bitmap,imageView1);
                }
                else {
                    image2 = true;
                    backgrounder.removeBg(bitmap,imageView2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] Alg(int heightS,Bitmap bitmap) {
        int height = bitmap.getHeight();
        int pix_sm = height/heightS;

        int [] array  = new int[3];
        array[0] = getPix(bitmap,height*56/207)/pix_sm;
        array[1] = getPix(bitmap,height*167/414)/pix_sm;
        array[2] = getPix(bitmap,height*200/414)/pix_sm;

        return array;
    }

    private static int getPix(Bitmap bitmap, int y) {
        int Pix = 0;
        for (int x = 0; x < (bitmap.getWidth() - 1); x++) {
            int pixel = bitmap.getPixel(x,y);
            if (Color.alpha(pixel) != 0){
                Pix++;
            }
        }
        return Pix;
    }

}
