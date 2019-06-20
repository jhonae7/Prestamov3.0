package com.example.prestamo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

@Entity (tableName = "PrestamosTb")
public class Prestamo implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id;

    @ForeignKey(entity = Cliente.class, parentColumns = "id", childColumns = "id_Cliente", onDelete = CASCADE, onUpdate = RESTRICT)
    public int ID_CLIENTE;
    String cliente;
    String monto_credito;
    String interes;
    String plazo;
    String fecha_actual;
    String fecha_final;
    String monto_pagar;
    String monto_cuota;
    public Prestamo(){
        this.cliente = "";
        this.monto_credito = "";
        this.interes = "";
        this.plazo = "";
        this.fecha_actual= "";
        this.fecha_final = "";
        this.monto_pagar = "";
        this.monto_cuota = "";
    }

    public int getID_CLIENTE() {
        return ID_CLIENTE;
    }

    public void setID_CLIENTE(int ID_CLIENTE) {
        this.ID_CLIENTE = ID_CLIENTE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMonto_credito() {
        return monto_credito;
    }

    public void setMonto_credito(String monto_credito) {
        this.monto_credito = monto_credito;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(String fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(String monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    public String getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(String monto_cuota) {
        this.monto_cuota = monto_cuota;
    }
}
