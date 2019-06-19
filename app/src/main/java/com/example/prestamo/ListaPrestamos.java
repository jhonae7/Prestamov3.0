package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaPrestamos extends AppCompatActivity {
    private List<String> prestamos;
    //private List<Prestamo> prestamoList = new ArrayList<>();
    private DBclass dBclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prestamos);
        dBclass = Room.databaseBuilder(this, DBclass.class, "db").allowMainThreadQueries().build();
        ListView lvPrestamos = (ListView) findViewById(R.id.lvprestamo);
        //int n = 0;
        prestamos = new ArrayList<>();
        int i = PrincipalActivity.listaPrestamos.size();
        if (i==0){
            Toast.makeText(this, "No se han realizado prestamos", Toast.LENGTH_SHORT).show();
        }
        else
        {
            /*int j =0;
            for (j=0; j<PrincipalActivity.listaPrestamos.size(); j++) {
                Prestamo item = PrincipalActivity.listaPrestamos.get(j);
                String cliente = item.cliente;
                String monto = item.monto_credito;
                String plazo = item.plazo;
                prestamos.add(cliente + "\n" + "            " + monto + "           " + plazo);
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, prestamos);
                lvPrestamos.setAdapter(arrayAdapter);
                //j++;
                lvPrestamos.getAdapter();
                arrayAdapter.notifyDataSetChanged();
            }*/
        }
    }
}
