package com.example.prestamo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.prestamo.dao.ClientesDao;
import com.example.prestamo.dao.PagosDao;
import com.example.prestamo.dao.PrestamosDao;

@Database(version = 1,entities = {Cliente.class, Prestamo.class, Pagos.class})
public abstract class DBclass extends RoomDatabase {
    public abstract ClientesDao clientesDao();
    public abstract PrestamosDao prestamosDao();
    public abstract PagosDao pagosDao();
}
