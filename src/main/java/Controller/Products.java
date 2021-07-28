package Controller;

import Model.Crud;
import Model.Models.Producto;

import java.util.ArrayList;

public abstract class Products {
    public static String readProducts(){
        String result = "";
        ArrayList<Producto> data = Crud.read();

        for (Producto producto:data) {
            result += producto.getId() + ". " + producto.getNombre() + " - cant: " + producto.getCantidad() + " - costo unitario: " + producto.getCostoUnitario() + "  - valor venta: " + producto.getValorVenta()+"\n";
        }

        return result;
    }

    public static boolean createProduct(Producto producto){
        double valorVenta = producto.getCostoUnitario()*1.5;
        producto.setValorVenta(valorVenta);
        return Crud.create(producto);
    }
}
