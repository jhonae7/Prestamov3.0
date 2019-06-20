package com.example.prestamo.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.prestamo.Pagos;

import java.util.List;

@Dao
public interface PagosDao {
    @Insert
    long insertart(Pagos pagos);
    @Query("select * from Pagos where id_prestamo = :id")
    List<Pagos> ObtenerPagos(int id);
}
