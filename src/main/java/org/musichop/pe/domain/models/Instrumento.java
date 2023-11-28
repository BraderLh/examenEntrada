package org.musichop.pe.domain.models;

public class Instrumento extends Articulo{
    private String nombreInst;
    private Usuario usuario;

    public Instrumento(String nombreArticulo, String nombreInst) {
        super(nombreArticulo);
        this.nombreInst = nombreInst;
    }
    public String getNombreInst() {
        return nombreInst;
    }

    public void setNombreInst(String nombreInst) {
        this.nombreInst = nombreInst;
    }

    public Usuario getUsuarioInstrumento() {
        return usuario;
    }

    public void setUsuarioInstrumento(Usuario usuario) {
        try {
            if (usuario!=null) this.usuario = usuario;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String showDetails() {
        return "Instrumento {" +
                "nombre='" + nombreInst + '\'' +
                ", duration=" + usuario.showUserDetails() +
                '}' + super.showDetails();
    }
}
