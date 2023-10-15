package com.servicio.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.modelo.Producto;

public class ProductoRepository {
    private final DataSource dataSource;

    public ProductoRepository(DataSourceConfig dataSourceConfig) {
        this.dataSource = dataSourceConfig.configureDataSource();

    }

    public boolean createProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, peso, stock) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPeso());
            statement.setInt(4, producto.getStock());
            int rowsInserted = statement.executeUpdate();
            
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error crear producto", e);
        }
    }

    public List<Producto> getAllProductos() {
        List<Producto> result = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                		rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("peso"),
                        rs.getInt("stock")
                );
            
                result.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error obtener productos", e);
        }
        return result;
    }

    public Producto readProducto(Integer id) {
        String sql = "SELECT * FROM producto WHERE id = ?";
        
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("peso"),
                            rs.getInt("stock")
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error leer producto", e);
        }
    }
    public boolean updateProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, peso = ?, stock = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

        	statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setDouble(3, producto.getPeso());
            statement.setInt(4, producto.getStock());
            statement.setInt(5, producto.getId());
            
            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error modificar producto", e);
        }
    }

    public boolean deleteProducto(Integer id) {
        String sql = "DELETE FROM producto WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error borrar producto", e);
        }
    }

}