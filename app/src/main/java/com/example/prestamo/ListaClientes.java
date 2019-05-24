package com.example.prestamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {
    public static List<Cliente> clientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        RecyclerView rvClientes = findViewById(R.id.rvClientes);
        Bundle bundle = getIntent().getExtras();
        clientes = (List<Cliente>) bundle.get("cliente");
        rvClientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvClientes.setLayoutManager(linearLayoutManager);
        RVAdapter adapter = new RVAdapter(clientes);
        rvClientes.setAdapter(adapter);
    }
}
