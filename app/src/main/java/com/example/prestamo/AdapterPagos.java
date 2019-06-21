package com.example.prestamo;

import android.content.Context;
import android.graphics.PaintFlagsDrawFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterPagos extends BaseAdapter {
    private Context context;
    List<Pagos> pagosList = new ArrayList<>();
    TextView Cuota, item;
    public AdapterPagos(List<Pagos> pagosList, Context context) {
        this.context = context;
        this.pagosList = pagosList;
    }

    @Override
    public int getCount() {
        return pagosList.size();
    }

    @Override
    public Object getItem(int position) {
        return pagosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_pago, null);
        Cuota = convertView.findViewById(R.id.twPago);
        item = convertView.findViewById(R.id.twID);
        Cuota.setText(String.valueOf(pagosList.get(position).getPagos()));
        item.setText(String.valueOf(pagosList.get(position).getId()));
        return null;
    }
}
