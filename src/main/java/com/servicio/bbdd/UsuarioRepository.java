package com.servicio.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.modelo.BaseDatosUsuario;

public class UsuarioRepository {
    private final DataSource dataSource;

    public UsuarioRepository(DataSourceConfig dataSourceConfig) {
        this.dataSource = dataSourceConfig.configureDataSource();

    }

    public boolean createUsuario(BaseDatosUsuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido, usuario, contrasena) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getUsuario());
            statement.setString(4, usuario.getContrasena());
            int rowsInserted = statement.executeUpdate();
            
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error crear usuario", e);
        }
    }

    public List<BaseDatosUsuario> getAllUsuario() {
        List<BaseDatosUsuario> result = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
            	BaseDatosUsuario usuario = new BaseDatosUsuario(
                		rs.getInt("id"),
                		rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("usuario"),
                        rs.getString("contrasena")
                );
            
                result.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obtener s", e);
        }
        return result;
    }

    public BaseDatosUsuario readUsuario(Integer id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new BaseDatosUsuario(
                    		rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("usuario"),
                            rs.getString("contrasena")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leer ", e);
        }
    }
    public boolean updateUsuario(BaseDatosUsuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, usuario = ?, contrasena = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

        	statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getUsuario());
            statement.setString(4, usuario.getContrasena());
            statement.setInt(4, usuario.getId());
            
            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error modificar usuario", e);
        }
    }

    public boolean deleteUsuario(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error borrar usuario", e);
        }
    }
}