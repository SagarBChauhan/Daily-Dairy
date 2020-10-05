package com.example.a201606100110006.pr3q2dailydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TaskManager";
    private static final int DATABASE_VERSION=1;
    private static final String TaskTable="tbl_Task";
    private static final String KEY_ID="ID";
    private static final String KEY_DATE="DATE";
    private static final String KEY_TIME="TIME";
    private static final String KEY_DESCRIPTION="DESCRIPTION";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Create_Table="Create TABLE "+TaskTable+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_DATE+" TEXT, "+KEY_TIME+" TEXT, "+KEY_DESCRIPTION+" TEXT "+");";
        sqLiteDatabase.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TaskTable);
        onCreate(sqLiteDatabase);
    }

    public void AddTask(Task task)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DATE,task.getDate());
        values.put(KEY_TIME,task.getTime());
        values.put(KEY_DESCRIPTION,task.getDescription());
        db.insert(TaskTable,null,values);
    }

    public ArrayList<String> getAllTasks()
    {
        ArrayList<String> TaskList=new ArrayList<String>();
        String Query="SELECT * FROM "+TaskTable;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);
        if(cursor.moveToFirst())
        {
            do{
                TaskList.add(cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2)+","+cursor.getString(3));
            }while (cursor.moveToNext());
        }
        return TaskList;
    }

    public ArrayList<String> getAllTaskDates()
    {
        ArrayList<String> TaskList=new ArrayList<String>();
        String Query="SELECT * FROM "+TaskTable;

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(Query,null);
        if(cursor.moveToFirst())
        {
            do{
                TaskList.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return TaskList;
    }
    public int updateTask(Task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_DATE,task.getDate());
        values.put(KEY_TIME,task.getTime() );
        values.put(KEY_DESCRIPTION,task.getDescription());

        return db.update(TaskTable,values,KEY_ID+"=?",new String[]{String.valueOf(task.getID())});
    }

    public void deleteTask(Task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TaskTable,KEY_ID+"=?",new String[]{String.valueOf(task.getID())});
        db.close();
    }

    Task getTaskDetails(int ID)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TaskTable,new String[]{KEY_ID,KEY_DATE,KEY_DATE,KEY_DESCRIPTION},KEY_ID+"=?",new String[]{String.valueOf(ID)},null,null,null,null );
        if(cursor !=null)
            cursor.moveToFirst();
            Task ObjTask=new Task(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            return ObjTask;
    }
}
