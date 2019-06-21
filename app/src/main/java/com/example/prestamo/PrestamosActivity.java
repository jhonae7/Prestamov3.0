package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrestamosActivity extends AppCompatActivity {
    private TextView Cliente, MontoCredito, Interes, Plazo, FechaActual, FechaFinal, MontoPagar, MontoCuota;
    PrestamoConCliente prestamoConCliente;
    List<Pagos> pagosList = new ArrayList<>();
    DBclass dBclass;
    ListView listView;
    int pago = 0;
    AdapterPagos adapterPagos = new AdapterPagos(pagosList, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);
        dBclass = Room.databaseBuilder(getApplicationContext(), DBclass.class, "db").allowMainThreadQueries().build();
        listView = findViewById(R.id.ListPagos);
        Bundle bundle = getIntent().getExtras();
        prestamoConCliente = (PrestamoConCliente) bundle.getSerializable("ID");
        pagosList.addAll(dBclass.pagosDao().ObtenerPagos(prestamoConCliente.getPrestamo().getId()));

        Cliente = findViewById(R.id.twClienteD);
        MontoCredito = findViewById(R.id.twMontoCreditoD);
        Interes = findViewById(R.id.twInteresD);
        Plazo = findViewById(R.id.twPlazoD);
        FechaActual = findViewById(R.id.twFechaD);
        FechaFinal = findViewById(R.id.twFechaFinD);
        MontoPagar = findViewById(R.id.twMonto2);
        MontoCuota = findViewById(R.id.twCuota2);

        Cliente.setText(prestamoConCliente.getCliente().getNombre());
        MontoCredito.setText(prestamoConCliente.getPrestamo().getMonto_credito());
        Interes.setText(prestamoConCliente.getPrestamo().getInteres());
        Plazo.setText(prestamoConCliente.getPrestamo().getPlazo());
        FechaActual.setText(prestamoConCliente.getPrestamo().getFecha_actual());
        FechaFinal.setText(prestamoConCliente.getPrestamo().getFecha_final());
        MontoPagar.setText(prestamoConCliente.getPrestamo().getMonto_pagar());
        MontoCuota.setText("0");
        //listView.setAdapter(adapterPagos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu4, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnAgregarPago:
                AlertDialog.Builder builder = new AlertDialog.Builder(PrestamosActivity.this);
                builder.setTitle("Cuota");
                final EditText Cuota = new EditText (PrestamosActivity.this);
                Cuota.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(Cuota);
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pago += Integer.parseInt(Cuota.getText().toString());
                        MontoCuota.setText(String.valueOf(pago));
                        Actualizar(Cuota);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Actualizar(EditText cuota){
        Pagos pagos = new Pagos();
        pagos.setPagos(Integer.parseInt(cuota.getText().toString()));
        pagos.setId_prestamo(prestamoConCliente.getPrestamo().getId());
        MontoCuota.setText(String.valueOf(pago));
        try{
            long id = dBclass.pagosDao().insertar(pagos);
            pagosList.add(pagos);
            adapterPagos.notifyDataSetChanged();
        }catch (SQLiteConstraintException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}