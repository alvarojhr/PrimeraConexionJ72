import Model.Crud;
import Model.DbConnection;
import View.Info;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //Abrimos la conexion para todas las operaciones
        Connection connection = Crud.setConnection(DbConnection.connect());

        Info.showProducts();
        Info.newProduct();
        Info.deleteProduct();

        //Cerramos la conexion que creamos al iniciar el programa
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
    }
}
