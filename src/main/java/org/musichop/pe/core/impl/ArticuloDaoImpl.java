package org.musichop.pe.core.impl;

import org.musichop.pe.core.ArticuloDao;
import org.musichop.pe.core.UsuarioDao;
import org.musichop.pe.domain.models.Articulo;
import org.musichop.pe.domain.models.Hardware;
import org.musichop.pe.domain.models.Software;
import org.musichop.pe.domain.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDaoImpl implements ArticuloDao {
    private Connection connection;
    private final List<Articulo> articulos;
    private UsuarioDao usuarioDao;
    private boolean isSuccess;

    private static final String SQL_SELECT_ARTICULOS = "SELECT * FROM articulos";
    private static final String SQL_INSERT = "INSERT INTO articulos(nombreart, marca, versionart, modelo, isloaned, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_2 = "INSERT INTO hardware(articulo_id, material) VALUES (?, ?)";
    private static final String SQL_INSERT_3 = "INSERT INTO software(tipolic, articulo_id) VALUES (?, ?)";
    private static final String SQL_DELETE_ARTICULO_1 = "UPDATE articulos SET status=? WHERE articulo_id = ?";
    private static final String SQL_DELETE_ARTICULO_2 = "DELETE from articulos WHERE articulo_id = ?";
    private static final String SQL_GET_ARTICLE_BY_ID = "SELECT * FROM articulos WHERE articulo_id = ?";
    private static final String SQL_LOAN_ARTICULO_USUARIO = "UPDATE articulos SET isloaned=?, usuario_id=? WHERE articulo_id = ?";

    public ArticuloDaoImpl(Connection connection) {
        this.connection = connection;
        this.articulos = new ArrayList<>();
    }

    @Override
    public boolean addArt(Articulo articulo) {
        assert articulo!=null;
        int rows, insertedId = 0;
        try{
            PreparedStatement psmt = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, articulo.getNombreArticulo());
            psmt.setString(2, articulo.getMarca());
            psmt.setInt(3, articulo.getVersion());
            psmt.setString(4, articulo.getModelo());
            psmt.setBoolean(5, false);
            psmt.setBoolean(6, true);
            System.out.println("Ejecutando query: " + SQL_INSERT);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            ResultSet rs = psmt.getGeneratedKeys();
            if(rs.next()){
                insertedId = rs.getInt(1);
            }
            if ( articulo instanceof Hardware) {
                try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_2)) {
                    stmt.setInt(1, insertedId);
                    stmt.setString(2, ((Hardware) articulo).getMaterial());
                    stmt.executeUpdate();
                }
            }
            if ( articulo instanceof Software) {
                try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT_3)) {
                    stmt.setString(1, ((Software) articulo).getTipoLicencia().toString());
                    stmt.setInt(2, insertedId);
                    stmt.executeUpdate();
                }
            }
            this.isSuccess = true;
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
            psmt.setBoolean(1, false);
            psmt.setInt(2, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados por eliminacion logica: " + rows);
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
            psmt.setInt(1, id);
            rows = psmt.executeUpdate();
            System.out.println("Registros afectados por eliminacion permanente: " + rows);
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
    public List<Articulo> getAllArt() {
        ResultSet rs = null;
        Articulo articulo = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_SELECT_ARTICULOS);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int articuloId = rs.getInt("articulo_id");
                String nombreArt = rs.getString("nombreart");
                String marca = rs.getString("marca");
                int version = rs.getInt("versionart");
                String modelo = rs.getString("modelo");
                Boolean isLoaned = rs.getBoolean("isloaned");
                Boolean isDeleted = rs.getBoolean("status");
                int usuarioId = rs.getInt("usuario_id");

                articulo = new Articulo(nombreArt, marca, version, modelo);
                articulo.setArticuloId(articuloId);
                articulo.setLoaned(isLoaned);
                articulo.setStatus(isDeleted);
                articulo.setUsuarioId(usuarioId);
                this.articulos.add(articulo);
            }
        }catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return this.articulos;
    }

    public Articulo getArtById(int id) {
        ResultSet rs;
        Articulo articulo = null;
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_GET_ARTICLE_BY_ID);
            System.out.println("Ejecutando query:" + SQL_GET_ARTICLE_BY_ID);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int artId = rs.getInt("articulo_id");
                String nombreArt = rs.getString("nombreart");
                String marca = rs.getString("marca");
                int version = rs.getInt("versionart");
                String modelo = rs.getString("modelo");
                Boolean isLoaned = rs.getBoolean("isloaned");
                Boolean isDeleted = rs.getBoolean("status");
                int usuarioId = rs.getInt("usuario_id");

                articulo = new Articulo(nombreArt, marca, version, modelo);
                articulo.setArticuloId(artId);
                articulo.setLoaned(isLoaned);
                articulo.setStatus(isDeleted);
                articulo.setUsuarioId(usuarioId);
            }
            System.out.println("Saliendo del query: " + SQL_GET_ARTICLE_BY_ID);
        } catch (SQLException ex) {
            System.out.println("El articulo no existe");
            ex.printStackTrace(System.out);
        }
        return articulo;
    }

    @Override
    public boolean loanArt(int artId, int userId) {
        Articulo articulo = getArtById(artId);
        Usuario usuario = usuarioDao.getUser(userId);

        try {
            if (articulo != null) {
                if (usuario != null) {
                    PreparedStatement psmt = connection.prepareStatement(SQL_LOAN_ARTICULO_USUARIO);
                    System.out.println("Ejecutando query:" + SQL_LOAN_ARTICULO_USUARIO);
                    psmt.setBoolean(1, true);
                    psmt.setInt(2, usuario.getUsuarioId());
                    psmt.setInt(3, articulo.getArticuloId());
                    System.out.println("Articulo alquilado con el id:" + articulo.getArticuloId() + " al usuario con el id: " + usuario.getUsuarioId());
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

    @Override
    public boolean returnArt(int artId) {
        Articulo articulo = getArtById(artId);
        //Usuario usuario = usuarioDao.getUser(userId);
        try {
            if (articulo != null)
            {
                PreparedStatement psmt = connection.prepareStatement(SQL_LOAN_ARTICULO_USUARIO);
                System.out.println("Ejecutando query:" + SQL_LOAN_ARTICULO_USUARIO);
                psmt.setBoolean(1, false);
                psmt.setNull(2, Types.INTEGER);
                psmt.setInt(3, articulo.getArticuloId());
                System.out.println("Articulo devuelto con el id:" + articulo.getArticuloId());
                isSuccess = true;
            } else {
                System.out.println("Error: El articulo no existe");
                isSuccess = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return isSuccess;
    }
}
