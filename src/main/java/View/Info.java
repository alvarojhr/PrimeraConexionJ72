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

    public static void deleteProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite el id del producto a eliminar: ");
        int id = sc.nextInt();
        Products.deleteProduct(id);
    }

    public static void updateProduct(){
        Scanner sc = new Scanner(System.in);
        showProducts();
        System.out.print("Digite el id del producto a actualizar: ");
        int id = sc.nextInt();
        Producto producto = Products.getProductoById(id);
        System.out.println("Para los valores que no desea modificar, oprima ENTER.");

        System.out.println("Nombre actual: "+ producto.getNombre());
        System.out.print("Nuevo nombre: ");
        sc.nextLine();
        String nombre = sc.nextLine();
        if(!nombre.isBlank()){
            producto.setNombre(nombre);
        }
        System.out.println("Cantidad actual: "+ producto.getCantidad());
        System.out.print("Nueva cantidad: ");
        String cantidad = sc.nextLine();
        if(!cantidad.isBlank()){
            producto.setCantidad(Integer.parseInt(cantidad));
        }
        System.out.println("Costo unitario actual: "+ producto.getCostoUnitario());
        System.out.print("Nueva costo unitario: ");
        String costo = sc.nextLine();
        if(!costo.isBlank()){
            producto.setCostoUnitario(Double.parseDouble(costo));
        }

        System.out.println(Products.updateProduct(producto)) ;
    }

}
