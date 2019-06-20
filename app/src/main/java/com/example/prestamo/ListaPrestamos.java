package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
        /*int i = prestamoList.size();
        if (i==0){
            Toast.makeText(this, "No se han realizado prestamos", Toast.LENGTH_SHORT).show();
        }
        else
        {*/
                adaptador = new Adaptador(prestamoList, this);
                lvPrestamos.setAdapter(adaptador);
                //lvPrestamos.getAdapter();
                //arrayAdapter.notifyDataSetChanged();

        //}
    }
}
