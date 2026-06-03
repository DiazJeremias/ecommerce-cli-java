package com.techlab.pedidos;

import java.util.List;

public class Pedido {

    private static long contadorId = 0;

   private Long id;
   private List<LineaPedido> productList;
   private Double total;

   public Pedido(List<LineaPedido> productList){
       contadorId++;
       this.id = contadorId;

       Double subtotal = 0.0;
       this.productList = productList;

       for(LineaPedido linea: productList){
           subtotal += linea.getProducto().getPrecio() * linea.getCantProducto();
       }

       total = subtotal;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LineaPedido> getProductList() {
        return productList;
    }

    public void setProductList(List<LineaPedido> productList) {
        this.productList = productList;
    }

    public Double getTotal() {
        return total;
    }

    public void mostrarProductosDelPedido(){
       for(LineaPedido pLinea: productList){
           System.out.println("Nombre producto: " + pLinea.getProducto().getNombre());
           System.out.println("Precio: " + pLinea.getProducto().getPrecio());
           System.out.println("Cantidad: " + pLinea.getCantProducto());

       }


    }

}
