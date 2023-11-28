package org.musichop.pe.core.impl;

import org.musichop.pe.core.AlmacenDao;
import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlmacenDaoImpl implements AlmacenDao {
    private Connection connection;
    private final String name;
    private List<Articulo> articulos;
    private List<Usuario> usuarios;
    private boolean isSuccess;

    private static final String SQL_SELECT_USERS = "SELECT * FROM usuarios";
    private static final String SQL_SELECT_ARTICULOS = "SELECT * FROM articulos";
    private static final String SQL_INSERT = "INSERT INTO articulos(nombreart, isloaned, isdeleted) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_2 = "INSERT INTO partituras(autor, duracion) VALUES (?, ?, ?)";
    private static final String SQL_INSERT_3 = "INSERT INTO instrumentos(nombreinst) VALUES (?)";
    private static final String SQL_INSERT_USER = "INSERT INTO usuarios(nombreusuario) VALUES (?)";
    private static final String SQL_DELETE_ARTICULO_1 = "UPDATE articulos SET isdeleted=? WHERE articulo_id = ?";
    private static final String SQL_DELETE_ARTICULO_2 = "DELETE from articulos WHERE articulo_id = ?";
    private static final String SQL_GET_ARTICLE_BY_ID = "SELECT * FROM articulos WHERE articulo_id = ?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM usuarios WHERE usuario_id = ?";

    private static final String SQL_LOAN_ARTICULO_USUARIO = "UPDATE articulos SET isloaned=?, usuario_id=? WHERE articulo_id = ?";
    public AlmacenDaoImpl(String name, Connection connection) {
        this.name = name;
        this.connection = connection;
        this.articulos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    @Override
    public boolean addArt(Articulo articulo) {
        assert articulo!=null;
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_INSERT);
            String nombre = articulo.getNombreArticulo();
            boolean isLoaned = articulo.isLoaned();
            boolean isdeleted = articulo.isStatus();
            Date fechacreacion = articulo.getFechaCreacion();
            psmt.setString(1, nombre);
            psmt.setBoolean(2, isLoaned);
            psmt.setBoolean(3, isdeleted);
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            this.isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

    @Override
    public boolean addUser(Usuario usuario) {
        assert usuario!=null;
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_INSERT_USER);
            String nombre = usuario.getNombreUsuario();
            psmt.setString(1, nombre);
            System.out.println("Ejecutando query: " + SQL_INSERT_USER);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteArtTemp(int id) {
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_ARTICULO_1);
            System.out.println("Ejecutando query: " + SQL_DELETE_ARTICULO_1);
            Boolean isDeleted = true;
            psmt.setBoolean(1, isDeleted);
            psmt.setInt(2, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }
    @Override
    public boolean deleteArtPerm(int id) {
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_ARTICULO_2);
            System.out.println("Ejecutando query: " + SQL_DELETE_ARTICULO_2);
            psmt.setInt(2, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }
    @Override
    public void showAllArt(List<Articulo> articulos) {
        System.out.println("***************** Showing rent articles *****************");
        if (articulos.isEmpty()) {
            System.out.println("Fail: The article item list is empty");
        } else {
            for (Articulo articulo : articulos) {
                System.out.println(articulo.showDetails());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    @Override
    public void showAllUsers(List<Usuario> usuarios) {
        System.out.println("***************** Showing users *****************");
        if (usuarios.isEmpty()) {
            System.out.println("Fail: The customer list is empty");
        } else {
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.showUserDetails());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    @Override
    public List<Articulo> getAllArt() {
        ResultSet rs = null;
        Articulo articulo = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_SELECT_ARTICULOS);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int articuloId = rs.getInt("articulo_id");
                String nombreArt = rs.getString("nombreart");
                Boolean isLoaned = rs.getBoolean("isloaned");
                Boolean isDeleted = rs.getBoolean("isdeleted");
                Date fecha = rs.getDate("creationdate");
                articulo = new Articulo(nombreArt);
                articulo.setArticuloId(articuloId);
                articulo.setNombreArticulo(nombreArt);
                articulo.setLoaned(isLoaned);
                articulo.setStatus(isDeleted);
                articulo.setFechaCreacion(fecha);
                this.articulos.add(articulo);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return articulos;
    }

    @Override
    public List<Usuario> getAllUsers() {
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_SELECT_USERS);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("usuario_id");
                String username = rs.getString("nombreusuario");
                usuario = new Usuario(username);
                usuario.setNombreUsuario(username);
                usuario.setUsuarioId(id_usuario);
                this.usuarios.add(usuario);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return usuarios;
    }

    public Articulo returnArt(int id) {
        ResultSet rs = null;
        Articulo articulo = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_GET_ARTICLE_BY_ID);
            System.out.println("Ejecutando query:" + SQL_GET_ARTICLE_BY_ID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int articuloId = rs.getInt("articulo_id");
                String nombreArt = rs.getString("nombreart");
                Boolean isLoaned = rs.getBoolean("isloaned");
                Boolean isDeleted = rs.getBoolean("isdeleted");
                Date fecha = rs.getDate("creationdate");
                articulo = new Articulo(nombreArt);
                articulo.setArticuloId(articuloId);
                articulo.setNombreArticulo(nombreArt);
                articulo.setLoaned(isLoaned);
                articulo.setStatus(isDeleted);
                articulo.setFechaCreacion(fecha);
            }
        } catch (SQLException ex) {
            System.out.println("El articulo no existe");
            ex.printStackTrace(System.out);
        }
        return articulo;
    }

    public Usuario returnUser(int id) {
        ResultSet rs = null;
        Usuario usuario = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_GET_USER_BY_ID);
            System.out.println("Ejecutando query:" + SQL_GET_USER_BY_ID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id_usuario = rs.getInt("usuario_id");
                String username = rs.getString("nombreusuario");
                usuario = new Usuario(username);
                usuario.setNombreUsuario(username);
                usuario.setUsuarioId(id_usuario);
                this.usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            System.out.println("El articulo no existe");
            ex.printStackTrace(System.out);
        }
        return usuario;
    }
    @Override
    public boolean loanArt(int artId, int userId) {
        Articulo articulo = returnArt(artId);
        Usuario usuario = returnUser(userId);
        int rows = 0;
        try {
            if (articulo != null) {
                if (usuario != null) {
                    PreparedStatement psmt = connection.prepareStatement(SQL_LOAN_ARTICULO_USUARIO);
                    System.out.println("Ejecutando query:" + SQL_LOAN_ARTICULO_USUARIO);
                    psmt.setBoolean(1, true);
                    psmt.setInt(2, userId);
                    psmt.setInt(3, artId);
                    rows = psmt.executeUpdate();
                    System.out.println("Articulo alquilado:" + rows);
                    isSuccess = true;
                } else {
                    System.out.println("Error: Customer not exists");
                    isSuccess = false;
                }
            } else {
                System.out.println("Error: Item not exists");
                isSuccess = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }
}
