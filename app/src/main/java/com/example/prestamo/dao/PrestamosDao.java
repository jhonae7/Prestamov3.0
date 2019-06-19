package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.prestamo.Prestamo;

import java.util.List;

@Dao
public interface PrestamosDao {
    @Insert
    Long insertar (Prestamo prestamo);

    @Delete
    void borrar (Prestamo prestamo);

    @Update
    void actualizar (Prestamo prestamo);

    @Query("select * from prestamostb")
    List<Prestamo> obtenerPrestamos();
}
