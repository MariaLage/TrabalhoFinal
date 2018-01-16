package com.example.marialage.trabalhofinal;

import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

public class Testes extends Activity {
    ListView listView ;
    protected List<String> osTipos;
    protected BDAdapter a;
    protected MainActivity mA;

    private void executarOutraActivity(Class<?> subActividade, String oValor) {
        Intent x = new Intent(this, subActividade);
        x.putExtra("umInt", oValor);
        startActivity(x);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "MainActivity onStart()", Toast.LENGTH_SHORT).show();
        a = new BDAdapter(this).open();
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
        setContentView(R.layout.activity_testes);
        osTipos = new ArrayList<String>();
        a = new BDAdapter(this).open();
        osTipos = a.obterTodosTestes();

        listView = (ListView) findViewById(R.id.testelv);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, osTipos);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int itemPosition = position;

                String  itemValue = (String) listView.getItemAtPosition(position);

                executarOutraActivity(DetalhesTestes.class, itemValue);

            }

        });

    }
}
