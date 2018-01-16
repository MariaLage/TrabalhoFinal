package com.example.marialage.trabalhofinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "base-dados2.db";
    private static final int VERSION = 1;
    public BD(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s =
                "CREATE TABLE agenda(_id integer primary key autoincrement, tipo integer, disciplina varchar(40), descricao varchar(50), data varchar(20))";
        db.execSQL(s);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS agenda");
        onCreate(db);
    }
}

