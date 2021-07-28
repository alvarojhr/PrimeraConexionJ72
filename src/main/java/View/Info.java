package View;

import Controller.Products;
import Model.Models.Producto;

import java.util.Scanner;

public abstract class Info {
    public static void showProducts(){
        System.out.println(Products.readProducts());
    }

    public static void newProduct(){
        Scanner sc = new Scanner(System.in);
        Producto producto = new Producto();
        System.out.print("Nombre: ");
        producto.setNombre(sc.nextLine());
        System.out.print("Cantidad: ");
        producto.setCantidad(sc.nextInt());
        System.out.print("Costo unitario: ");
        producto.setCostoUnitario(sc.nextDouble());

        if(Products.createProduct(producto)){
            System.out.println("Registro exitoso! 😊");
        }else {
            System.out.println("Houston, tenemos un problema");
        }
    }

}
