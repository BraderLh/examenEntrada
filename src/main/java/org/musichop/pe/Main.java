package org.musichop.pe;

import org.musichop.pe.core.AlmacenDao;
import org.musichop.pe.core.impl.AlmacenDaoImpl;
import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Instrumento;
import org.musichop.pe.domain.models.Partitura;
import org.musichop.pe.domain.models.Usuario;
import org.musichop.pe.utils.DbConnection;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        AlmacenDao almacenDao = new AlmacenDaoImpl("ShopiMusic", dbConnection.getConnection());
        //Articulo articulo1 = new Partitura("Be yourself", "Audioslave", (short) 4);
        //Articulo articulo2 = new Partitura("En el 2000", "Natalia Lafourcade", (short) 3);
        //Articulo articulo3 = new Partitura("En el 2000", "Natalia Lafourcade", (short) 3);
        //Articulo articulo4 = new Partitura("Camisa Negra", "Juanes", (short) 3);
        //Articulo articulo5 = new Partitura("Smooth Criminal", "Michael Jackson", (short) 4);
        //Articulo articulo6 = new Instrumento("Guitarra electroacústica", "Gibson J300");
        //Articulo articulo7 = new Instrumento("Batería", "Pearl");
        //Articulo articulo8 = new Instrumento("Piano", "Yamaha 200");
        //Articulo articulo9 = new Instrumento("Micrófono", "Sure 3IT200");
        //Articulo articulo10 = new Instrumento("Bajo", "Ibanez 33000");
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