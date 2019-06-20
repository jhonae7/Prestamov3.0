package com.example.prestamo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends BaseAdapter {
    private Context context;
    private List<PrestamoConCliente> prestamoConClienteList;
    private TextView textNombre, textMonto, textPlazo;

    public Adaptador(List<PrestamoConCliente> prestamoConClienteList, Context context){
        this.prestamoConClienteList = prestamoConClienteList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return prestamoConClienteList.size();
    }

    @Override
    public Object getItem(int position) {
        return prestamoConClienteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PrestamoConCliente prestamoConCliente = (PrestamoConCliente) getItem(position);
        convertView = (View) LayoutInflater.from(context).inflate(R.layout.item_prestamo, null);
        textNombre = convertView.findViewById(R.id.textNombre);
        textMonto = convertView.findViewById(R.id.textMonto);
        textPlazo = convertView.findViewById(R.id.textPlazo);
        textNombre.setText(prestamoConClienteList.get(position).getCliente().getNombre());
        textMonto.setText(prestamoConCliente.getPrestamo().monto_credito);
        textPlazo.setText(prestamoConCliente.getPrestamo().plazo);
        return convertView;
    }
}
