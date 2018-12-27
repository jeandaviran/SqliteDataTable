package com.example.jdaviran.sqlitedatatable;

import java.io.Serializable;

public class ProductoDetalle implements Serializable {
    Boolean estado;
    int cantidad;
    int idproducto;
    int idcanje;

    public ProductoDetalle() {
    }

    public ProductoDetalle(int idcanje,int idproducto, Boolean estado, int cantidad) {
        this.idcanje = idcanje;
        this.idproducto = idproducto;
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public int getIdcanje() {
        return idcanje;
    }

    public void setIdcanje(int idcanje) {
        this.idcanje = idcanje;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }
}
