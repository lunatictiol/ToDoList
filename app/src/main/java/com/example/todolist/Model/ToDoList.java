package com.example.todolist.Model;

public class ToDoList {
  private String mDate;
  private String mTime;
  private String mTask;
  private int mID;

  public ToDoList(int id,String task, String date,String time) {
      setID(id);
      setDate(date);
        setTime(time);
        setTask(task);

    }

    public String getDate() {
        return mDate;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getTask() {
        return mTask;
    }

    public void setTask(String mTask) {
        this.mTask = mTask;
    }


}
