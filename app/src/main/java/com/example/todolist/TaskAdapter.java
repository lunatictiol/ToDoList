package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolist.Model.DataBaseHandler;
import com.example.todolist.Model.ToDoList;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<ToDoList> {
   private int mID;


    public TaskAdapter(@NonNull Context context, int id, @NonNull List<ToDoList> objects) {
        super(context, id, objects);
        mID = id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        ToDoList currentList = getItem(position);
        listItemView.setId(currentList.getID());
        TextView taskTxtView = (TextView) listItemView.findViewById(R.id.task_txtv);
        taskTxtView.setText(currentList.getTask());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_txtv);
        dateTextView.setText(currentList.getDate());
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_txtv);
        timeTextView.setText(currentList.getTime());
        listItemView.setId(currentList.getID());

  return listItemView;


    }

}
