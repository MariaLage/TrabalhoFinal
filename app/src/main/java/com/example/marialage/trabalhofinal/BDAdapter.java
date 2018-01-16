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

    public int obterTodosCampos(List<Integer> osIds, List<Integer> osTipos, List<String> asDisci,List<String> asDescri, List<String> asDatas) {
        String[] colunas = new String[23];
        colunas[0] = "_id";
        colunas[1] = "tipo";
        colunas [2] = "disciplina";
        colunas[3] = "descricao";
        colunas [4] = "data";
        Cursor c = database.query("agenda", colunas, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                osIds.add(c.getInt(0));
                osTipos.add(c.getInt(1));
                asDisci.add(c.getString(2));
                asDescri.add(c.getString(3));
                asDatas.add(c.getString(4));
            } while (c.moveToNext());
        }
        c.close();
        return osIds.size();
    }
    public boolean existe(String umNome) {
        Cursor cursor = database.rawQuery(
                "select nome from agenda where tipo=?", new String[] { umNome });
        boolean b = cursor.getCount() >= 1;
        cursor.close();
        return b;
    }
    public long insertTestTrab(Integer oTipo, String aDisci, String aDesc, String aData) {
        ContentValues values = new ContentValues() ;
        values.put("tipo", oTipo);
        values.put("disciplina", aDisci);
        values.put("descricao", aDesc);
        values.put("data", aData);
        return database.insert("agenda", null, values);
    }
    public int deleteNome(String oNome) {
        String whereClause = "nome = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = oNome;
        return database.delete("contactos", whereClause, whereArgs);
    }
    public int deleteTodosNomes() {
        return database.delete("contactos", null, null);
    }


    public int updateNome(Integer oId, String oNome, String aMorada, Integer oTelefone) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = new Integer(oId).toString();
        ContentValues values = new ContentValues();
        values.put("nome", oNome);
        values.put("morada", aMorada);
        values.put("telefone", oTelefone);
        return database.update("contactos", values, whereClause, whereArgs);
    }

    public List<String> obterTodosTrabalhos() {
        ArrayList<String> trabalhos = new ArrayList<String>();
        Cursor cursor = obterRegistos();
        if (cursor.moveToFirst()) {
            do {
                trabalhos.add(cursor.getString(1) + "->" + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return trabalhos;
    }

    public List<String> obterTodosTestes() {
        ArrayList<String> testes = new ArrayList<String>();
        Cursor cursor = obterRegistos();
        if (cursor.moveToFirst()) {
            do {
                testes.add(cursor.getString(1) + "->" + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return testes;
    }

    public String[] obterDetalhesRegisto(String id) {
        Cursor db = obterRegistos();
        String[] registo = new String[4];
        if (db.moveToFirst()) {
            do {
                if(db.getString(1).equals(id)) {
                    registo[0] = db.getString(0);
                    registo[1] = db.getString(1);
                    registo[2] = db.getString(2);
                    registo[3] = db.getString(3);
                }
            } while (db.moveToNext());
        }
        db.close();
        return registo;
    }
}
