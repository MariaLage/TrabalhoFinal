package com.example.marialage.trabalhofinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Detalhes extends Activity {
    protected TextView disci, desc, google;
    protected EditText data;
    protected String[] osDetalhes;
    protected BDAdapter a;
    Intent oIntent;
    protected Button btEdit, btDelete, btGoogle;
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
        btGoogle = (Button) findViewById(R.id.btGoogle);
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

        Log.d("search", "**** APP START");
        google = (TextView) findViewById(R.id.textView4);

        btGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String str = disci.getText().toString();

                Thread thread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        try {


                            // looking for
                            String strNoSpaces = str.replace(" ", "+");

                            // Your API key
                            String key="AIzaSyD-P0peWqV8xYLnbkEBIyGRtDT_Tuw2VmM";

                            // Your Search Engine ID
                            String cx = "003384601798782252287:depwhcxkutg";

                            String url2 = "https://www.googleapis.com/customsearch/v1?q=" + strNoSpaces + "&key=" + key + "&cx=" + cx + "&alt=json";
                            Log.d("search", "Url = "+  url2);
                            String result2 = httpGet(url2);

                            google.setText(result2);

                        }
                        catch(Exception e) {
                            System.out.println("Error1 " + e.getMessage());
                        }

                    }


                    private String httpGet(String urlStr) throws IOException {

                        URL url = new URL(urlStr);

                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        if(conn.getResponseCode() != 200) {
                            throw new IOException(conn.getResponseMessage());
                        }

                        Log.d("search", "Connection status = " + conn.getResponseMessage());

                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;

                        while((line = rd.readLine()) != null) {

                            Log.d("search", "Line =" + rd.readLine());
                            sb.append(line+"\n");

                        }
                        rd.close();

                        conn.disconnect();
                        return sb.toString();
                    }
                });

                thread.start();

            }
        });
    }


}
