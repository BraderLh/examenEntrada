package org.musichop.pe;

import org.musichop.pe.core.ArticuloDao;

import org.musichop.pe.core.UsuarioDao;
import org.musichop.pe.core.impl.ArticuloDaoImpl;
import org.musichop.pe.core.impl.UsuarioDaoImpl;
import org.musichop.pe.domain.models.*;
import org.musichop.pe.services.AlmacenService;
import org.musichop.pe.services.impl.AlmacenServiceImpl;
import org.musichop.pe.utils.DbConnection;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        //DbConnection dbConnection = new DbConnection();
        ArticuloDao articuloDao = new ArticuloDaoImpl(DbConnection.getConnection());
        UsuarioDao usuarioDao = new UsuarioDaoImpl(DbConnection.getConnection());
        System.out.println("articuloDao = " + articuloDao);
        Articulo articulo1 = new Hardware("Mouse", "Logitech", 203, "G", "plástico");
        Articulo articulo2 = new Hardware("Teclado", "Redragon", 552, "Kumara", "plástico");
        Articulo articulo3 = new Hardware("Monitor", "LG", 2423, "MK", "plástico");
        Articulo articulo4 = new Hardware("Tarjeta de video", "Nvidia", 3050, "RTX", "plásticoAluminio");
        Articulo articulo5 = new Hardware("Memoria RAM", "Kingstom", 800, "Hyper X", "plásticoCobre");
        Articulo articulo6 = new Software("Antivirus", "Kapersky", 2023, "Internet Security Premium", TipoLicencia.Digital);
        Articulo articulo7 = new Software("SGBD", "Oracle", 16, "Enterprise edition", TipoLicencia.Digital);
        Articulo articulo8 = new Software("Microsoft 365", "Microsoft", 2021, "Family plan", TipoLicencia.Fisica);
        Articulo articulo9 = new Software("Corel Draw", "Corel Draw", 2023, "Graphics Suit", TipoLicencia.Fisica);
        Articulo articulo10 = new Software("Google Cloud", "Google", 2023, "Google Services Enterprise", TipoLicencia.Digital);
        Articulo articulo11 = new Software("AWS", "Amazon", 2023, "Amazon Web Services", TipoLicencia.Fisica);

        Usuario usuario1 = new Usuario("usuario1");
        Usuario usuario2 = new Usuario("usuario2");
        Usuario usuario3 = new Usuario("usuario3");
        Usuario usuario4 = new Usuario("usuario4");
        Usuario usuario5 = new Usuario("usuario5");
        Usuario usuario6 = new Usuario("usuario6");
        Usuario usuario7 = new Usuario("usuario7");
        Usuario usuario8 = new Usuario("usuario8");
        Usuario usuario9 = new Usuario("usuario9");
        Usuario usuario10 = new Usuario("usuario10");
        Usuario usuario11 = new Usuario("usuario11");

        AlmacenService almacenService = new AlmacenServiceImpl(articuloDao, usuarioDao);
        //if(almacenDao.addArt(articulo10)) System.out.println("Añadido correctamente");
        //System.out.println(articulo10.showDetails());
        //List<Usuario> usuarios = new ArrayList<>();
        //List<Articulo> articulos = new ArrayList<>();
        //articulos = almacenDao.getAllArt();
        // almacenDao.showAllArt(articulos);
        //Articulo articulo = almacenDao.returnArt(4);
        /*Boolean success = almacenDao.loanArt(2, 1);
        if (success) {
            System.out.println("Alquilado exitoso!");
        } else {
            System.out.println("Alquilado falló!");
        }*/
        //System.out.println(articulo.showDetails());
    }
}