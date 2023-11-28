package org.musichop.pe.domain.models;

public class Partitura extends Articulo{
    private String autor;
    private short duracion;

    public Partitura(String nombreArticulo, String autor, short duracion) {
        super(nombreArticulo);
        this.autor = autor;
        this.duracion = duracion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public short getDuracion() {
        return duracion;
    }

    public void setDuracion(short duracion) {
        this.duracion = duracion;
    }

    @Override
    public String showDetails() {
        return "Partitura { " +
                "director='" + autor + '\'' +
                ", duration=" + duracion + "min " +
                '}' + super.showDetails();
    }
}
