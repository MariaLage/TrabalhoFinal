package com.example.marialage.trabalhofinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected Button botaoAdic, botaoTestes, botaoTrab;
    protected BDAdapter a;
    int indice;


    private void executarOutraActivity(Class<?> subActividade, int oValor) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("tipo", oValor);
        startActivity(x);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "MainActivity onStart()", Toast.LENGTH_SHORT).show();

        a = new BDAdapter(this).open();
        indice = -1;
    }
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "MainActivity onPause()", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "MainActivity onStop()", Toast.LENGTH_SHORT).show();
        a.close();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoAdic = (Button) findViewById(R.id.create);
        botaoTestes = (Button) findViewById(R.id.testesbt);
        botaoTrab = (Button) findViewById(R.id.trabalhosbt);
        final Context context = this;
        a = new BDAdapter(this).open();
        Toast.makeText(this, "MainActivity onCreate()", Toast.LENGTH_SHORT).show();


        botaoAdic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Create.class);
                startActivity(intent);
            }
        });

        botaoTestes.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(Testes.class, 0);
            }
        });

        botaoTrab.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                executarOutraActivity(Testes.class, 1);
            }
        });
    }
}
