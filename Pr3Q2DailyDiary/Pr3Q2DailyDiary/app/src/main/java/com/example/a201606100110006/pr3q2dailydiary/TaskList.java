package com.example.a201606100110006.pr3q2dailydiary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {

    ListView lv_Task_list;
    DatabaseHelper dbHelper;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        init();
    }

    private void init() {
        lv_Task_list=findViewById(R.id.lv_tasks);
        dbHelper=new DatabaseHelper(this);
        BindData();
        registerForContextMenu(lv_Task_list);
    }
    private void BindData() {
        ArrayList<String> TaskList=dbHelper.getAllTaskDates();
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,TaskList);
        lv_Task_list.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.my_context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_view:
                Intent i = new Intent(getApplicationContext(),Task_Details.class);
                startActivity(i);
                AdapterView.AdapterContextMenuInfo menuInfo=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                int listPosition=menuInfo.position;
                Toast.makeText(this, "Viewing details of item: "+Integer.toString(listPosition), Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete_task:
                AdapterView.AdapterContextMenuInfo menuInfo1=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                int listPosition1=menuInfo1.position;
                Toast.makeText(this, "Item "+Integer.toString(listPosition1)+" Deleted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_update_task:
                AdapterView.AdapterContextMenuInfo menuInfo2=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
                int listPosition2=menuInfo2.position;
                Toast.makeText(this, "Updating Item: "+Integer.toString(listPosition2), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
