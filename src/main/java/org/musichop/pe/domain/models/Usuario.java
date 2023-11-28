package org.musichop.pe.domain.models;

public class Usuario {
    private int usuarioId;
    private String nombreUsuario;

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String showUserDetails() {
        return "Usuario { " +
                "id_usuario=" + usuarioId +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }
}
