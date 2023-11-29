package org.musichop.pe.core;

import org.musichop.pe.domain.models.Articulo;

import java.util.List;

public interface ArticuloDao {
    public boolean addArt(Articulo articulo);
    public boolean deleteArtTemp(int id);
    public boolean deleteArtPerm(int id);
    public void showAllArt(List<Articulo> articulos);
    public List<Articulo> getAllArt();
    public Articulo getArtById(int id);
    public boolean loanArt(int artId, int userId);
    public boolean returnArt(int artId);
}
