package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Model.DataBaseHandler;
import com.example.todolist.Model.ToDoList;

import java.util.ArrayList;

public class ModifyTaskActivity extends AppCompatActivity implements View.OnClickListener {
  private DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= new DataBaseHandler(this);
        setContentView(R.layout.activity_delete_task);
        updateLayout();

    }
    private void updateLayout(){
        ArrayList<ToDoList> toDoListArrayList = db.returnTodoList();
        if(toDoListArrayList.size()>0){
            ScrollView sc = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(toDoListArrayList.size());
            gridLayout.setColumnCount(5);
            TextView[] idTextView = new TextView[toDoListArrayList.size()];
            EditText[][]taskDateAndTimeTextView = new EditText[toDoListArrayList.size()][3];
            Button[] modifyButtons = new Button[toDoListArrayList.size()];


            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);
            int screenWidth = screenSize.x;
            int counter = 0;
            for (ToDoList todolistObject : toDoListArrayList){
                idTextView[counter] = new TextView(this);

                idTextView[counter].setGravity(Gravity.CENTER);

                idTextView[counter].setText(todolistObject.getID() + " ");
               taskDateAndTimeTextView[counter][0]= new EditText(this);
               taskDateAndTimeTextView[counter][0].setText(todolistObject.getTask());
               taskDateAndTimeTextView[counter][0].setId(todolistObject.getID() + 10);

               taskDateAndTimeTextView[counter][1]= new EditText(this);
               taskDateAndTimeTextView[counter][1].setText(todolistObject.getTime() + " ");

               taskDateAndTimeTextView[counter][1].setId(todolistObject.getID() + 20);

               taskDateAndTimeTextView[counter][2]= new EditText(this);
               taskDateAndTimeTextView[counter][2].setText(todolistObject.getDate());
               taskDateAndTimeTextView[counter][2].setId(todolistObject.getID() + 30);
                modifyButtons[counter] = new Button(this);
                modifyButtons[counter].setText("Modify");
                modifyButtons[counter].setId(todolistObject.getID());
                modifyButtons[counter].setOnClickListener(this);
                gridLayout.addView(idTextView[counter], (int) (screenWidth * 0.05),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(taskDateAndTimeTextView[counter][0],(int) (screenWidth * 0.28),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(taskDateAndTimeTextView[counter][1],(int) (screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(taskDateAndTimeTextView[counter][2],(int) (screenWidth * 0.20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButtons[counter],(int) (screenWidth * 0.35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                counter++;

            }
            sc.addView(gridLayout);
            setContentView(sc);
        }

    }

    @Override
    public void onClick(View view) {
        int id  = view.getId();
        EditText taskEditText = (EditText) findViewById(id +10);
        EditText timeEditText = (EditText) findViewById(id +20);
        EditText dateEditText = (EditText) findViewById(id +30);

        String  task = taskEditText.getText().toString();
        String  date = dateEditText.getText().toString();
        String  time = timeEditText.getText().toString();
        try {

            db.modifyTask(id,task,date,time);
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){

        }
    }
}