package com.example.kirill.todolist;


import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ToDoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    DBHelper dbHelper;
    static ArrayAdapter <String> arrayAdapter;
    int listId;
    static ListView lv;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lv = findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        dbHelper = new DBHelper(this);
        Intent  intent = getIntent();
        fab = findViewById(R.id.fab2);
        fab.setOnClickListener(this);
        listId = intent.getIntExtra("id",1);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,
                dbHelper.getItems(listId));
        lv.setAdapter(arrayAdapter);
        Log.d("myLogs",String.valueOf(listId));
    }

    public static void updateAdapter(String string){
        arrayAdapter.add(string);
        lv.setAdapter(arrayAdapter);
    }


    @Override
    public void onClick(View v) {
        AddItemDialogFramgent dialogFramgent = new AddItemDialogFramgent();
        Bundle args = new Bundle();
        args.putInt("id",listId);
        dialogFramgent.setArguments(args);
        dialogFramgent.show(getSupportFragmentManager(),"custom");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = view.findViewById(android.R.id.text1);

        if((textView.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG)>0) {
            textView.setPaintFlags(textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        } else {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

    }
}




