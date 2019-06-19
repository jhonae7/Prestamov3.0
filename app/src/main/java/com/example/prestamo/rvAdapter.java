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

class RVAdapter extends RecyclerView.Adapter<RVAdapter.ClienteViewHolder> implements View.OnClickListener {

    private OnItemClickListener onItemClickListener;
    private OnClickDeleteItemListener onClickDeleteItemListener;
    private OnClickEditItemListener onClickEditItemListener;
    private List<ClienteConPrestamo> clientes;

    @Override
    public void onClick(View v) {

    }

    public interface OnItemClickListener{
        void onItemClickListener(Cliente cliente, int pos);
    }
    public interface OnClickDeleteItemListener{
        void onClickDeleteItemListener(Cliente cliente, int pos);
    }
    public interface OnClickEditItemListener {
        void onClickEditItemlistener(Cliente cliente, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnClickDeleteItemListener(OnClickDeleteItemListener onClickDeleteItemListener){
        this.onClickDeleteItemListener = onClickDeleteItemListener;
    }
    public void setOnClickEditListener(OnClickEditItemListener onClickEditItemListener){
        this.onClickEditItemListener = onClickEditItemListener;
    }
    RVAdapter(List<ClienteConPrestamo> clientes){
        this.clientes = clientes;
    }
    public class ClienteViewHolder extends RecyclerView.ViewHolder{

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
            imBorrar = itemView.findViewById(R.id.imBorrar);
            imEditar = itemView.findViewById(R.id.imEditar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(clientes.get(getAdapterPosition()).getCliente(), getAdapterPosition());
                }
            });
            imBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickDeleteItemListener.onClickDeleteItemListener(clientes.get(getAdapterPosition()).getCliente(), getAdapterPosition());
                }
            });
            imEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEditItemListener.onClickEditItemlistener(clientes.get(getAdapterPosition()).getCliente(), getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cliente, viewGroup, false);
        v.setOnClickListener(this);
        ClienteViewHolder clienteViewHolder = new ClienteViewHolder(v);
        return clienteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteViewHolder clienteViewHolder, int i) {
        clienteViewHolder.itemNombre.setText(clientes.get(i).getCliente().nombre);
        clienteViewHolder.itemApellido.setText(clientes.get(i).getCliente().apellido);
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
