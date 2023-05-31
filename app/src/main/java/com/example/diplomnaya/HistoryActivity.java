package com.example.diplomnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.diplomnaya.DBHelper.DBHelper;
import com.example.diplomnaya.entity.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listView;
    DBHelper dbHelper;
    List<History> size =new ArrayList<>();
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        delete = findViewById(R.id.buttonDelete);

        dbHelper = new DBHelper(this);

        getList(dbHelper);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteAll();
                getList(dbHelper);
            }
        });

    }

    private void getList(DBHelper dbHelper){
        size = dbHelper.getAll();

        listView = findViewById(R.id.requestHistoryList);
        // создаем адаптер
        AdapterHistoryList stateAdapter = new AdapterHistoryList(this, R.layout.list_history, size);
        // устанавливаем адаптер
        listView.setAdapter(stateAdapter);

    }
}