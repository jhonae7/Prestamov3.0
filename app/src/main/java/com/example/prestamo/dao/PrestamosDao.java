package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.example.prestamo.Prestamo;
import com.example.prestamo.PrestamoConCliente;

import java.util.Collection;
import java.util.List;

@Dao
public interface PrestamosDao {
    @Insert
    Long insertar (Prestamo prestamo);

    @Delete
    void borrar (Prestamo prestamo);

    @Update
    void actualizar (Prestamo prestamo);

    @Query("select * from prestamostb inner join clientestb on clientestb.id_Cliente=prestamostb.ID_CLIENTE")
    List<PrestamoConCliente> ObtenerPrestamos();
}
