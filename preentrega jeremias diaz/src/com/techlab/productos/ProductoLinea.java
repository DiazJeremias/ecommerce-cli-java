package com.techlab.productos;

public class ProductoLinea {

    private Producto producto;
    private Integer cantProducto;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantProducto() {
        return cantProducto;
    }

    public void setCantProducto(Integer cantProducto) {
        this.cantProducto = cantProducto;
    }

    public void mostrarProductos(){

        System.out.printf("%-5d %-15s %-10.2f %-10d %-15d%n",
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                this.getCantProducto());
    }
}
