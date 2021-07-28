package Model;

import Model.Models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Crud {
    private static final Connection connection = DbConnection.connect();

    public static ArrayList<Producto> read(){

        ArrayList<Producto> productos = new ArrayList<>();

        ResultSet result = null;

        String sql = "SELECT * FROM Productos";
        try {
            result = connection.prepareStatement(sql).executeQuery();
            //Filling the array products with info from db
            while (result.next()) {
                Producto producto = new Producto(result.getInt(1),result.getString(2),
                                                result.getInt(3), result.getDouble(4),
                                                result.getDouble(5));
                productos.add(producto);
            }

        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
        finally {
            try {
                if (result != null) {
                    result.close();
                }
                connection.close();
            } catch (SQLException e) {
                System.out.println("CONNECTION| "+e);
            }
        }
        return productos;
    }

    public static boolean create(Producto producto){
        boolean success = false;

        PreparedStatement query = null;

        String sql = "INSERT INTO Productos(Nombre,Cantidad,CostoUnitario,ValorVenta) VALUES (?,?,?,?)";
        try {
            query = connection.prepareStatement(sql);
            query.setString(1,producto.getNombre());
            query.setInt(2,producto.getCantidad());
            query.setDouble(3,producto.getCostoUnitario());
            query.setDouble(4,producto.getValorVenta());

            query.execute();
            success = true;
        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("CONNECTION| "+e);
            }
        }

        return success;
    }
}
