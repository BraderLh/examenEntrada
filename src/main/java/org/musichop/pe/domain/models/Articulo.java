package org.musichop.pe.domain.models;

import java.util.Date;

public class Articulo {
    protected int articuloId;
    protected String nombreArticulo;
    protected boolean isLoaned;
    protected boolean status;
    protected Date fechaCreacion;
    public Articulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
        this.isLoaned = false;
        this.status = false;
    }

    public void setArticuloId(int articuloId) {
        this.articuloId = articuloId;
    }

    public int getArticuloId() {
        return articuloId;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void setLoaned(boolean loaned) {
        isLoaned = loaned;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String showDetails() {
        String msmLoaned = this.isLoaned ? "rentado" : "sin rentar";
        String status = this.status ? "eliminado" : "disponible";
        return "Articulo { " +
                "articuloId = " + articuloId +
                ", nombreArt = '" + nombreArticulo + '\'' +
                ", disponibilidad = " + msmLoaned +
                ", estado = " + status +
                ", fecha = " + fechaCreacion + " " +
                '}';
    }
}
