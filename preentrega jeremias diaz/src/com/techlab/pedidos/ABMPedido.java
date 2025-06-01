package com.techlab.pedidos;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ABMPedido {

    private Map<Long,Pedido> pedidoMap;

    public ABMPedido(){
        pedidoMap =  new HashMap<>();
    }

    public void agregarPedido(Pedido pedido){
        pedidoMap.put(pedido.getId(), pedido);
    }

    public void quitarPedido(Pedido pedido){

        pedidoMap.remove(pedido.getId());
    }

    public void mostrarPedidos(){
        List<Pedido> pedidos = new ArrayList<>(pedidoMap.values());

        for(Pedido pedidoActual: pedidos){
            System.out.println("ID del pedido : "+ pedidoActual.getId());
            pedidoActual.mostrarProductosDelPedido();
            System.out.println("-------------------------------------");
        }
    }


}
