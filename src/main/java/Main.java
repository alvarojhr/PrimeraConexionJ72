import Model.Crud;
import Model.DbConnection;
import View.Console.Info;
import View.GUI.CreateProduct;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //Abrimos la conexion para todas las operaciones
        Connection connection = Crud.setConnection(DbConnection.connect());

        //Info.showProducts();
        //Info.newProduct();
        //Info.deleteProduct();
        //Info.updateProduct();

        JFrame frame = new CreateProduct("Creacion de producto");
        frame.setVisible(true);

        //Cerramos la conexion que creamos al iniciar el programa
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("CONNECTION| "+e);
        }
    }
}
