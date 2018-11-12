package com.example.kirill.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class AddListDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
    EditText input;
    DBHelper dbHelper;
    String title;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dbHelper = new DBHelper(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        input = new EditText(getActivity());
        return builder
                .setTitle("Диалоговое окно")
                .setMessage("Введите назание списка")
                .setView(input)
                .setPositiveButton("ОК", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getActivity(), input.getText().toString(), Toast.LENGTH_LONG).show();
        title = input.getText().toString();
        int id = (int) dbHelper.addToDoList(title);
        MainActivity.updateAdapter(new ToDoList(title, id));
        Intent intent = new Intent(getActivity(),ToDoActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}

