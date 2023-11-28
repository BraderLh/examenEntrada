package org.musichop.pe.core;

import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Usuario;

import java.util.List;

public interface AlmacenDao {
    public boolean addArt(Articulo articulo);
    public boolean addUser(Usuario usuario);
    public boolean deleteArtTemp(int id);
    public boolean deleteArtPerm(int id);
    public void showAllArt(List<Articulo> articulos);
    public void showAllUsers(List<Usuario> usuarios);
    public List<Articulo> getAllArt();
    public List<Usuario> getAllUsers();
    public Articulo returnArt(int id);
    public boolean loanArt(int artId, int userId);
}
