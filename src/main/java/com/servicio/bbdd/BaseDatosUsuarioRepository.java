package com.servicio.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import com.modelo.BaseDatosUsuario;

import jakarta.servlet.http.HttpServletRequest;

public class BaseDatosUsuarioRepository {
	private DataSource dataSource;

    public BaseDatosUsuarioRepository(DataSourceConfig dataSourceConfig) {
        this.dataSource = dataSourceConfig.configureDataSource();
    }

    public Optional<BaseDatosUsuario> findUsuarioByCredentials(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);  // Atención: en la práctica, nunca almacenar y comparar contraseñas en texto claro!

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new BaseDatosUsuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("usuario"),
                            rs.getString("contrasena"),
                            rs.getInt("rol")
                    ));
                }
            }
        }  catch (SQLException e) {
            throw new RuntimeException("Error obtener usuario", e);
        }
        return Optional.empty();
    }
    
    public String usuarioRol(HttpServletRequest request) {

	    // Consulta SQL para obtener el rol del usuario
	    String sql = "SELECT rol FROM usuarios WHERE usuario = ? AND contrasena = ?;";
	    String tipoUsuario = null;
	    
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {

	    	String usuario = null;
	    	String contrasena = null;
	    	
	    	
	        stmt.setString(1, usuario);
	        stmt.setString(2, contrasena);

	        // Ejecutar la consulta
	        try (ResultSet resultSet = stmt.executeQuery()) {
	            if (resultSet.next()) {
	                // Se encontró un usuario con el nombre de usuario y contraseña proporcionados
	                int rol = resultSet.getInt("rol");
	                
					// Verificar si el rol es admin
	                if (rol == 2) {
	                	tipoUsuario="admin"; 
	                }else if (rol == 1) {
	                	tipoUsuario="usuario";
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return tipoUsuario;

	}
}