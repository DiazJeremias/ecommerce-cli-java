package com.techlab.pedidos;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductoLinea;

import java.util.List;
import java.util.Objects;

public class Pedido {

   private Long id;
   private List<ProductoLinea> productList;
   private Double total;

   public Pedido(List<ProductoLinea> productList){
    Double subtotal=0.0;
    this.productList = productList;

       for(ProductoLinea linea: productList){
           subtotal +=linea.getProducto().getPrecio() * linea.getCantProducto();
       }

       total = subtotal;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductoLinea> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductoLinea> productList) {
        this.productList = productList;
    }

    public Double getTotal() {
        return total;
    }

    public void mostrarProductosDelPedido(){
       for(ProductoLinea pLinea: productList){
           System.out.println("Nombre producto: " + pLinea.getProducto().getNombre());
           System.out.println("Precio: " + pLinea.getProducto().getPrecio());
           System.out.println("Cantidad: " + pLinea.getCantProducto());

       }


    }

}
