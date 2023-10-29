package com.servicio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ConfigLoader {

	private static ConfigLoader instance;
	private final Properties properties;

	public ConfigLoader() {
		properties = new Properties();
		// Intenta cargar el archivo config.properties del classpath
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			// Si input es null, el archivo no se ha encontrado en el classpath
			if (input == null) {
				System.out.println("ConfigLoader().input_null");
				throw new IOException("Sorry, unable to find config.properties");
			}
			// Carga las propiedades del archivo
			properties.load(input);
		} catch (IOException ex) {
			System.out.println("ConfigLoader() " + ex.toString());
			throw new RuntimeException("Error loading config.properties", ex);
		}
	}

	public static synchronized ConfigLoader getInstance() {
		if (instance == null) {
			instance = new ConfigLoader();
		}
		return instance;
	}

	public static String generateJWT() {
		String privateKeyPath = instance.getPrivateKeyPath();
		KeyFactory keyFactory;
		String privateKeyContent;

		try {
			keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al obtener la fábrica de claves: " + e.toString(), e);
		}

		try {
			privateKeyContent = new String(Files.readAllBytes(Paths.get(privateKeyPath)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al leer la clave privada desde el archivo: " + e.toString(), e);
		}

		privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "")
				.replace("-----END PRIVATE KEY-----", "");

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
				Base64.getDecoder().decode(privateKeyContent));
		PrivateKey privateKey;

		try {
			privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al generar la clave privada: " + e.toString(), e);
		}

		String appId = instance.getClientId();
		Date now = Date.from(Instant.now());
		Date exp = new Date(now.getTime() + 600000); // 10 minutos de validez

		return Jwts.builder()
		    .setIssuedAt(now)
		    .setIssuer(String.valueOf(appId))
		    .setExpiration(exp)
		    .signWith(privateKey, SignatureAlgorithm.RS256)
		    .compact();

	}

	public String getPrivateKeyPath() {
		String path = properties.getProperty("privateKeyPath");
		if (path == null || path.trim().isEmpty()) {
			throw new IllegalStateException("privateKeyPath is missing or empty in config.properties");
		}
		return path;
	}

	public String getJDBC() {
		String jdbc = getProperty("bbdd.jdbc");
		return jdbc;
	}

	public String getUser() {
		String user = getProperty("bbdd.user");
		return user;
	}

	public String getPass() {
		String pass = getProperty("bbdd.pass");
		return pass;
	}

	public String getClientId() {

		String clientId = getProperty("github.id.client");

		return clientId;
	}

	public String getClientSecret() {
		String clientSecret = getProperty("github.app.secret");
		return clientSecret;
	}

	public String getRedirectUri() {
		String uri = getProperty("redirectUri");
		return uri;
	}

	private String getProperty(String key) {
		String value = properties.getProperty(key);
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalStateException(key + " is missing or empty in config.properties");
		}
		return value;
	}
}
