package com.modelo;

public class Producto {

	private Integer id;
	private String nombre;
	private String descripcion;
	private Double peso;
	private Integer stock;

	// Constructor sin parámetros
    public Producto() {

    }

    // Constructor con parámetros
    public Producto(Integer id, String nombre, String descripcion, Double peso, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.stock = stock;
    }

    // Constructor sin ID
    public Producto(String nombre, String descripcion, Double peso, Integer stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.stock = stock;
    }

    // Métodos getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Método toString para representar el producto
    @Override
    public String toString() {
        return "Producto [getId()=" + getId() + ", getNombre()=" + getNombre() + ", getDescripcion()="
                + getDescripcion() + ", getPeso()=" + getPeso() + ", getStock()=" + getStock() + "]";
    }
}