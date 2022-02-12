package com.example.ailatrieuphu.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteAssetHelper{
    public static  final String DATA_NAME = "db.sqlite";
    public static final int DATA_VERSION =1;

    String createTableQuestion = "CREATE TABLE "+"Question"
            +"("
            +"id "+"INTEGER "+"PRIMARY KEY "+"autoincrement,"
            +"level "+" INTEGER,"
            +"content "+" TEXT,"
            +"rightAns "+" TEXT,"
            +"wrongAns "+" TEXT"
            +")";

    public MyDatabaseHelper(@Nullable Context context){
        super(context, DATA_NAME, null, DATA_VERSION);
    }

}
