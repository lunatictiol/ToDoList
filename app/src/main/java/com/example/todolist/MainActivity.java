package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todolist.Model.DataBaseHandler;
import com.example.todolist.Model.ToDoList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DataBaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);
         updateUi();

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu,menu);

            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();
            switch (id){
                case R.id.Add_task_menu:
                    Intent AddTaskActivityIntent = new Intent(MainActivity.this,AddTaskActivity.class);
                    startActivity(AddTaskActivityIntent);
                    return true;
            }
            return super.onOptionsItemSelected(item);








        }
        private void updateUi(){
            db = new DataBaseHandler(this);
            final ArrayList<ToDoList> toDoList = db.returnTodoList();

            TaskAdapter taskAdapter = new TaskAdapter(this, 0, toDoList);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(taskAdapter);

            TextView emptyView = (TextView) findViewById(R.id.empty_textView);
            emptyView.setText("Press + to add Task


        }

    @Override
    protected void onResume() {
        super.onResume();
        updateUi();

    }");
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent modifyActivityIntent = new Intent(MainActivity.this,ModifyTaskActivity.class);
                startActivity(modifyActivityIntent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = view.getId();
                db.deleteTask(id);
                updateUi();

                Toast.makeText(MainActivity.this, "Task Deleted " , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}