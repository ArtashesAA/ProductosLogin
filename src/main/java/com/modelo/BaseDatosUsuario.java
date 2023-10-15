package com.modelo;

import java.io.Serializable;

public class BaseDatosUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String usuario;
	private String contrasena;
	private String nombre;
	private String apellido;
	private Integer rol;

	BaseDatosUsuario() {
		super();
	}

	public BaseDatosUsuario(Integer id, String nombre, String apellido, String usuario, String contrasena,
			Integer rol) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = rol;
	}

	public BaseDatosUsuario(Integer id, String nombre, String apellido, String usuario, String contrasena) {
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = 1;
	}
	
	public BaseDatosUsuario(String nombre, String apellido, String usuario, String contrasena) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido = apellido;
		this.rol = 1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRol() {
		return rol;
	}

	public void setRol(Integer rol) {
		this.rol = rol;
	}

}
