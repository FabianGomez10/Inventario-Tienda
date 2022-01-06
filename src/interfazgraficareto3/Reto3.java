/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazgraficareto3;

import java.util.Scanner;

/**
 *
 * @author Oscar Gomez
 */
public class Reto3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String opcion = input.nextLine();
        String datosAIngresar = input.nextLine();
        String[] listaDatos = datosAIngresar.split(" ");
        int codigo = Integer.parseInt(listaDatos[0]);
        String nombre = listaDatos[1];
        float precio = Float.parseFloat(listaDatos[2]);
        int inventario = Integer.parseInt(listaDatos[3]);
        Producto producto= new Producto(codigo,nombre,precio,inventario);
        BaseDatosProductos datosProductos = new BaseDatosProductos();
        if ("AGREGAR".equals(opcion)) {
            //datosProductos.agregar(codigo, nombre, precio, inventario);
            datosProductos.agregar(producto);
        } else if ("BORRAR".equals(opcion)) {
            datosProductos.borrar(producto);
        } else if ("ACTUALIZAR".equals(opcion)) {
            datosProductos.actualizar(producto);
        }
    }

}

