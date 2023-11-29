package org.musichop.pe.services.impl;

import org.musichop.pe.core.ArticuloDao;
import org.musichop.pe.core.UsuarioDao;
import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Usuario;
import org.musichop.pe.services.AlmacenService;

import java.util.ArrayList;
import java.util.List;

public class AlmacenServiceImpl implements AlmacenService {
    ArticuloDao articuloDao;
    UsuarioDao usuarioDao;

    public AlmacenServiceImpl(ArticuloDao articuloDao, UsuarioDao usuarioDao) {
        this.articuloDao = articuloDao;
        this.usuarioDao = usuarioDao;
    }

    @Override
    public void addArt(Articulo articulo) {
        if (articulo!=null) {
            if (articuloDao.addArt(articulo)) {
                System.out.println("Articulo añadido exitosament!");
            }else {
                System.out.println("Ocurrión un error al añadir el artículo");
            }
        }else {
            System.out.println("Error: Artículo vacío");
        }
    }

    @Override
    public void deleteArt(int artId) {
        if (articuloDao.deleteArtTemp(artId)) {
            System.out.println("Articulo eliminado exitosament!");
        }else {
            System.out.println("Ocurrión un error al eliminar el artículo");
        }
    }

    @Override
    public void loanArt(int artId, int userId) {
        if (articuloDao.loanArt(artId, userId)) {
            System.out.println("Préstamo exitoso!");
        }else {
            System.out.println("Ocurrió un fallo en el préstamo del artículo");
        };
    }

    @Override
    public void returnArt(int artId) {
        if (articuloDao.returnArt(artId)) {
            System.out.println("Devolución exitosa!");
        }else {
            System.out.println("Ocurrió un fallo en la devolución del artículo");
        };
    }

    @Override
    public void showAllArt() {
        List<Articulo> articulos;
        articulos = articuloDao.getAllArt();
        articuloDao.showAllArt(articulos);
    }

    @Override
    public void addUser(Usuario usuario) {
        if (usuario!=null) {
            if (usuarioDao.addUser(usuario)) {
                System.out.println("Usuario añadido exitosament!");
            }else {
                System.out.println("Ocurrión un error al añadir el usuario");
            }
        }else {
            System.out.println("Error: Usuario vacío");
        }
    }

    @Override
    public void deleteUser(int userId) {
        if (usuarioDao.deleteUser1(userId)) {
            System.out.println("Usuario eliminado exitosamente!");
        }else {
            System.out.println("Ocurrión un error al eliminar el usuario");
        }
    }

    @Override
    public void showAllUsers() {
        List<Usuario> usuarios;
        usuarios = usuarioDao.getAllUsers();
        usuarioDao.showAllUsers(usuarios);
    }

}
