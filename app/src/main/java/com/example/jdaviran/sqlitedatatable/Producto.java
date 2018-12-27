package com.example.jdaviran.sqlitedatatable;

import java.io.Serializable;

public class Producto implements Serializable {

    int id;
    String fecha;
    String nombre;
    String dni;
    int boleta;
    double monto;
    //ESTO ES PARA LA LISTA en el array
    int idproducto;
    String producto;

    public Producto() {
    }

    public Producto(int idproducto, String producto) {
        this.idproducto = idproducto;
        this.producto = producto;
    }

    public Producto(String fecha, String nombre, String dni, int boleta, double monto) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.dni = dni;
        this.boleta = boleta;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getBoleta() {
        return boleta;
    }

    public void setBoleta(int boleta) {
        this.boleta = boleta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
