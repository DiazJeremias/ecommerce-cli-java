package com.techlab.productos;

import com.techlab.exceptions.NotEnoughStockException;
import com.techlab.pedidos.Pedido;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ABMProducto {

    List<Producto> productList;

    public ABMProducto(){
        productList = new ArrayList<>();
    }

    public void agregarProducto(Producto producto){
        productList.add(producto);
    }

    public void quitarProducto(Producto producto){
        quitarProductoDeLista(producto);
    }

    public void quitarProductoDeLista(Producto producto){
        Iterator<Producto> iterator = productList.iterator();

        while (iterator.hasNext()) {
            Producto productoActual = iterator.next();
            if (productoActual.getId().equals(producto.getId())) {
                iterator.remove();
            }
        }
    }

    public Producto actualizarStock(Producto producto, Integer stock) {
        Producto productoEncontrado = getProducto(producto);

        if (productoEncontrado == null) {
            System.out.println("Producto no encontrado.");
            return null;
        }

        try {
            verificarStockDisponible(productoEncontrado, producto.getStock());
            productoEncontrado.setStock(productoEncontrado.getStock() - stock);
            System.out.println("Se actualizó correctamente el producto");
            return productoEncontrado;
        } catch (NotEnoughStockException ex) {
            System.out.println("No hay suficiente stock para el producto");
            return null;
        }
    }

    public Producto getProducto(Producto producto) {
        Producto productoEncontrado = null;
        for (Producto productoActual : productList) {
            if (productoActual.getId().equals(producto.getId())) {
                productoEncontrado = productoActual;
                break;
            }
        }
        return productoEncontrado;
    }

    public void verificarStockDisponible(Producto producto, int cantidadSolicitada) throws NotEnoughStockException {
        if (producto.getStock() < cantidadSolicitada) {
            throw new NotEnoughStockException();
        }
    }

    public Producto actualizarProducto(Producto producto) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(producto.getId())) {
                productList.set(i, producto);
                return producto;
            }
        }
        return null;
    }

    public void mostrarProductos(){
        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Nombre", "Precio", "Stock");
        System.out.println("---------------------------------------------------");

        for (Producto p : productList) {
            System.out.printf("%-5d %-15s %-10.2f %-10d%n",
                        p.getId(),
                        p.getNombre(),
                        p.getPrecio(),
                        p.getStock());
            }

    }

    public Producto buscarPorId(Long id){
        for (Producto producto : productList) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }
}
