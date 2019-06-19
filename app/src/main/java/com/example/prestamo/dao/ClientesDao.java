package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.Cliente;
import com.example.prestamo.ClienteConPrestamo;

import java.util.List;

@Dao
public interface ClientesDao {
    @Insert
    Long insertar (Cliente cliente);

    @Delete
    void borrar (Cliente cliente);

    @Update
    void actualizar (Cliente cliente);

    @Query("select * from clientestb")
    List<ClienteConPrestamo> ObtenerTodosClientes();

    @Query("select * from clientestb where id_Cliente=:id")
    Cliente ObtenerCliente(int id);
}
