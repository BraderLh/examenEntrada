package org.musichop.pe.core.impl;

import org.musichop.pe.core.UsuarioDao;
import org.musichop.pe.domain.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDao {
    private Connection connection;
    private List<Usuario> usuarios;
    private boolean isSuccess;

    private static final String SQL_SELECT_USERS = "SELECT * FROM usuarios";
    private static final String SQL_INSERT_USER = "INSERT INTO usuarios(nombreusuario) VALUES (?)";
    private static final String SQL_DELETE_USER = "DELETE from usuarios WHERE usuario_id = ?";
    private static final String SQL_DELETE_USER_2 = "UPDATE usuarios SET status=? WHERE usuario_id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE usuarios SET username=? WHERE usuario_id = ?";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM usuarios WHERE usuario_id = ?";

    public UsuarioDaoImpl(Connection connection) {
        this.connection = connection;
        this.usuarios = new ArrayList<>();
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
    public boolean deleteUser1(int id) {
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_USER_2);
            System.out.println("Ejecutando query: " + SQL_DELETE_USER_2);
            psmt.setBoolean(1, true);
            psmt.setInt(2, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados por eliminacion 1: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

    @Override
    public boolean deleteUser2(int id) {
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_DELETE_USER);
            System.out.println("Ejecutando query: " + SQL_DELETE_USER);
            psmt.setInt(1, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados por eliminacion 2: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }

    @Override
    public boolean updateUser(String username) {
        int rows = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_UPDATE_USER);
            System.out.println("Ejecutando query: " + SQL_UPDATE_USER);
            psmt.setString(1, username);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados por actualizacion: " + rows);
            isSuccess = true;
        }
        catch (SQLException ex){
            this.isSuccess = false;
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }


    public Usuario getUser(int id) {
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
            }
        } catch (SQLException ex) {
            System.out.println("El usuario no existe");
            ex.printStackTrace(System.out);
        }
        return usuario;
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
        return this.usuarios;
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
}
