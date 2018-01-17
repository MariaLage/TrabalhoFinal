package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.lang.UCharacterEnums;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Edit extends Activity {


    protected EditText disci, desc, data;
    protected BDAdapter a;
    protected Button guardarEdit;
    protected Integer id_lembrete;
    private int ano, mes, dia;
    protected String[] osDetalhes;


    private void executarOutraActivity(Class<?> subActividade) {
        Intent x = new Intent(this, subActividade);
        startActivity(x);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final Spinner tipoS = (Spinner) findViewById(R.id.tipoEditTe);

        List<String> cat = new ArrayList<String>();
        cat.add("Teste");
        cat.add("Trabalho");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        tipoS.setAdapter(dataAdapter);
        guardarEdit = (Button) findViewById(R.id.editTe);
        disci = (EditText)findViewById(R.id.discietEditTe);
        desc = (EditText)findViewById(R.id.descrietEditTe);
        data = (EditText)findViewById(R.id.dataEditTe);


        a = new BDAdapter(this).open();

        data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePicker();
            }
        });

        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DatePicker();
                }
            }
        });

        Intent oIntent = getIntent();
        id_lembrete = oIntent.getIntExtra("_id", 0);
        osDetalhes = a.obterDetalhesRegistoId(id_lembrete);
        disci.setText(osDetalhes[2]);
        desc.setText(osDetalhes[3]);
        data.setText(osDetalhes[4]);


        if(osDetalhes[1] == "0") tipoS.setSelection(0);
        else tipoS.setSelection(1);


        guardarEdit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.updateTesteTrab(id_lembrete, tipoS.getSelectedItemPosition(), disci.getText().toString(), desc.getText().toString(), data.getText().toString());
                executarOutraActivity(MainActivity.class);
            }
        });
    }


    public void DatePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        data.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, ano, mes, dia);
        datePickerDialog.show();
    }

    public void DatePicker2(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        ano = c.get(Calendar.YEAR);
        mes = c.get(Calendar.MONTH);
        dia = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        data.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, ano, mes, dia);
        datePickerDialog.show();
    }


}
