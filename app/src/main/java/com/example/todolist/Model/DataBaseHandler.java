package com.example.todolist.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ToDoListDatabase";
    private static final int DATABASE_VERSION = 1;
    public DataBaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createCommand = "create table " + TaskContact.TaskEntry.TO_DO_TABLE +
                "( " + TaskContact.TaskEntry.ID_KEY + " integer primary key autoincrement"
                + ", " + TaskContact.TaskEntry.TASK_KEY + " text" + ", " + TaskContact.TaskEntry.DATE_KEY +" text" +", "
                + TaskContact.TaskEntry.TIME_KEY + " text" + " )";
        sqLiteDatabase.execSQL(createCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
     String dropCommand  = "drop table if exists " + TaskContact.TaskEntry.TO_DO_TABLE;
     sqLiteDatabase.execSQL(dropCommand);
     onCreate(sqLiteDatabase);
    }
    public void addTask(ToDoList toDoList){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String addTskCommand = "insert into " + TaskContact.TaskEntry.TO_DO_TABLE + " values(null, '"
                + toDoList.getTask() +"', '" + toDoList.getDate() + "', '"
                + toDoList.getTime() + "')";

        sqLiteDatabase.execSQL(addTskCommand);
        sqLiteDatabase.close();
    }
    public void deleteTask(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String deleteCommand = "delete from " + TaskContact.TaskEntry.TO_DO_TABLE + " where " + TaskContact.TaskEntry.ID_KEY + " = " + id;
        sqLiteDatabase.execSQL(deleteCommand);
        sqLiteDatabase.close();

    }
    public void modifyTask(int id, String newTask, String newDate, String newTime){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String modifyCommand = "update " + TaskContact.TaskEntry.TO_DO_TABLE + " set " + TaskContact.TaskEntry.TASK_KEY + " = '" + newTask +
                "', " + TaskContact.TaskEntry.DATE_KEY + " = '" + newDate +
                "', " + TaskContact.TaskEntry.TIME_KEY + " = '" + newTime +
                "' " + "where " + TaskContact.TaskEntry.ID_KEY + " = " + id;
        sqLiteDatabase.execSQL(modifyCommand);
        sqLiteDatabase.close();
    }
    public ArrayList<ToDoList> returnTodoList(){
        ArrayList<ToDoList> TodoArrayList=new ArrayList<>();
        SQLiteDatabase sqlDb = getWritableDatabase();
        String sqlQuery = "select * from " + TaskContact.TaskEntry.TO_DO_TABLE;
        Cursor cur = sqlDb.rawQuery(sqlQuery,null);
        while (cur.moveToNext()){
            ToDoList toDoListObject = new ToDoList(Integer.parseInt(cur.getString(0)),cur.getString(1),cur.getString(2), cur.getString(3) );
            TodoArrayList.add(toDoListObject);
        }
        sqlDb.close();
        return TodoArrayList;
    }


}
