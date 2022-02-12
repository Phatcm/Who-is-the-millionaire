package com.example.ailatrieuphu.sql;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.ailatrieuphu.object.Question;

import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;

    public DBManager(Context c){
        this.context = c;
    }

    public DBManager open() throws SQLException{
        this.dbHelper = new MyDatabaseHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public  void close(){
        this.dbHelper.close();
    }
    public ArrayList<ArrayList<Question>> getQuestion(){
        ArrayList<ArrayList<Question>> arr = new ArrayList<>();
        HashMap<Integer,ArrayList<Question>> arrs = new HashMap<>();
        Cursor cursor = this.database.query("Question",
                new String[]{
                        "level",
                        "content",
                        "rightAns",
                        "wrongAns"},null,null,null,null,null);

        while (cursor.moveToNext()){
            int level = cursor.getInt(cursor.getColumnIndexOrThrow("level"));

            ArrayList<Question> arrLevel = arrs.get(level);
            if(arrLevel == null){
                arrLevel = new ArrayList<>();
            }

            arrLevel.add(createQuest(
                    cursor.getString(cursor.getColumnIndexOrThrow("content")),
                    cursor.getString(cursor.getColumnIndexOrThrow("rightAns")),
                    cursor.getString(cursor.getColumnIndexOrThrow("wrongAns"))
            ));
            arrs.put(level,arrLevel);

        }
        for(Integer key: arrs.keySet()){
            arr.add(arrs.get(key));
        }
        return arr;
    }

    private Question createQuest(String s1, String s2, String s3){
        Question q1 = new Question();
        q1.setContent(s1);
        q1.setRightAnswer(s2);
        q1.setArrWrongAnswer(s3);
        return q1;
    }
}
