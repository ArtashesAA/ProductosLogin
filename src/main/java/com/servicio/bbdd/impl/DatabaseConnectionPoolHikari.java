package com.servicio.bbdd.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servicio.ConfigLoader;
import com.servicio.bbdd.DataSourceConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionPoolHikari implements DataSourceConfig {
	public DataSource configureDataSource() {
		final Logger logger = LoggerFactory.getLogger(DatabaseConnectionPoolHikari.class);
		HikariConfig config = new HikariConfig();
		ConfigLoader configLoader = ConfigLoader.getInstance();
	
		config.setJdbcUrl("jdbc:mysql://localhost:3306/productos");
		config.setUsername("root");
		config.setPassword("root");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		/**
		 * cachePrepStmts Determina si el motor de la base de datos debe almacenar en
		 * caché las sentencias PreparedStatement que se utilizan.
		 */
		// Valores:
		// - true: Las sentencias PreparedStatement y CallableStatement serán
		// almacenadas en caché y reutilizadas para mejorar la eficiencia y el
		// rendimiento.
		// - false: Las sentencias no se almacenarán en caché.

		config.addDataSourceProperty("cachePrepStmts", "true");

		/**
		 * prepStmtCacheSize : Define el número máximo de sentencias preparadas que el
		 * driver de MySQL almacenará en caché por cada conexión.
		 */
		//Un número entero que representa la cantidad máxima de sentencias preparadas para cachear
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		/**
		 * Establece el límite máximo en bytes para la longitud de las sentencias SQL
		 * preparadas que serán cacheadas
		 */
		//Un número entero que representa el límite en bytes.
		// En el ejemplo, es 2048 bytes, o 2KB.
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

	    try {
	        return new HikariDataSource(config);
	    } catch (Exception e) {
	        logger.error("Error creando HikariDataSource: ", e);
	        throw new RuntimeException("No se pudo inicializar HikariDataSource", e);
	    }
	}

}
