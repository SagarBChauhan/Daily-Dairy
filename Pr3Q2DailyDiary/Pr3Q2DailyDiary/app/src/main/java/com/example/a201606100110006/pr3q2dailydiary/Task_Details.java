package com.example.a201606100110006.pr3q2dailydiary;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Task_Details extends AppCompatActivity {

    TextView task_name, task_date, task_description;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task__details);
        init();
    }

    private void init() {
        task_name=findViewById(R.id.txt_task_name);
        task_date=findViewById(R.id.txt_task_date);
        task_description=findViewById(R.id.txt_task_description);

        dbHelper=new DatabaseHelper(this);
    }
}
