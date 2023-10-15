package com.servicio.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.modelo.Producto;

public class ProductoUsuarioRepository {

	private DataSource dataSource;

	public ProductoUsuarioRepository(DataSourceConfig dataSourceConfig) {
	    this.dataSource = dataSourceConfig.configureDataSource();
	}

	public boolean agregarProductoAlCarrito(Integer id, int productoId) {
		// Verifica si el usuario ya tiene el producto en su carrito.
		if (usuarioYaTieneProductoEnCarrito(id, productoId)) {
			// El usuario ya tiene este producto en su carrito, no es necesario agregarlo
			// nuevamente.
			return false;
		}

		// Si el usuario no tiene el producto en su carrito, lo agregamos.
		String sql = "INSERT INTO producto_usuario (id_producto, id_usuario) VALUES (?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			statement.setInt(2, productoId);
			int rowsInserted = statement.executeUpdate();

			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Manejo de errores: Devuelve false en caso de error.
		}
	}

	private boolean usuarioYaTieneProductoEnCarrito(Integer idUsuario, int idProducto) {
		// Verificar si el usuario ya tiene el producto en su carrito.
		String sql = "SELECT id_producto_usuario FROM producto_usuario WHERE id_usuario = ? AND id_producto = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idUsuario);
			statement.setInt(2, idProducto);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next(); // Si existe una fila, el producto ya está en el carrito.
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Manejo de errores: Devuelve false en caso de error.
		}
	}

	public List<Producto> getProductosEnCarrito(int idUsuario) {
		List<Producto> productosEnCarrito = new ArrayList<>();

		String sql = "select p.nombre, p.descripcion from producto p  "
				+ "inner join producto_usuario pu ON pu.id_producto=p.id inner join usuarios u on pu.id_usuario = u.id "
				+ "where u.id = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Producto producto = new Producto();
					producto.setNombre(resultSet.getString("nombre"));
					producto.setDescripcion(resultSet.getString("descripcion"));
					// Añadir más propiedades según tu modelo de datos

					productosEnCarrito.add(producto);
				}
			}
		} catch (SQLException e) {
			// Manejar errores
			e.printStackTrace();
		}

		return productosEnCarrito;
	}

	public boolean eliminarProductoDelCarrito(Integer idUsuario, int idProducto) {
		// Lógica para eliminar un producto del carrito
		String sql = "DELETE FROM producto_usuario WHERE id_usuario = ? AND id_producto = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idUsuario);
			statement.setInt(2, idProducto);
			int rowsDeleted = statement.executeUpdate();

			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Manejo de errores: Devuelve false en caso de error.
		}
	}

	public boolean vaciarCarrito(int idUsuario) {
		// Lógica para vaciar el carrito de un usuario
		String sql = "DELETE FROM producto_usuario WHERE id_usuario = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idUsuario);
			int rowsDeleted = statement.executeUpdate();

			return rowsDeleted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Manejo de errores: Devuelve false en caso de error.
		}
	}

	public int getTotalProductosEnCarrito(int idUsuario) {
		// Lógica para obtener el total de productos en el carrito de un usuario
		String sql = "SELECT COUNT(*) as total FROM producto_usuario WHERE id_usuario = ?";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, idUsuario);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt("total");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0; // Manejo de errores: Devuelve 0 en caso de error.
	}

}
