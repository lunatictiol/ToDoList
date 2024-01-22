
package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.Model.DataBaseHandler;
import com.example.todolist.Model.ToDoList;

public class AddTaskActivity extends AppCompatActivity  {
   EditText task_et,time_et , date_et;
   Button addTask_btn;
   DataBaseHandler dataBaseHandler;
   private String tag = String.valueOf(AddTaskActivity.this.getClass());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
      task_et = (EditText) findViewById(R.id.TaskEditView);
      time_et = (EditText) findViewById(R.id.editTextTime);
      date_et = (EditText) findViewById(R.id.editTextDate);
      dataBaseHandler = new DataBaseHandler(this);
      addTask_btn = (Button) findViewById(R.id.add_task_btn);
       addTask_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               addTaskToDataBase();
           }
       });


    }


    private void addTaskToDataBase(){
        String Task = task_et.getText().toString();
        String Date  = date_et.getText().toString();
        String Time = time_et.getText().toString();
        try {
            ToDoList toDoList = new ToDoList(0,Task,Date,Time b);
dataBaseHandler.addTask(toDoList);
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            Log.e(tag,"Error in adding martial object to database");
        }
    }
}