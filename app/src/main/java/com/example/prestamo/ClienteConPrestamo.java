package com.example.prestamo;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class ClienteConPrestamo {
    @Embedded
    private Cliente cliente;
    @Relation(entity = Prestamo.class, parentColumn = "id_Cliente", entityColumn = "ID_CLIENTE")
    private List<Prestamo> prestamoList = new ArrayList<>();

    public Cliente getCliente(){
        return cliente;
    }
    public void setCliente (Cliente cliente){

        this.cliente = cliente;
    }

    public void setPrestamoList(List<Prestamo> prestamoList){

        this.prestamoList = prestamoList;
    }

    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }
}
