package org.musichop.pe.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DbConnection {
    private Connection connection;
    public Connection getConnection(){
        try {
            Properties props = new Properties();
            InputStream inputStream = DbConnection.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(inputStream);
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion exitosa");
            return connection;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void close(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
