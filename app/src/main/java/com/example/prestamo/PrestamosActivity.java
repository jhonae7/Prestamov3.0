package com.example.prestamo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PrestamosActivity extends AppCompatActivity {
    public static int n;
    private TextView Cliente, MontoCredito, Interes, Plazo, FechaActual, FechaFinal, MontoPagar, MontoCuota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prestamos);
        Cliente = findViewById(R.id.twClienteD);
        MontoCredito = findViewById(R.id.twMontoCreditoD);
        Interes = findViewById(R.id.twInteresD);
        Plazo = findViewById(R.id.twPlazoD);
        FechaActual = findViewById(R.id.twFechaD);
        FechaFinal = findViewById(R.id.twFechaFinD);
        MontoPagar = findViewById(R.id.twMontoPagarD);
        MontoCuota = findViewById(R.id.twMontoCuotaD);
        n = 0;
        Prestamos(n);
        Button siguiente = findViewById(R.id.btSiguienteP);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n==(PrincipalActivity.listaPrestamos.size()-1)){
                    Toast.makeText(PrestamosActivity.this, "Llego al final", Toast.LENGTH_SHORT).show();
                }
                else{
                    n++;
                    Prestamos(n);
                }
            }
        });
        Button anterior = findViewById(R.id.btAnteriorP);
        anterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n==0){
                    Toast.makeText(PrestamosActivity.this, "Llego al inicio", Toast.LENGTH_SHORT).show();
                }
                else{
                    n--;
                    Prestamos(n);
                }
            }
        });
    }
    public void Prestamos(int n){
        Prestamo item = PrincipalActivity.listaPrestamos.get(n);
        Cliente.setText(item.cliente);
        MontoCredito.setText(item.monto_credito);
        Interes.setText(item.interes);
        Plazo.setText(item.plazo);
        FechaActual.setText(item.fecha_actual);
        FechaFinal.setText(item.fecha_final);
        MontoPagar.setText(item.monto_pagar);
        MontoCuota.setText(item.monto_cuota);

    }
}
