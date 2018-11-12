package com.example.kirill.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoListDb";
    public static final String TABLE_LISTS = "ToDoLists";
    public static final String TABLE_ITEMS = "TodoItems";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_LIST_ID = "_list_id";

    final String LOG_TAG = "myLogs";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG,"---oncreate database---");
        db.execSQL("create table "
                + TABLE_LISTS
                + "("
                + KEY_ID + " integer primary key autoincrement, "
                + KEY_TITLE + " text "
                + ")"
        );
        db.execSQL("create table "
                + TABLE_ITEMS
                + "("
                + KEY_ID + " integer primary key autoincrement, "
                + KEY_CONTENT +" text, "
                + KEY_LIST_ID + " integer "
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_LISTS);

        onCreate(db);
    }

    public long addToDoList(String title) {
        Log.d(LOG_TAG,"--- Insert in mytable ---");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE,title);
        return db.insert(TABLE_LISTS,null,initialValues);
    }

    public long addToDoItem(String content,int listId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_CONTENT,content);
        contentValues.put(KEY_LIST_ID,listId);
        Log.d(LOG_TAG,"--- Adding todo item to " + listId + " list ---");

        return db.insert(TABLE_ITEMS,null,contentValues);
    }


    public List<ToDoList> getLists (){
        Log.d(LOG_TAG," --- Getting item ---");
        SQLiteDatabase db = this.getReadableDatabase();
        List <ToDoList> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_LISTS,new String[]{KEY_ID,KEY_TITLE},
                null,null, null,null,null);

        Log.d(LOG_TAG," --- Item has got ---");
        if(cursor.moveToFirst()){
            do{
                Log.d(LOG_TAG,
                        "title: " + cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                list.add(new ToDoList(cursor.getString(cursor.getColumnIndex(KEY_TITLE)),cursor.getInt(cursor.getColumnIndex(KEY_ID))));
            } while (cursor.moveToNext());
        } else
            Log.d(LOG_TAG," --- 0 rows ---");
            cursor.close();

            return list;
    }



    public List<String> getItems(int id){
        Log.d(LOG_TAG," --- Getting itemS ---");
        List <String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS,new String[]{KEY_CONTENT},
                KEY_LIST_ID + "=?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor.moveToFirst()){
            do{
                        list.add(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
            } while (cursor.moveToNext());
        } else
            Log.d(LOG_TAG," --- 0 rows ---");
            cursor.close();
        return list;
    }

}
