package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;

import java.util.ArrayList;
import java.util.List;


public class Create extends Activity {

    protected EditText aDisci, aDesc, aData;
    protected Button botaoIns;
    protected Spinner oTipo;
    protected BDAdapter a;
    private int ano, mes, dia;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        a = new BDAdapter(this).open();
        final Context context = this;
        aDisci = (EditText) findViewById(R.id.disciet);
        aDesc = (EditText) findViewById(R.id.descriet);
        aData = (EditText) findViewById(R.id.data);
        oTipo = (Spinner) findViewById(R.id.tipo);
        botaoIns = (Button) findViewById(R.id.createbt);

        List<String> umaLista = new ArrayList<String>();
        umaLista.add("Teste");
        umaLista.add("Trabalho");
        ArrayAdapter<String> oAdaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, umaLista);
        oAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oTipo.setAdapter(oAdaptador);


        botaoIns.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                a.insertTestTrab(oTipo.getSelectedItemPosition(), aDisci.getText().toString(), aDesc.getText().toString(), aData.getText().toString());
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
