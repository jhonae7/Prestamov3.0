package com.example.prestamo.dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import android.support.v4.util.ArrayMap;
import com.example.prestamo.Cliente;
import com.example.prestamo.ClienteConPrestamo;
import com.example.prestamo.Prestamo;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class ClientesDao_Impl implements ClientesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCliente;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCliente;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfCliente;

  public ClientesDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCliente = new EntityInsertionAdapter<Cliente>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ClientesTB`(`id_Cliente`,`nombre`,`apellido`,`telefono`,`sexo`,`cedula`,`ocupacion`,`direccion`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cliente value) {
        stmt.bindLong(1, value.getId_Cliente());
        if (value.getNombre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre());
        }
        if (value.getApellido() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getApellido());
        }
        if (value.getTelefono() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTelefono());
        }
        if (value.getSexo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSexo());
        }
        if (value.getCedula() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCedula());
        }
        if (value.getOcupacion() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getOcupacion());
        }
        if (value.getDireccion() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDireccion());
        }
      }
    };
    this.__deletionAdapterOfCliente = new EntityDeletionOrUpdateAdapter<Cliente>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ClientesTB` WHERE `id_Cliente` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cliente value) {
        stmt.bindLong(1, value.getId_Cliente());
      }
    };
    this.__updateAdapterOfCliente = new EntityDeletionOrUpdateAdapter<Cliente>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ClientesTB` SET `id_Cliente` = ?,`nombre` = ?,`apellido` = ?,`telefono` = ?,`sexo` = ?,`cedula` = ?,`ocupacion` = ?,`direccion` = ? WHERE `id_Cliente` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cliente value) {
        stmt.bindLong(1, value.getId_Cliente());
        if (value.getNombre() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNombre());
        }
        if (value.getApellido() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getApellido());
        }
        if (value.getTelefono() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTelefono());
        }
        if (value.getSexo() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSexo());
        }
        if (value.getCedula() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCedula());
        }
        if (value.getOcupacion() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getOcupacion());
        }
        if (value.getDireccion() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDireccion());
        }
        stmt.bindLong(9, value.getId_Cliente());
      }
    };
  }

  @Override
  public Long insertar(Cliente cliente) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfCliente.insertAndReturnId(cliente);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void borrar(Cliente cliente) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCliente.handle(cliente);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void actualizar(Cliente cliente) {
    __db.beginTransaction();
    try {
      __updateAdapterOfCliente.handle(cliente);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ClienteConPrestamo> ObtenerTodosClientes() {
    final String _sql = "select * from clientestb";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final ArrayMap<Long, ArrayList<Prestamo>> _collectionPrestamoList = new ArrayMap<Long, ArrayList<Prestamo>>();
      final int _cursorIndexOfIdCliente = _cursor.getColumnIndexOrThrow("id_Cliente");
      final int _cursorIndexOfNombre = _cursor.getColumnIndexOrThrow("nombre");
      final int _cursorIndexOfApellido = _cursor.getColumnIndexOrThrow("apellido");
      final int _cursorIndexOfTelefono = _cursor.getColumnIndexOrThrow("telefono");
      final int _cursorIndexOfSexo = _cursor.getColumnIndexOrThrow("sexo");
      final int _cursorIndexOfCedula = _cursor.getColumnIndexOrThrow("cedula");
      final int _cursorIndexOfOcupacion = _cursor.getColumnIndexOrThrow("ocupacion");
      final int _cursorIndexOfDireccion = _cursor.getColumnIndexOrThrow("direccion");
      final List<ClienteConPrestamo> _result = new ArrayList<ClienteConPrestamo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ClienteConPrestamo _item;
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
        _item = new ClienteConPrestamo();
        if (!_cursor.isNull(_cursorIndexOfIdCliente)) {
          final Long _tmpKey = _cursor.getLong(_cursorIndexOfIdCliente);
          ArrayList<Prestamo> _tmpCollection = _collectionPrestamoList.get(_tmpKey);
          if(_tmpCollection == null) {
            _tmpCollection = new ArrayList<Prestamo>();
            _collectionPrestamoList.put(_tmpKey, _tmpCollection);
          }
          _item.setPrestamoList(_tmpCollection);
        }
        _item.setCliente(_tmpCliente);
        _result.add(_item);
      }
      __fetchRelationshipPrestamosTbAscomExamplePrestamoPrestamo(_collectionPrestamoList);
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Cliente ObtenerCliente(int id) {
    final String _sql = "select * from clientestb where id_Cliente=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdCliente = _cursor.getColumnIndexOrThrow("id_Cliente");
      final int _cursorIndexOfNombre = _cursor.getColumnIndexOrThrow("nombre");
      final int _cursorIndexOfApellido = _cursor.getColumnIndexOrThrow("apellido");
      final int _cursorIndexOfTelefono = _cursor.getColumnIndexOrThrow("telefono");
      final int _cursorIndexOfSexo = _cursor.getColumnIndexOrThrow("sexo");
      final int _cursorIndexOfCedula = _cursor.getColumnIndexOrThrow("cedula");
      final int _cursorIndexOfOcupacion = _cursor.getColumnIndexOrThrow("ocupacion");
      final int _cursorIndexOfDireccion = _cursor.getColumnIndexOrThrow("direccion");
      final Cliente _result;
      if(_cursor.moveToFirst()) {
        _result = new Cliente();
        final int _tmpId_Cliente;
        _tmpId_Cliente = _cursor.getInt(_cursorIndexOfIdCliente);
        _result.setId_Cliente(_tmpId_Cliente);
        final String _tmpNombre;
        _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
        _result.setNombre(_tmpNombre);
        final String _tmpApellido;
        _tmpApellido = _cursor.getString(_cursorIndexOfApellido);
        _result.setApellido(_tmpApellido);
        final String _tmpTelefono;
        _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
        _result.setTelefono(_tmpTelefono);
        final String _tmpSexo;
        _tmpSexo = _cursor.getString(_cursorIndexOfSexo);
        _result.setSexo(_tmpSexo);
        final String _tmpCedula;
        _tmpCedula = _cursor.getString(_cursorIndexOfCedula);
        _result.setCedula(_tmpCedula);
        final String _tmpOcupacion;
        _tmpOcupacion = _cursor.getString(_cursorIndexOfOcupacion);
        _result.setOcupacion(_tmpOcupacion);
        final String _tmpDireccion;
        _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
        _result.setDireccion(_tmpDireccion);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  private void __fetchRelationshipPrestamosTbAscomExamplePrestamoPrestamo(final ArrayMap<Long, ArrayList<Prestamo>> _map) {
    final Set<Long> __mapKeySet = _map.keySet();
    if (__mapKeySet.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      ArrayMap<Long, ArrayList<Prestamo>> _tmpInnerMap = new ArrayMap<Long, ArrayList<Prestamo>>(android.arch.persistence.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _mapIndex = 0;
      int _tmpIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipPrestamosTbAscomExamplePrestamoPrestamo(_tmpInnerMap);
          _tmpInnerMap = new ArrayMap<Long, ArrayList<Prestamo>>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipPrestamosTbAscomExamplePrestamoPrestamo(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`ID_CLIENTE`,`cliente`,`monto_credito`,`interes`,`plazo`,`fecha_actual`,`fecha_final`,`monto_pagar`,`monto_cuota` FROM `PrestamosTb` WHERE `ID_CLIENTE` IN (");
    final int _inputSize = __mapKeySet.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : __mapKeySet) {
      if (_item == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_stmt);
    try {
      final int _itemKeyIndex = _cursor.getColumnIndex("ID_CLIENTE");
      if (_itemKeyIndex == -1) {
        return;
      }
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
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final Long _tmpKey = _cursor.getLong(_itemKeyIndex);
          ArrayList<Prestamo> _tmpCollection = _map.get(_tmpKey);
          if (_tmpCollection != null) {
            final Prestamo _item_1;
            _item_1 = new Prestamo();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item_1.setId(_tmpId);
            _item_1.ID_CLIENTE = _cursor.getInt(_cursorIndexOfIDCLIENTE);
            final String _tmpCliente;
            _tmpCliente = _cursor.getString(_cursorIndexOfCliente);
            _item_1.setCliente(_tmpCliente);
            final String _tmpMonto_credito;
            _tmpMonto_credito = _cursor.getString(_cursorIndexOfMontoCredito);
            _item_1.setMonto_credito(_tmpMonto_credito);
            final String _tmpInteres;
            _tmpInteres = _cursor.getString(_cursorIndexOfInteres);
            _item_1.setInteres(_tmpInteres);
            final String _tmpPlazo;
            _tmpPlazo = _cursor.getString(_cursorIndexOfPlazo);
            _item_1.setPlazo(_tmpPlazo);
            final String _tmpFecha_actual;
            _tmpFecha_actual = _cursor.getString(_cursorIndexOfFechaActual);
            _item_1.setFecha_actual(_tmpFecha_actual);
            final String _tmpFecha_final;
            _tmpFecha_final = _cursor.getString(_cursorIndexOfFechaFinal);
            _item_1.setFecha_final(_tmpFecha_final);
            final String _tmpMonto_pagar;
            _tmpMonto_pagar = _cursor.getString(_cursorIndexOfMontoPagar);
            _item_1.setMonto_pagar(_tmpMonto_pagar);
            final String _tmpMonto_cuota;
            _tmpMonto_cuota = _cursor.getString(_cursorIndexOfMontoCuota);
            _item_1.setMonto_cuota(_tmpMonto_cuota);
            _tmpCollection.add(_item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
