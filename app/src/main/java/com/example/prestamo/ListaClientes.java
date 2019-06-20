package com.example.prestamo;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {
    public List<ClienteConPrestamo> clientes = new ArrayList<>();
    DBclass dBclass;
    RecyclerView rvClientes;
    public RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        rvClientes = findViewById(R.id.rvClientes);
        dBclass = Room.databaseBuilder(this,DBclass.class,"db").allowMainThreadQueries().build();
        clientes = dBclass.clientesDao().ObtenerTodosClientes();
        rvAdapter = new RVAdapter(clientes);
        rvAdapter.setOnClickDeleteItemListener(new RVAdapter.OnClickDeleteItemListener() {
            @Override
            public void onClickDeleteItemListener(final Cliente cliente, final int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListaClientes.this);
                builder.setMessage("Desea eliminar al cliente "+ cliente.getNombre() +"?");
                builder.setNegativeButton("No", null);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dBclass.clientesDao().borrar(cliente);
                        clientes.remove(cliente);
                        Toast.makeText(ListaClientes.this, "", Toast.LENGTH_SHORT).show();
                        rvAdapter.notifyItemRemoved(pos);
                        rvAdapter.notifyDataSetChanged();

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                rvAdapter.notifyDataSetChanged();
            }
        });
        rvAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Cliente cliente, int pos) {
                Intent intent = new Intent(ListaClientes.this, ClienteActivity.class);
                intent.putExtra("cliente", cliente);
                startActivityForResult(intent, 3333);
            }
        });
        rvAdapter.setOnClickEditListener(new RVAdapter.OnClickEditItemListener() {
            @Override
            public void onClickEditItemlistener(Cliente cliente, int pos) {
                Intent intent = new Intent(ListaClientes.this, MainActivity.class);
                intent.putExtra("ID", cliente.getId_Cliente());
                intent.putExtra("posicion", pos);
                startActivityForResult(intent, 4444);
            }
        });
        //Bundle bundle = getIntent().getExtras();
        rvClientes.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvClientes.setLayoutManager(linearLayoutManager);
        rvClientes.setAdapter(rvAdapter);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==3333 && resultCode!=0){
            Prestamo prestamo = (Prestamo) data.getExtras().getSerializable("prestamo");
            Intent intent = new Intent();
            intent.putExtra("prestamo", prestamo);
            setResult(RESULT_OK, intent);
        }
        if(requestCode==4444 && resultCode!=0){
            Cliente cliente = (Cliente) data.getExtras().getSerializable("cliente");
            dBclass.clientesDao().actualizar(cliente);
            int pos = data.getExtras().getInt("posicion");
            ClienteConPrestamo clienteConPrestamo = new ClienteConPrestamo();
            clienteConPrestamo.setCliente(cliente);
            clientes.set(pos, clienteConPrestamo);
            rvClientes.getAdapter().notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
