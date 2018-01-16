package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetalhesTestes extends Activity {
    protected TextView tipo, disci, desc, data;
    protected String[] osDetalhes;
    protected BDAdapter a;
    Intent oIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_testes);
        oIntent = getIntent();

        tipo = (TextView) findViewById(R.id.tipoDeta);
        disci = (TextView) findViewById(R.id.disciDeta);
        desc = (TextView) findViewById(R.id.descDeta);
        data = (TextView) findViewById(R.id.dataDeta);

        a = new BDAdapter(this).open();
        Intent oIntent = getIntent();

        String teste = oIntent.getStringExtra("umInt");

        osDetalhes = a.obterDetalhesRegisto(teste);

        tipo.setText(osDetalhes[1]);
        disci.setText(osDetalhes[2]);
        desc.setText(osDetalhes[3]);
        data.setText(osDetalhes[4]);
    }
}
