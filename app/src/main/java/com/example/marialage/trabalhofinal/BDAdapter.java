package com.example.marialage.trabalhofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BDAdapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private BD dbHelper;
    private SQLiteDatabase database;
    public BDAdapter(Context context) {
        dbHelper = new BD(context.getApplicationContext());
    }
    public BDAdapter open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    private Cursor obterRegistos() {
        String[] colunas = new String[4];
        colunas[0] = "tipo";
        colunas [1] = "disciplina";
        colunas[2] = "descricao";
        colunas[3] = "data";
        return database.query("agenda", colunas, null, null, null, null, "tipo");
    }

    private Cursor obterRegistosTrabalhosTestes(Integer tipo) {
        String[] colunas = new String[4];
        colunas[0] = "tipo";
        colunas [1] = "disciplina";
        colunas[2] = "descricao";
        colunas[3] = "data";
        String[] args = { tipo.toString()};
        return database.query("agenda", colunas, "tipo = ?", args, null, null, "tipo");
    }

    private Cursor obterRegistosTTS(String disciplina) {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "tipo";
        colunas[2] = "disciplina";
        colunas[3] = "descricao";
        colunas[4] = "data";
        String[] args = { disciplina.toString()};
        return database.query("agenda", colunas, "disciplina = ?", args, null, null, "disciplina");
    }

    private Cursor obterRegistosTTSid(Integer idLembrete) {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "tipo";
        colunas[2] = "disciplina";
        colunas[3] = "descricao";
        colunas[4] = "data";
        String[] args = { idLembrete.toString()};
        return database.query("agenda", colunas, "_id = ?", args, null, null, "disciplina");
    }


    public long insertTestTrab(Integer oTipo, String aDisci, String aDesc, String aData) {
        ContentValues values = new ContentValues() ;
        values.put("tipo", oTipo);
        values.put("disciplina", aDisci);
        values.put("descricao", aDesc);
        values.put("data", aData);
        return database.insert("agenda", null, values);
    }
    public int deleteTesteTrabalho(Integer id) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = id.toString();
        return database.delete("agenda", whereClause, whereArgs);
    }

    public int updateTesteTrab(Integer oId, Integer oTipo, String aDisci, String aDesc, String aData) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("tipo", oTipo);
        values.put("disciplina", aDisci);
        values.put("descricao", aDesc);
        values.put("data", aData);
        return database.update("agenda", values, whereClause, whereArgs);
    }


    public List<String> obterTodosTestesTrabalhos(Integer tipo) {
        ArrayList<String> testes = new ArrayList<String>();
        Cursor cursor = obterRegistosTrabalhosTestes(tipo);
        if (cursor.moveToFirst()) {
            do {
                testes.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return testes;
    }

    public String[] obterDetalhesRegisto(String id) {
        Cursor db = obterRegistosTTS(id);
        String[] registo = new String[5];
        if (db.moveToFirst()) {
            do {
                if(db.getString(2).equals(id)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                    registo[4] = db.getString(4);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }

    public String[] obterDetalhesRegistoId(Integer id) {
        Cursor db = obterRegistosTTSid(id);
        String[] registo = new String[5];
        if (db.moveToFirst()) {
            do {
                if(db.getString(0).equals(id)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                    registo[4] = db.getString(4);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }
}
