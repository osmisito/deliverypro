package com.oscarfernandez.deliverypro.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.oscarfernandez.deliverypro.R;
import com.oscarfernandez.deliverypro.controller.RutasController;
import com.oscarfernandez.deliverypro.domain.DataString;

import java.util.ArrayList;

import adapter.DatosRutaAdapter;

public class RutasActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RutasController rutasController;
    private Spinner spinner;
    private DatosRutaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rutas);
        setTitle("Rutas");
        rutasController = new RutasController(this);
        spinner = (Spinner) findViewById(R.id.spinner2);
        rv = (RecyclerView) findViewById(R.id.rvDatos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new DatosRutaAdapter();
        rv.setAdapter(adapter);
        rutasController.getRutasInfo();
    }

    public void fillRuta(ArrayList<DataString> data) {
        adapter.updateData(data);
    }

    public void fillDatosRutas(ArrayList<String> rutas) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rutas);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if ((position != 0) && (id != 0)) {
                            Object item = parent.getItemAtPosition(position);
                            String stringItem = (String) item;
                            rutasController.getRutaById(stringItem);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
    }
}
