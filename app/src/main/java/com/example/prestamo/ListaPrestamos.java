package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaPrestamos extends AppCompatActivity {
    public List<PrestamoConCliente> prestamoList = new ArrayList<>();
    public Adaptador adaptador;
    private DBclass dBclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prestamos);
        dBclass = Room.databaseBuilder(this, DBclass.class, "db").allowMainThreadQueries().build();
        ListView lvPrestamos = (ListView) findViewById(R.id.lvprestamo);
        prestamoList.addAll(dBclass.prestamosDao().ObtenerPrestamos());
        int i = prestamoList.size();
        if (i==0){
            Toast.makeText(this, "No se han realizado prestamos", Toast.LENGTH_SHORT).show();
        }
        else
        {
                adaptador = new Adaptador(prestamoList, this);
                lvPrestamos.setAdapter(adaptador);
                lvPrestamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        PrestamoConCliente prestamoConCliente = prestamoList.get(position);
                        Intent intent = new Intent(ListaPrestamos.this, PrestamosActivity.class);
                        intent.putExtra("ID", prestamoConCliente);
                        startActivityForResult(intent, 6666);
                    }
                });
        }
    }
}
