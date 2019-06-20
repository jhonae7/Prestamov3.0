package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PrestamosActivity extends AppCompatActivity {
    public static int n;
    private TextView Cliente, MontoCredito, Interes, Plazo, FechaActual, FechaFinal, MontoPagar, MontoCuota;
    PrestamoConCliente prestamoConCliente;
    List<Pagos> pagosList = new ArrayList<>();
    DBclass dBclass;
    ListView listView;

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
    }
}