package com.example.jdaviran.sqlitedatatable.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jdaviran.sqlitedatatable.Producto;
import com.example.jdaviran.sqlitedatatable.ProductoDetalle;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "lucky.db";
    private static final int DATABASE_VERSION = 1;
    //COLUMNAS DE LA TABLA FACTURA
    public static final String TABLE_NAME = "canje";
    public static final String columnId = "pro_id";
    public static final String columnFecha = "pro_fecha";
    public static final String columnNombreCliente = "pro_cliente";
    public static final String columnDni = "pro_dni";
    public static final String columnNroboleta = "pro_boleta";
    public static final String columnNombreMonto = "pro_monto";

    //COLUMNAS DE LA TABLA PRODUCTO DETALLE
    public static final String TABLE_NAME_DETALLE = "canje_detalle";
    public static final String columnIdDet = "det_id";
    public static final String columnIdCanje = "det_idcanje";
    public static final String columnIdProducto = "det_idproducto";
    public static final String columnEstado = "det_estado";
    public static final String columnCantidad = "det_cantidad";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        "(" + columnId + " INTEGER PRIMARY KEY autoincrement, " +
                        columnFecha + " CHAR(19),"+
                        columnNombreCliente + " VARCHAR(250),"+
                        columnDni + " CHAR(11),"+
                        columnNroboleta + " INTEGER,"+
                        columnNombreMonto + " Double(7,2)"+
                        ")"
        );

        db.execSQL(
                "CREATE TABLE " + TABLE_NAME_DETALLE +
                        "(" + columnIdDet + " INTEGER PRIMARY KEY autoincrement, " +
                        columnIdCanje + " INTEGER,"+
                        columnIdProducto + " INTEGER,"+
                        columnEstado + " INTEGER, " +
                        columnCantidad + " INTEGER " +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DETALLE);
        onCreate(db);
    }

    public boolean insCanje(Producto p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnFecha, p.getFecha());
        contentValues.put(columnNombreCliente, p.getNombre());
        contentValues.put(columnDni, p.getDni());
        contentValues.put(columnNroboleta, p.getBoleta());
        contentValues.put(columnNombreMonto, p.getMonto());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean insCanjeDetalle(ProductoDetalle p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnIdCanje,p.getIdcanje());
        contentValues.put(columnIdProducto, p.getIdproducto());
        contentValues.put(columnEstado, p.getEstado() == true ? 1:0);
        contentValues.put(columnCantidad,p.getCantidad());
        db.insert(TABLE_NAME_DETALLE, null, contentValues);
        return true;
    }

    public int getLastTransaction(){
        SQLiteDatabase db = this.getReadableDatabase();
        int lastId = 0;
        Cursor res = db.rawQuery("SELECT max(pro_id) from canje",null);
        if (res != null && res.moveToFirst()) {
            lastId = res.getInt(0);
        }
        return lastId;
    }

    }
