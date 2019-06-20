package com.example.prestamo;

import android.content.Intent;
import android.support.annotation.Nullable;
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
    Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        Bundle bundle = getIntent().getExtras();
        cliente = (Cliente) bundle.getSerializable("cliente");
        Clientes(cliente);
    }
    public void Clientes(Cliente cliente){
        Nombre =  findViewById(R.id.twNombreD);
        Apellido = findViewById(R.id.twApellidoD);
        Sexo = findViewById(R.id.twSexoD);
        Telefono = findViewById(R.id.twTelefonoD);
        Cedula = findViewById(R.id.twCedulaD);
        Ocupacion = findViewById(R.id.twOcupacionD);
        Direccion = findViewById(R.id.twDireccionD);
        Nombre.setText(cliente.nombre);
        Apellido.setText(cliente.apellido);
        Sexo.setText(cliente.sexo);
        Telefono.setText(cliente.telefono);
        Cedula.setText(cliente.cedula);
        Ocupacion.setText(cliente.ocupacion);
        Direccion.setText(cliente.direccion);
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
                intent.putExtra("cliente", cliente);
                startActivityForResult(intent, 5555);
                Toast.makeText(this, "Nuevo Prestamo", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==5555){
            if(requestCode!=0){
                Prestamo prestamo = (Prestamo) data.getExtras().getSerializable("prestamo");
                Intent intent = new Intent();
                intent.putExtra("prestamo", prestamo);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}