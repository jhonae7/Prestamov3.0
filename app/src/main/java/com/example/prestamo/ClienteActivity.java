package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClienteActivity extends AppCompatActivity {
    private TextView Nombre, Telefono, Sexo, Cedula, Direccion, Apellido, Ocupacion;
    public static int n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Nombre =  findViewById(R.id.twNombreD);
        Apellido = findViewById(R.id.twApellidoD);
        Sexo = findViewById(R.id.twSexoD);
        Telefono = findViewById(R.id.twTelefonoD);
        Cedula = findViewById(R.id.twCedulaD);
        Ocupacion = findViewById(R.id.twOcupacionD);
        Direccion = findViewById(R.id.twDireccionD);
        n = 0;
        Clientes(n);
    }
    public void Clientes(int n){
        Cliente item = PrincipalActivity.listaClientes.get(n);
        Nombre.setText(item.nombre);
        Apellido.setText(item.apellido);
        Sexo.setText(item.sexo);
        Telefono.setText(item.telefono);
        Cedula.setText(item.cedula);
        Ocupacion.setText(item.ocupacion);
        Direccion.setText(item.direccion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu3) {
        getMenuInflater().inflate(R.menu.menu3, menu3);
        return super.onCreateOptionsMenu(menu3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnAgregar:
                Intent intent = new Intent(ClienteActivity.this, SecondActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Nuevo Prestamo", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}