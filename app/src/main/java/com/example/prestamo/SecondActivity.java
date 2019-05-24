package com.example.prestamo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private TextView MontoCuota, MontoPagar;
    private EditText MontoCredito, Plazo, FechaActual, FechaFinal;
    private Spinner Interes;
    public Spinner spClientes;
    public static List<String> clientes =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String fecha = new SimpleDateFormat("d/M/yyyy").format(new Date());
        FechaActual = findViewById(R.id.etFecha);
        FechaFinal = findViewById(R.id.etFechaFinal);
        FechaActual.setText(fecha);
        spClientes = findViewById(R.id.spClientes);
        MontoCredito = findViewById(R.id.etMontoCredito);
        Interes = findViewById(R.id.spInteres);
        Plazo = findViewById(R.id.etPlazo);
        FechaActual = findViewById(R.id.etFecha);
        FechaFinal = findViewById(R.id.etFechaFinal);
        MontoPagar = findViewById(R.id.twMontoPagar1);
        MontoCuota = findViewById(R.id.twMontoCuota1);
        Clientes();
        Interes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saldo_pendiente();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        MontoCredito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saldo_pendiente();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //Plazo = findViewById(R.id.etPlazo);
        Plazo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saldo_pendiente();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void saldo_pendiente()
    {
        int monto = 0;
        int porcentaje = 0;
        int meses = 0;
        float interes = 0;
        float saldo = 0;
        float cuota = 0;
        if(MontoCredito.getText().toString().length()!=0){
            monto=Integer.valueOf(MontoCredito.getText().toString());
        }
        porcentaje=Integer.valueOf(Interes.getSelectedItem().toString());
        interes =Float.valueOf((monto*porcentaje)/100);
        saldo = Float.valueOf(monto + interes);
        MontoPagar.setText(String.valueOf(saldo));
        Calendar fechafinal = Calendar.getInstance();
        int day = fechafinal.get(Calendar.DAY_OF_MONTH);
        int month;
        int year;
        if(Plazo.getText().toString().length()!=0){
            meses =Integer.valueOf(Plazo.getText().toString());
            saldo = Float.valueOf(monto + (interes*meses));
            cuota = Float.valueOf(saldo/meses);
            MontoPagar.setText(String.valueOf(saldo));
            MontoCuota.setText(String.valueOf(cuota));
            fechafinal.add(Calendar.MONTH, meses);
            month = fechafinal.get(Calendar.MONTH);
            month++;
            year = fechafinal.get(Calendar.YEAR);
            FechaFinal.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
        }
        else{
            MontoCuota.setText(String.valueOf(saldo));
            fechafinal.add(Calendar.MONTH, 1);
            month = fechafinal.get(Calendar.MONTH);
            month++;
            year = fechafinal.get(Calendar.YEAR);
            FechaFinal.setText(String.valueOf(day)+"/"+String.valueOf(month)+"/"+String.valueOf(year));
        }
    }
    public void Clientes(){
        for (int n=0; n<PrincipalActivity.listaClientes.size(); n++){
            String item = PrincipalActivity.listaClientes.get(n).nombre + " " + PrincipalActivity.listaClientes.get(n).apellido;
            clientes.add(item);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clientes);
        spClientes.setAdapter(adapter);
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
                String cliente = spClientes.getSelectedItem().toString();
                String credito = MontoCredito.getText().toString();
                String plazo = Plazo.getText().toString();
                String interes = Interes.getSelectedItem().toString();
                String fecha_actual = FechaActual.getText().toString();
                String fecha_final = FechaFinal.getText().toString();
                String monto_pagar = MontoPagar.getText().toString();
                String monto_cuota = MontoCuota.getText().toString();
                Prestamo prestamo = new Prestamo();
                prestamo.cliente = cliente;
                prestamo.monto_credito = credito;
                prestamo.plazo = plazo;
                prestamo.interes = interes;
                prestamo.fecha_actual = fecha_actual;
                prestamo.fecha_final = fecha_final;
                prestamo.monto_pagar = monto_pagar;
                prestamo.monto_cuota = monto_cuota;
                Intent intent = new Intent();
                intent.putExtra("prestamo", prestamo);
                setResult(RESULT_OK, intent);
                clientes.removeAll(clientes);
                finish();
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnCancelar:
                intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                clientes.removeAll(clientes);
                finish();
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
