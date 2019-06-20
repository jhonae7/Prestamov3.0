package com.example.prestamo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.example.prestamo.dao.ClientesDao;
import com.example.prestamo.dao.ClientesDao_Impl;
import com.example.prestamo.dao.PrestamosDao;
import com.example.prestamo.dao.PrestamosDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class DBclass_Impl extends DBclass {
  private volatile ClientesDao _clientesDao;

  private volatile PrestamosDao _prestamosDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ClientesTB` (`id_Cliente` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nombre` TEXT, `apellido` TEXT, `telefono` TEXT, `sexo` TEXT, `cedula` TEXT, `ocupacion` TEXT, `direccion` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PrestamosTb` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ID_CLIENTE` INTEGER NOT NULL, `cliente` TEXT, `monto_credito` TEXT, `interes` TEXT, `plazo` TEXT, `fecha_actual` TEXT, `fecha_final` TEXT, `monto_pagar` TEXT, `monto_cuota` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"db5f388d64b60bdae35374da163235ea\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ClientesTB`");
        _db.execSQL("DROP TABLE IF EXISTS `PrestamosTb`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsClientesTB = new HashMap<String, TableInfo.Column>(8);
        _columnsClientesTB.put("id_Cliente", new TableInfo.Column("id_Cliente", "INTEGER", true, 1));
        _columnsClientesTB.put("nombre", new TableInfo.Column("nombre", "TEXT", false, 0));
        _columnsClientesTB.put("apellido", new TableInfo.Column("apellido", "TEXT", false, 0));
        _columnsClientesTB.put("telefono", new TableInfo.Column("telefono", "TEXT", false, 0));
        _columnsClientesTB.put("sexo", new TableInfo.Column("sexo", "TEXT", false, 0));
        _columnsClientesTB.put("cedula", new TableInfo.Column("cedula", "TEXT", false, 0));
        _columnsClientesTB.put("ocupacion", new TableInfo.Column("ocupacion", "TEXT", false, 0));
        _columnsClientesTB.put("direccion", new TableInfo.Column("direccion", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClientesTB = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClientesTB = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClientesTB = new TableInfo("ClientesTB", _columnsClientesTB, _foreignKeysClientesTB, _indicesClientesTB);
        final TableInfo _existingClientesTB = TableInfo.read(_db, "ClientesTB");
        if (! _infoClientesTB.equals(_existingClientesTB)) {
          throw new IllegalStateException("Migration didn't properly handle ClientesTB(com.example.prestamo.Cliente).\n"
                  + " Expected:\n" + _infoClientesTB + "\n"
                  + " Found:\n" + _existingClientesTB);
        }
        final HashMap<String, TableInfo.Column> _columnsPrestamosTb = new HashMap<String, TableInfo.Column>(10);
        _columnsPrestamosTb.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPrestamosTb.put("ID_CLIENTE", new TableInfo.Column("ID_CLIENTE", "INTEGER", true, 0));
        _columnsPrestamosTb.put("cliente", new TableInfo.Column("cliente", "TEXT", false, 0));
        _columnsPrestamosTb.put("monto_credito", new TableInfo.Column("monto_credito", "TEXT", false, 0));
        _columnsPrestamosTb.put("interes", new TableInfo.Column("interes", "TEXT", false, 0));
        _columnsPrestamosTb.put("plazo", new TableInfo.Column("plazo", "TEXT", false, 0));
        _columnsPrestamosTb.put("fecha_actual", new TableInfo.Column("fecha_actual", "TEXT", false, 0));
        _columnsPrestamosTb.put("fecha_final", new TableInfo.Column("fecha_final", "TEXT", false, 0));
        _columnsPrestamosTb.put("monto_pagar", new TableInfo.Column("monto_pagar", "TEXT", false, 0));
        _columnsPrestamosTb.put("monto_cuota", new TableInfo.Column("monto_cuota", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPrestamosTb = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPrestamosTb = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPrestamosTb = new TableInfo("PrestamosTb", _columnsPrestamosTb, _foreignKeysPrestamosTb, _indicesPrestamosTb);
        final TableInfo _existingPrestamosTb = TableInfo.read(_db, "PrestamosTb");
        if (! _infoPrestamosTb.equals(_existingPrestamosTb)) {
          throw new IllegalStateException("Migration didn't properly handle PrestamosTb(com.example.prestamo.Prestamo).\n"
                  + " Expected:\n" + _infoPrestamosTb + "\n"
                  + " Found:\n" + _existingPrestamosTb);
        }
      }
    }, "db5f388d64b60bdae35374da163235ea", "561a2fb0805a50483b9a347fef979a9a");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "ClientesTB","PrestamosTb");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ClientesTB`");
      _db.execSQL("DELETE FROM `PrestamosTb`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ClientesDao clientesDao() {
    if (_clientesDao != null) {
      return _clientesDao;
    } else {
      synchronized(this) {
        if(_clientesDao == null) {
          _clientesDao = new ClientesDao_Impl(this);
        }
        return _clientesDao;
      }
    }
  }

  @Override
  public PrestamosDao prestamosDao() {
    if (_prestamosDao != null) {
      return _prestamosDao;
    } else {
      synchronized(this) {
        if(_prestamosDao == null) {
          _prestamosDao = new PrestamosDao_Impl(this);
        }
        return _prestamosDao;
      }
    }
  }
}
