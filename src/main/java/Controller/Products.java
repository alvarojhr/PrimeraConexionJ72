package Controller;

import Model.Crud;
import Model.Models.Producto;

import java.util.ArrayList;

public abstract class Products {
    private static final float utilidad = 1.5f;

    public static String readProducts(){
        String result = "";
        ArrayList<Producto> data = Crud.read();

        for (Producto producto:data) {
            result += producto.getId() + ". " + producto.getNombre() + " - cant: " + producto.getCantidad() + " - costo unitario: " + producto.getCostoUnitario() + "  - valor venta: " + producto.getValorVenta()+"\n";
        }

        return result;
    }

    public static String[][] getProducts(){
        //Obtenemos la información de los productos desde el Model como un ArrayList de instancias de Producto.
        ArrayList<Producto> data = Crud.read();

        String[][] result = new String[data.size()][5];
        int contProducto = 0;

        //Procesamiento para convertir el ArrayList de productos en una matriz de String con la información de los productos.
        for (Producto producto:data) {
            String[] vector = {String.valueOf(producto.getId()),producto.getNombre(),String.valueOf(producto.getCantidad()),
                                String.valueOf(producto.getCostoUnitario()),String.valueOf(producto.getValorVenta())};
            result[contProducto] = vector;
            contProducto++;
        }

        return result;
    }

    public static boolean createProduct(Producto producto){
        calcularValorVenta(producto);
        return Crud.create(producto);
    }

    public static void deleteProduct(int id){
        Crud.delete(id);
    }

    public static Producto getProductoById(int id){
        return Crud.getProductoById(id);
    }

    public static String updateProduct(Producto producto) {
        String output = "";
        //Calcular el valor de venta basado en la utilidad y el costo unitario
        calcularValorVenta(producto);

        if (Crud.update(producto)){
            output += "Actualización exitosa";
        }else{
            output += "Houston tenemos un problema";
        }
        return output;
    }

    private static void calcularValorVenta(Producto producto){
        double valorVenta = producto.getCostoUnitario()*utilidad;
        producto.setValorVenta(valorVenta);
    }
}
