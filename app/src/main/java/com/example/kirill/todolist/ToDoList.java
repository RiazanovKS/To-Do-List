package com.example.kirill.todolist;

public class ToDoList {

    private String title;
    private int id;


    public ToDoList(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public ToDoList() {
        this.id = 0;
        this.title = "List";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "title='" + title + '\'' +
                '}';
    }
}



