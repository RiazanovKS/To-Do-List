package com.example.kirill.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class AddItemDialogFramgent extends DialogFragment implements DialogInterface.OnClickListener {
    EditText input;
    String content;
    DBHelper dbHelper;
    int id;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        id = getArguments().getInt("id");
        dbHelper = new DBHelper(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        input = new EditText(getActivity());
        return builder
                .setTitle("Диалоговое окно")
                .setMessage("Название элемента")
                .setPositiveButton("OK", this)
                .setView(input)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        content = input.getText().toString();
        dbHelper.addToDoItem(content, id);
        ToDoActivity.updateAdapter(content);
    }

}
