package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    public TextView Actividades;
    public static List<ClienteConPrestamo> listaClientes = new ArrayList<>();
    public static List<PrestamoConCliente> listaPrestamos = new ArrayList<>();
    public DBclass dBclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        dBclass = Room.databaseBuilder(this, DBclass.class, "db").allowMainThreadQueries().build();
        Actividades = findViewById(R.id.tvActividades);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnAcerdade:
                Toast.makeText(this, "Electiva Android", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnVerCliente:
                Intent intent2 = new Intent(this, ListaClientes.class);
                //intent2.putExtra("clientes", (Serializable) listaClientes);
                startActivityForResult(intent2, 2222);
                break;
            case R.id.mnNuevoCliente:
                Intent intent = new Intent(this, MainActivity.class);
                startActivityForResult(intent, 1111);
                break;
            case R.id.mnVerPrestamos:
                /*Intent intent3 = new Intent(this, ListaPrestamos.class);
                startActivityForResult(intent3, 0000);*/
                Intent intent3 = new Intent(PrincipalActivity.this, ListaPrestamos.class);
                startActivity(intent3);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1111) {
            if (requestCode == 0){
                Toast.makeText(this, "No se han ingresado Clientes", Toast.LENGTH_SHORT).show();
            }
            else {
                Cliente cliente = (Cliente) data.getExtras().getSerializable("cliente");
                ClienteConPrestamo clienteConPrestamo = new ClienteConPrestamo();
                clienteConPrestamo.setCliente(cliente);
                listaClientes.add(clienteConPrestamo);
                Long id = dBclass.clientesDao().insertar(cliente);
                cliente.setId_Cliente(id.intValue());
            }
        }
        if(requestCode==2222)
        {
            if (resultCode!=0)
            {
                Prestamo prestamo = (Prestamo) data.getExtras().getSerializable("prestamo");
                PrestamoConCliente prestamoConCliente = new PrestamoConCliente();
                prestamoConCliente.setPrestamo(prestamo);
                listaPrestamos.add(prestamoConCliente);
                Long id = dBclass.prestamosDao().insertar(prestamo);
                prestamo.setID_CLIENTE(id.intValue());
                Toast.makeText(this, ""+prestamo.getMonto_credito(), Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextual, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.borrar:
                Actividades.setText("");
                break;
            case R.id.copiar:
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("CortaPapeles", Actividades.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                break;
        }
        return super.onContextItemSelected(item);
    }
}

