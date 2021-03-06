package Model;

import Model.Models.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public abstract class Crud {
    private static Connection connection;

    public static Connection setConnection(Connection connection) {
        Crud.connection = connection;
        return  connection;
    }

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
        return success;
    }

    public static void delete(int id){
        PreparedStatement query = null;

        try{
            String sql = String.format("DELETE FROM Productos WHERE Id=%d",id);
            query = connection.prepareStatement(sql);
            query.execute();
        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
    }

    public static Producto getProductoById(int id){
            Producto producto= null;
            ResultSet result = null;

            String sql = String.format("SELECT * FROM Productos where id=%d",id);

            try {
                result = connection.prepareStatement(sql).executeQuery();
                producto = new Producto(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4),result.getDouble(5));
            } catch (SQLException e) {
                System.out.println("CONNECTION| " + e);
            } finally {
                try {
                    if (result != null) {
                        result.close();
                    }

                } catch (SQLException e) {
                    System.out.println("CONNECTION| "+e);
                }
            }
            return producto;
    }

    public static boolean update(Producto producto) {
        boolean success = false;

        PreparedStatement query = null;
        String sql = String.format(Locale.ROOT,"UPDATE Productos SET Nombre='%s', Cantidad=%d, CostoUnitario =%.2f, ValorVenta = %.2f WHERE Id = %d",
                    producto.getNombre(),producto.getCantidad(),producto.getCostoUnitario(),producto.getValorVenta(),producto.getId());
        try {
            query = connection.prepareStatement(sql);
            query.execute();
            success = true;
        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
        return success;
    }
}
