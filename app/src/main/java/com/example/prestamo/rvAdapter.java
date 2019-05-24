package com.example.prestamo;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ClienteViewHolder> {

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        public TextView itemNombre;
        public TextView itemApellido;
        public ImageButton imEditar;
        public ImageButton imBorrar;
        CardView cvClientes;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);
            cvClientes = itemView.findViewById(R.id.cvClientes);
            itemNombre = itemView.findViewById(R.id.itemNombre);
            itemApellido = itemView.findViewById(R.id.itemApellido);
            imBorrar = itemView.findViewById(R.id.imEditar);
            imEditar = itemView.findViewById(R.id.imBorrar);

        }
    }
    List<Cliente> clientes;
    RVAdapter (List<Cliente> clientes){
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cliente, viewGroup, false);
        ClienteViewHolder clienteViewHolder = new ClienteViewHolder(v);
        return clienteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder clienteViewHolder, int i) {
        clienteViewHolder.itemNombre.setText(clientes.get(i).nombre);
        clienteViewHolder.itemApellido.setText(clientes.get(i).apellido);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }
}
