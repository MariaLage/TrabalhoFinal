package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class Detalhes extends Activity {
    protected TextView disci, desc;
    protected EditText data;
    protected String[] osDetalhes;
    protected BDAdapter a;
    Intent oIntent;
    protected Button btEdit, btDelete;
    public int tipoLembrete, id_lembrete;

    private void executarOutraActivity(Class<?> subActividade, int oValor) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("_id", oValor);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        oIntent = getIntent();
        a = new BDAdapter(this).open();
        final Context context = this;
        tipoLembrete = oIntent.getIntExtra("tipo", 0);

        final Spinner tipo = (Spinner) findViewById(R.id.spinner);

        List<String> cat = new ArrayList<String>();
        cat.add("Teste");
        cat.add("Trabalho");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipo.setAdapter(dataAdapter);

        disci = (TextView) findViewById(R.id.disciDeta);
        desc = (TextView) findViewById(R.id.descDeta);
        data = (EditText) findViewById(R.id.editText3);
        btEdit = (Button) findViewById(R.id.btedit);
        btDelete = (Button) findViewById(R.id.btdelete);
        String id = "";


        a = new BDAdapter(this).open();
        Intent oIntent = getIntent();

        String teste = oIntent.getStringExtra("umInt");

        osDetalhes = a.obterDetalhesRegisto(teste);

        disci.setText(osDetalhes[2]);
        desc.setText(osDetalhes[3]);
        data.setText(osDetalhes[4]);
        id_lembrete = Integer.parseInt(osDetalhes[0]);


        btEdit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(Edit.class, id_lembrete);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.deleteTesteTrabalho(id_lembrete);
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
