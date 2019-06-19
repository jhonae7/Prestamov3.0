package com.example.prestamo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "ClientesTB")
public class Cliente implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int id_Cliente;
    String nombre;
    String apellido;
    String telefono;
    String sexo;
    String cedula;
    String ocupacion;
    String direccion;
    public Cliente (){
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.sexo = "";
        this.cedula = "";
        this.ocupacion = "";
        this.direccion = "";
    }

    /*public int getId() {
        return id_Cliente;
    }

    public void setId(int id) {
        this.id_Cliente = id_Cliente;
    }*/

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
