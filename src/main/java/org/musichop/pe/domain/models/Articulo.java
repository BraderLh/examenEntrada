package org.musichop.pe.domain.models;

import java.util.Date;

public class Articulo {
    protected int articuloId;
    protected String nombreArticulo;
    protected String marca;
    protected int version;
    protected String modelo;
    protected boolean isLoaned;
    protected boolean status;
    protected int usuarioId;
    public Articulo(String nombreArticulo, String marca, int version, String modelo) {
        this.nombreArticulo = nombreArticulo;
        this.marca = marca;
        this.version = version;
        this.modelo = modelo;
        this.isLoaned = false;
        this.status = true;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String model) {
        this.modelo = model;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String showDetails() {
        String msmLoaned = isLoaned ? "rentado" : "sin rentar";
        String msmStatus = status ? "activo" : "eliminado";
        return "Articulo {" +
                "articuloId=" + articuloId +
                ", nombreArticulo='" + nombreArticulo + '\'' +
                ", marca='" + marca + '\'' +
                ", version=" + version +
                ", modelo='" + modelo + '\'' +
                ", estaRentado? =" + msmLoaned +
                ", estado= " + msmStatus +
                ", usuarioId= " + usuarioId +
                '}';
    }
}
