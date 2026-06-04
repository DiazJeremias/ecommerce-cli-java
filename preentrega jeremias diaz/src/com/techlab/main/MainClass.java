package com.techlab.main;

import com.techlab.exceptions.NotEnoughStockException;
import com.techlab.exceptions.ProductNotFoundException;
import com.techlab.pedidos.ABMPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.ABMProducto;
import com.techlab.productos.Producto;
import com.techlab.pedidos.LineaPedido;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args){
        ABMPedido abmPedidos = new ABMPedido();
        ABMProducto abmProductos = new ABMProducto();
        Scanner scanner = new Scanner(System.in);

        boolean flag= true;
        while(flag) {

            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("a. Agregar producto: ");
            System.out.println("b. Listar Productos: ");
            System.out.println("c. Buscar/Actualizar Producto: ");
            System.out.println("d. Eliminar Producto: ");
            System.out.println("e. Listar Pedidos: ");
            System.out.println("f. Agregar pedido: ");
            System.out.println("g. Salir: ");


            String nombre = scanner.nextLine().trim().toLowerCase();

            switch (nombre) {
                case "a":
                    Producto producto = menuAgregarProducto(scanner);
                    abmProductos.agregarProducto(producto);
                    System.out.println("Producto agregado. " + producto.getId());
                    break;
                case "b":
                    abmProductos.mostrarProductos();
                    break;
                case "c":
                    Producto productoActualizado = menuBuscarProducto(scanner, abmProductos);
                    if (productoActualizado != null) {
                        Producto productoModificado = menuActualizarProducto(scanner, productoActualizado);
                        if (productoModificado != null) {
                            abmProductos.actualizarProducto(productoActualizado);
                            System.out.println("Producto actualizado con éxito.");
                        }
                    }
                    break;
                case "d":
                    Producto productoAEliminar = menuEliminarProducto(scanner);
                    abmProductos.quitarProducto(productoAEliminar);
                    break;
                case "e":
                    abmPedidos.mostrarPedidos();
                    break;
                case "f":
                    Pedido pedidoNuevo = menuAgregarPedido(scanner,abmProductos);
                    if (pedidoNuevo != null) {
                        abmPedidos.agregarPedido(pedidoNuevo);
                    }
                    break;
                case "g":
                    flag=false;
                    System.out.println("Gracias por utilizar nuestro sistema.");
                    break;
                default:
                    System.out.println("Opción inválida. Vuelva a intentarlo:");
            }
        }
        scanner.close();
    }

    private static Pedido menuAgregarPedido(Scanner scanner,ABMProducto abmProducto) {
        System.out.println("Cuantos productos va a agregar?: ");
        String cantProductosAAgregar = scanner.nextLine();
        Integer contadorProductos =  Integer.valueOf(cantProductosAAgregar);


        List<LineaPedido> productos = new ArrayList<>();
        while (contadorProductos>0){
            System.out.println("Ingresar id producto: ");
            String idProducto = scanner.nextLine();
            System.out.println("Ingresar cantidad productos: ");
            Integer cantProducto = Integer.valueOf(scanner.nextLine());

            LineaPedido prodLinea = new LineaPedido();
            Producto producto = new Producto();
            producto.setId(Long.valueOf(idProducto));
            producto.setStock(cantProducto);

            Producto productoEnLista = abmProducto.getProducto(producto);
            try {
                if(productoEnLista==null){
                    throw new ProductNotFoundException();
                }


                abmProducto.verificarStockDisponible(productoEnLista, producto.getStock());

                producto.setPrecio(productoEnLista.getPrecio());
                producto.setNombre(productoEnLista.getNombre());
                producto.setStock(productoEnLista.getStock());

                prodLinea.setProducto(producto);
                prodLinea.setCantProducto(cantProducto);

                if(productoEnLista!=null){
                    productos.add(prodLinea);
                    contadorProductos--;
                }
            }
            catch(ProductNotFoundException pNotFoundEx ){
                System.out.println("No se encontro el producto. Vuelva a ingresar ");
            }
            catch (NotEnoughStockException e) {
                System.out.println("No hay stock suficiente para el producto indicado, vuelva a cargar. Stock actual: "+productoEnLista.getStock());
            }

        }
        System.out.println("El pedido es : ");
        mostrarProductosLinea(productos);
        System.out.println("¿Confirma pedido? S/N");
        String confirmacion = scanner.nextLine();
        Pedido pedido = null;
        switch(confirmacion){

            case "S":
                actualizarStockProductos(productos,abmProducto);
                pedido = new Pedido(productos);
                break;
            case "N":

            default:

                System.out.println("Pedido descartado.");
                break;
        }


        return pedido;
    }

    private static void actualizarStockProductos(List<LineaPedido> productos, ABMProducto abmProducto) {
        for(LineaPedido productoActual : productos){
            abmProducto.actualizarStock(productoActual.getProducto(),productoActual.getCantProducto());
        }
    }

    private static void mostrarProductosLinea(List<LineaPedido> productos) {
        System.out.printf("%-5s %-15s %-10s %-10s %-15s%n", "ID", "Nombre", "Precio", "Stock", "Cant Producto pedido");
        System.out.println("-----------------------------------------------------------------------");

        for(LineaPedido pLinea : productos){
            pLinea.mostrarProductos();
        }

    }

    private static Producto menuEliminarProducto(Scanner scanner) {
        System.out.println("Ingresar id producto: ");
        String idProducto = scanner.nextLine();

        Producto productoAEliminar = new Producto();
        productoAEliminar.setId(Long.valueOf(idProducto));
        return productoAEliminar;
    }


    private static Producto menuAgregarProducto(Scanner scanner) {

        System.out.println("Ingresar nombre producto: ");
        String nombreProducto = scanner.nextLine();
        System.out.println("Ingresar precio producto: ");
        String precioProducto = scanner.nextLine();
        System.out.println("Ingresar stock producto: ");
        String stockProducto = scanner.nextLine();

        Producto producto =  new Producto();
        producto.setNombre(nombreProducto);
        producto.setPrecio(Double.valueOf(precioProducto));
        producto.setStock(Integer.valueOf(stockProducto));

        return producto;

    }

    private static Producto menuBuscarProducto(Scanner scanner, ABMProducto abmProducto) {
        System.out.println("Ingrese el id del producto que desea buscar: ");
        Long idProducto = Long.valueOf(scanner.nextLine());

        Producto productoEncontrado = abmProducto.buscarPorId(idProducto);
        if(productoEncontrado==null){
            System.out.println("El producto no existe.");
            return null;
        }

        System.out.println("\n--- INFORMACIÓN DEL PRODUCTO ENCONTRADO ---");
        System.out.println("ID:     " + productoEncontrado.getId());
        System.out.println("Nombre: " + productoEncontrado.getNombre());
        System.out.println("Precio: $" + productoEncontrado.getPrecio());
        System.out.println("Stock:  " + productoEncontrado.getStock());
        System.out.println("-------------------------------------------\n");

        return productoEncontrado;
    }

    private static Producto menuActualizarProducto(Scanner scanner, Producto producto) {
        System.out.println("Desea actualizar el producto? S/N");
        String confirmacion = scanner.nextLine().trim().toUpperCase();

        if(!confirmacion.equals("S")){
            System.out.println("No hubo modificaciones.");
            return null;
        }

        System.out.println("Ingrese el nuevo nombre: ");
        producto.setNombre(scanner.nextLine());

        producto.setPrecio(leerNumeroPositivo(scanner, "Ingrese el nuevo precio: ",
                "El precio debe ser un valor positivo. Intente de nuevo: ", false));

        producto.setStock(Integer.valueOf((int) leerNumeroPositivo(scanner, "Ingrese el nuevo stock: ",
                "El stock no puede ser valor negativo. Intente de nuevo: ", true)));

        return producto;
    }

    private static double leerNumeroPositivo(Scanner scanner, String mensaje, String mensajeError, boolean permiteCero) {
        double numero = 0;
        while (true) {
            try {
                System.out.println(mensaje);
                numero = Double.valueOf(scanner.nextLine());
                if(permiteCero && numero >= 0){
                    break;
                } else if (!permiteCero && numero > 0) {
                    break;
                }
                System.out.println(mensajeError);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numérico válido.");
            }
        }
        return numero;
    }
}
