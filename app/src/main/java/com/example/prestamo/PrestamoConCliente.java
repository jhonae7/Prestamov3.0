package com.example.prestamo;

import android.arch.persistence.room.Embedded;

public class PrestamoConCliente {
    @Embedded
    private Cliente cliente;

    @Embedded
    private Prestamo prestamo;

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public Prestamo getPrestamo(){
        return prestamo;
    }
    public void setPrestamo(Prestamo prestamo){
        this.prestamo = prestamo;
    }
}
