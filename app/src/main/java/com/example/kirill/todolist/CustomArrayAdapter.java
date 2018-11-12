package com.example.kirill.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter {


    public CustomArrayAdapter(Context context, List<ToDoList> tasks){
        super(context,android.R.layout.simple_list_item_1,tasks);
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        ToDoList toDoList = (ToDoList) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(android.R.layout.simple_list_item_1,null);
        }
        ((TextView) convertView.findViewById(android.R.id.text1))
                .setText(toDoList.getTitle());

        return convertView;
    }
}
