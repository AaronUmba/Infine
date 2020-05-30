package com.infined.infine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ToDo2 extends SQLiteOpenHelper {

    private static final String TD_NAME="TODO";
    private static final int TD_VER = 1;
    public static final String TD_TABLE="Task";
    public static final String TD_COLUMN = "TaskName";



    public ToDo2(Context context) {
        super(context, TD_NAME, null, TD_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase td) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL);",TD_TABLE,TD_COLUMN);
        td.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase td, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXISTS %s",TD_TABLE);
        td.execSQL(query);
        onCreate(td);
    }
    public void insertNewTask(String task) {
        SQLiteDatabase td = this.getWritableDatabase ();
        ContentValues values = new ContentValues();
        values.put(TD_COLUMN,task);
        td.insertWithOnConflict(TD_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        td.close();
    }
    public void deleteTask(String task) {
        SQLiteDatabase td = this.getWritableDatabase();
        td.delete(TD_TABLE,TD_COLUMN + " = ?",new String[]{task});
        td.close();
    }
    public ArrayList<String> getTaskList(){
                ArrayList<String> taskList = new ArrayList<>();
                SQLiteDatabase td = this.getReadableDatabase();
            Cursor cursor = td.query(TD_TABLE,new String[]{TD_COLUMN},null,null,null,null,null);
            while(cursor.moveToNext()){
                int index = cursor.getColumnIndex(TD_COLUMN);
                taskList.add(cursor.getString(index));
            }
        cursor.close();
        td.close();
        return taskList;

    }
}
