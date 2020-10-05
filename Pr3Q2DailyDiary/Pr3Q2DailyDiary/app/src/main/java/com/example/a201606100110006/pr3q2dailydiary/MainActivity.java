package com.example.a201606100110006.pr3q2dailydiary;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_SelectDate,btn_SelectTime,btn_save;
    TextView txt_Date, txt_Time;
    EditText ed_description;
    DatabaseHelper dbHelper;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        btn_SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                txt_Date.setText(day + "/" + month + "/" + year);
                            }
                        }, 0, 0, 0);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        btn_SelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        txt_Time.setText(hourOfDay+" : "+minute+" AM");
                    }
                },0,0,false);
                timePickerDialog.show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.AddTask(new Task(txt_Date.getText().toString(),txt_Time.getText().toString(),ed_description.getText().toString()));
                Intent i = new Intent(getApplicationContext(),TaskList.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        btn_SelectDate=findViewById(R.id.btn_SelectDate);
        btn_SelectTime=findViewById(R.id.btn_SelectTime);
        btn_save=findViewById(R.id.btn_Save);
        txt_Date=findViewById(R.id.txt_Date);
        txt_Time=findViewById(R.id.txt_Time);
        ed_description=findViewById(R.id.ed_txt_Description);

        dbHelper=new DatabaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_show_list:
                Intent i= new Intent(getApplicationContext(),TaskList.class);
                startActivity(i);
                break;
            case  R.id.menu_exit:
                finish();
                Toast.makeText(this, "Exiting", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
