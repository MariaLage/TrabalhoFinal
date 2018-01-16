package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Detalhes extends Activity {
    protected TextView tipo, disci, desc;
    protected EditText data;
    protected String[] osDetalhes;
    protected BDAdapter a;
    Intent oIntent;
    protected Button btEdit, btDelete;
    public  int tipoLembrete;

    private void executarOutraActivity(Class<?> subActividade, int oValor) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("tipo", oValor);
        startActivity(x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        oIntent = getIntent();
        a = new BDAdapter(this).open();
        tipoLembrete = oIntent.getIntExtra("tipo", 0);

        tipo = (TextView) findViewById(R.id.tipoDeta);
        disci = (TextView) findViewById(R.id.disciDeta);
        desc = (TextView) findViewById(R.id.descDeta);
        data = (EditText) findViewById(R.id.editText3);
        btEdit = (Button) findViewById(R.id.btedit);
        btDelete = (Button) findViewById(R.id.btdelete);


        a = new BDAdapter(this).open();
        Intent oIntent = getIntent();

        String teste = oIntent.getStringExtra("umInt");

        osDetalhes = a.obterDetalhesRegisto(teste);

        tipo.setText(osDetalhes[0]);
        disci.setText(osDetalhes[1]);
        desc.setText(osDetalhes[2]);
        data.setText(osDetalhes[3]);

        btEdit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(EditTrabalho.class, 0);
                executarOutraActivity(EditTeste.class, 1);
            }
        });
    }


}
