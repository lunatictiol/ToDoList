package com.example.todolist.Model;

import android.provider.BaseColumns;

public final class TaskContact {
    private TaskContact(){}
    public static final class TaskEntry implements BaseColumns{

        public static final String TO_DO_TABLE="ToDoList";
        public static final String TASK_KEY = "task";
       public static final String ID_KEY = "id";
       public static final String DATE_KEY = "date";
       public static final String TIME_KEY = "time";
    }
}
