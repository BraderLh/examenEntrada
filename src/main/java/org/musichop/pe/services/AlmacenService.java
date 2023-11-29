package org.musichop.pe.services;

import org.musichop.pe.core.ArticuloDao;
import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Usuario;

public interface AlmacenService {
    public void addArt(Articulo articulo);
    public void deleteArt(int artId);
    public void loanArt(int artId, int userId);
    public void returnArt(int artId);
    public void showAllArt();

    public void addUser(Usuario usuario);
    public void deleteUser(int userId);
    public void showAllUsers();
}
