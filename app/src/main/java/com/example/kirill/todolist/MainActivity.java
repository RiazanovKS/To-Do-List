package com.example.kirill.todolist;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    DBHelper dbHelper;
    FloatingActionButton fab;
    static ListView listView;
    static ArrayAdapter <ToDoList> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        listView = findViewById(R.id.list_view);
        dbHelper = new DBHelper(this);
        adapter = new CustomArrayAdapter(
                this, dbHelper.getLists());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Toast.makeText(this, "Нажата плавающая кнопка", Toast.LENGTH_LONG).show();
                AddListDialogFragment dialog = new AddListDialogFragment();
                dialog.show(getSupportFragmentManager(), "custom");
        }
    }

    public static void updateAdapter(ToDoList toDoList) {
        adapter.add(toDoList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"HEEEEEEEEY",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ToDoActivity.class);
        intent.putExtra("id",adapter.getItem(position).getId());
        Log.d("myLogs",String.valueOf(adapter.getItem(position).getId()));
        startActivity(intent);
    }

}