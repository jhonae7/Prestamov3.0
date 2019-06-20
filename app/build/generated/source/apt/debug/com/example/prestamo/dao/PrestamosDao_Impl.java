package com.example.prestamo.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.example.prestamo.Cliente;
import com.example.prestamo.Prestamo;
import com.example.prestamo.PrestamoConCliente;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PrestamosDao_Impl implements PrestamosDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPrestamo;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPrestamo;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPrestamo;

  public PrestamosDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPrestamo = new EntityInsertionAdapter<Prestamo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `PrestamosTb`(`id`,`ID_CLIENTE`,`cliente`,`monto_credito`,`interes`,`plazo`,`fecha_actual`,`fecha_final`,`monto_pagar`,`monto_cuota`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prestamo value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.ID_CLIENTE);
        if (value.getCliente() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCliente());
        }
        if (value.getMonto_credito() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMonto_credito());
        }
        if (value.getInteres() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getInteres());
        }
        if (value.getPlazo() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPlazo());
        }
        if (value.getFecha_actual() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFecha_actual());
        }
        if (value.getFecha_final() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getFecha_final());
        }
        if (value.getMonto_pagar() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMonto_pagar());
        }
        if (value.getMonto_cuota() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getMonto_cuota());
        }
      }
    };
    this.__deletionAdapterOfPrestamo = new EntityDeletionOrUpdateAdapter<Prestamo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `PrestamosTb` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prestamo value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfPrestamo = new EntityDeletionOrUpdateAdapter<Prestamo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PrestamosTb` SET `id` = ?,`ID_CLIENTE` = ?,`cliente` = ?,`monto_credito` = ?,`interes` = ?,`plazo` = ?,`fecha_actual` = ?,`fecha_final` = ?,`monto_pagar` = ?,`monto_cuota` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Prestamo value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.ID_CLIENTE);
        if (value.getCliente() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCliente());
        }
        if (value.getMonto_credito() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMonto_credito());
        }
        if (value.getInteres() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getInteres());
        }
        if (value.getPlazo() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPlazo());
        }
        if (value.getFecha_actual() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getFecha_actual());
        }
        if (value.getFecha_final() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getFecha_final());
        }
        if (value.getMonto_pagar() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMonto_pagar());
        }
        if (value.getMonto_cuota() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getMonto_cuota());
        }
        stmt.bindLong(11, value.getId());
      }
    };
  }

  @Override
  public Long insertar(Prestamo prestamo) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPrestamo.insertAndReturnId(prestamo);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void borrar(Prestamo prestamo) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPrestamo.handle(prestamo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void actualizar(Prestamo prestamo) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPrestamo.handle(prestamo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<PrestamoConCliente> ObtenerPrestamos() {
    final String _sql = "select * from prestamostb inner join clientestb on clientestb.id_Cliente=prestamostb.ID_CLIENTE";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfIDCLIENTE = _cursor.getColumnIndexOrThrow("ID_CLIENTE");
      final int _cursorIndexOfCliente = _cursor.getColumnIndexOrThrow("cliente");
      final int _cursorIndexOfMontoCredito = _cursor.getColumnIndexOrThrow("monto_credito");
      final int _cursorIndexOfInteres = _cursor.getColumnIndexOrThrow("interes");
      final int _cursorIndexOfPlazo = _cursor.getColumnIndexOrThrow("plazo");
      final int _cursorIndexOfFechaActual = _cursor.getColumnIndexOrThrow("fecha_actual");
      final int _cursorIndexOfFechaFinal = _cursor.getColumnIndexOrThrow("fecha_final");
      final int _cursorIndexOfMontoPagar = _cursor.getColumnIndexOrThrow("monto_pagar");
      final int _cursorIndexOfMontoCuota = _cursor.getColumnIndexOrThrow("monto_cuota");
      final int _cursorIndexOfIdCliente = _cursor.getColumnIndexOrThrow("id_Cliente");
      final int _cursorIndexOfNombre = _cursor.getColumnIndexOrThrow("nombre");
      final int _cursorIndexOfApellido = _cursor.getColumnIndexOrThrow("apellido");
      final int _cursorIndexOfTelefono = _cursor.getColumnIndexOrThrow("telefono");
      final int _cursorIndexOfSexo = _cursor.getColumnIndexOrThrow("sexo");
      final int _cursorIndexOfCedula = _cursor.getColumnIndexOrThrow("cedula");
      final int _cursorIndexOfOcupacion = _cursor.getColumnIndexOrThrow("ocupacion");
      final int _cursorIndexOfDireccion = _cursor.getColumnIndexOrThrow("direccion");
      final List<PrestamoConCliente> _result = new ArrayList<PrestamoConCliente>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PrestamoConCliente _item;
        final Prestamo _tmpPrestamo;
        if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfIDCLIENTE) && _cursor.isNull(_cursorIndexOfCliente) && _cursor.isNull(_cursorIndexOfMontoCredito) && _cursor.isNull(_cursorIndexOfInteres) && _cursor.isNull(_cursorIndexOfPlazo) && _cursor.isNull(_cursorIndexOfFechaActual) && _cursor.isNull(_cursorIndexOfFechaFinal) && _cursor.isNull(_cursorIndexOfMontoPagar) && _cursor.isNull(_cursorIndexOfMontoCuota))) {
          _tmpPrestamo = new Prestamo();
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          _tmpPrestamo.setId(_tmpId);
          _tmpPrestamo.ID_CLIENTE = _cursor.getInt(_cursorIndexOfIDCLIENTE);
          final String _tmpCliente_1;
          _tmpCliente_1 = _cursor.getString(_cursorIndexOfCliente);
          _tmpPrestamo.setCliente(_tmpCliente_1);
          final String _tmpMonto_credito;
          _tmpMonto_credito = _cursor.getString(_cursorIndexOfMontoCredito);
          _tmpPrestamo.setMonto_credito(_tmpMonto_credito);
          final String _tmpInteres;
          _tmpInteres = _cursor.getString(_cursorIndexOfInteres);
          _tmpPrestamo.setInteres(_tmpInteres);
          final String _tmpPlazo;
          _tmpPlazo = _cursor.getString(_cursorIndexOfPlazo);
          _tmpPrestamo.setPlazo(_tmpPlazo);
          final String _tmpFecha_actual;
          _tmpFecha_actual = _cursor.getString(_cursorIndexOfFechaActual);
          _tmpPrestamo.setFecha_actual(_tmpFecha_actual);
          final String _tmpFecha_final;
          _tmpFecha_final = _cursor.getString(_cursorIndexOfFechaFinal);
          _tmpPrestamo.setFecha_final(_tmpFecha_final);
          final String _tmpMonto_pagar;
          _tmpMonto_pagar = _cursor.getString(_cursorIndexOfMontoPagar);
          _tmpPrestamo.setMonto_pagar(_tmpMonto_pagar);
          final String _tmpMonto_cuota;
          _tmpMonto_cuota = _cursor.getString(_cursorIndexOfMontoCuota);
          _tmpPrestamo.setMonto_cuota(_tmpMonto_cuota);
        }  else  {
          _tmpPrestamo = null;
        }
        final Cliente _tmpCliente;
        if (! (_cursor.isNull(_cursorIndexOfIdCliente) && _cursor.isNull(_cursorIndexOfNombre) && _cursor.isNull(_cursorIndexOfApellido) && _cursor.isNull(_cursorIndexOfTelefono) && _cursor.isNull(_cursorIndexOfSexo) && _cursor.isNull(_cursorIndexOfCedula) && _cursor.isNull(_cursorIndexOfOcupacion) && _cursor.isNull(_cursorIndexOfDireccion))) {
          _tmpCliente = new Cliente();
          final int _tmpId_Cliente;
          _tmpId_Cliente = _cursor.getInt(_cursorIndexOfIdCliente);
          _tmpCliente.setId_Cliente(_tmpId_Cliente);
          final String _tmpNombre;
          _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
          _tmpCliente.setNombre(_tmpNombre);
          final String _tmpApellido;
          _tmpApellido = _cursor.getString(_cursorIndexOfApellido);
          _tmpCliente.setApellido(_tmpApellido);
          final String _tmpTelefono;
          _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
          _tmpCliente.setTelefono(_tmpTelefono);
          final String _tmpSexo;
          _tmpSexo = _cursor.getString(_cursorIndexOfSexo);
          _tmpCliente.setSexo(_tmpSexo);
          final String _tmpCedula;
          _tmpCedula = _cursor.getString(_cursorIndexOfCedula);
          _tmpCliente.setCedula(_tmpCedula);
          final String _tmpOcupacion;
          _tmpOcupacion = _cursor.getString(_cursorIndexOfOcupacion);
          _tmpCliente.setOcupacion(_tmpOcupacion);
          final String _tmpDireccion;
          _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
          _tmpCliente.setDireccion(_tmpDireccion);
        }  else  {
          _tmpCliente = null;
        }
        _item = new PrestamoConCliente();
        _item.setPrestamo(_tmpPrestamo);
        _item.setCliente(_tmpCliente);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
