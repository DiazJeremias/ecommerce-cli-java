package com.techlab.productos;

public class Producto {

    private static long contadorId = 0;

    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;

    public Producto() {
        contadorId++;
        this.id = contadorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
