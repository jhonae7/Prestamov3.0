package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private EditText Nombre, Telefono, Cedula, Direccion, Apellido, Ocupacion;
    private Spinner Sexo;
    DBclass dBclass;
    int id = 0;
    int pos = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBclass = Room.databaseBuilder(this, DBclass.class, "db").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            id = bundle.getInt("ID");
            pos = bundle.getInt("posicion");
            if(id!=0){
                update(id);
            }
        }
        Nombre = findViewById(R.id.edNombre);
        Apellido = findViewById(R.id.edApellido);
        Sexo = findViewById(R.id.spSexo);
        Telefono = findViewById(R.id.edTelefono);
        Cedula = findViewById(R.id.edCedula);
        Ocupacion = findViewById(R.id.edOcupacion);
        Direccion = findViewById(R.id.edDireccion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu2) {
        getMenuInflater().inflate(R.menu.menu2, menu2);
        return super.onCreateOptionsMenu(menu2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnGuardar:
                if (Nombre.getText().toString().isEmpty()) {
                    Nombre.setError("Campo Obligatorio");
                    Toast.makeText(MainActivity.this,"Revise los campos", Toast.LENGTH_SHORT).show();
                } else if (Telefono.getText().toString().isEmpty()) {
                    Telefono.setError("Campo obligatorio");
                    Toast.makeText(MainActivity.this, "Revise los campos", Toast.LENGTH_SHORT).show();
                } else if (Cedula.getText().toString().isEmpty()) {
                    Cedula.setError("Campo Obligatorio");
                    Toast.makeText(MainActivity.this, "Revise los campos", Toast.LENGTH_SHORT).show();
                } else if (Direccion.getText().toString().isEmpty()) {
                    Direccion.setError("Campo obligatorio");
                    Toast.makeText(MainActivity.this, "Revise los campos", Toast.LENGTH_SHORT).show();
                } else {
                    Cliente cliente = new Cliente();
                    String nombre = Nombre.getText().toString();
                    String apellido = Apellido.getText().toString();
                    String sexo = Sexo.getSelectedItem().toString();
                    String telefono = Telefono.getText().toString();
                    String cedula = Cedula.getText().toString();
                    String ocupacion = Ocupacion.getText().toString();
                    String direccion = Direccion.getText().toString();
                    cliente.nombre = nombre;
                    cliente.apellido = apellido;
                    cliente.sexo = sexo;
                    cliente.telefono = telefono;
                    cliente.cedula = cedula;
                    cliente.ocupacion = ocupacion;
                    cliente.direccion = direccion;
                    if (id!=0){
                        cliente.setId_Cliente(id);
                    }
                    Intent intent = new Intent();
                    /*Long id=dBclass.clientesDao().insertar(cliente);
                    cliente.setId_Cliente(id.intValue());*/
                    intent.putExtra("posicion", pos);
                    intent.putExtra("cliente", (Serializable) cliente);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnCancelar:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void update(int id){
        Cliente edit = new Cliente();
        edit = dBclass.clientesDao().ObtenerCliente(id);
        Nombre = findViewById(R.id.edNombre);
        Nombre.setText(edit.getNombre());
        Apellido = findViewById(R.id.edApellido);
        Apellido.setText(edit.getApellido());
        Sexo = findViewById(R.id.spSexo);
        if(edit.getSexo() == "Masculino"){
            Sexo.setSelection(0);
        }
        else{
            Sexo.setSelection(1);
        }
        Telefono = findViewById(R.id.edTelefono);
        Telefono.setText(edit.getTelefono());
        Cedula = findViewById(R.id.edCedula);
        Cedula.setText(edit.getCedula());
        Ocupacion = findViewById(R.id.edOcupacion);
        Ocupacion.setText(edit.getOcupacion());
        Direccion = findViewById(R.id.edDireccion);
        Direccion.setText(edit.getDireccion());
    }
}