package View.GUI;

import Controller.Products;
import Model.Crud;
import Model.DbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class Home  extends JFrame{
    private JButton addButton;
    private JButton editarButton;
    private JButton deleteButton;
    private JTable table1;
    private JPanel Principal;
    private JButton refrescarButton;
    private static boolean creatingOpen = false;
    private final String[] columnas = {"Id","Nombre","Cantidad","Costo unitario","Valor unitario"};

    private Connection connection;

    public Home(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(Principal);
        this.pack();




        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!creatingOpen) {
                    JFrame frame = new CreateProduct("Creacion de producto");
                    frame.setVisible(true);
                    creatingOpen = true;
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                connection= Crud.setConnection(DbConnection.connect());
                loadDataTable();

                table1.getColumnModel().getColumn(0).setPreferredWidth(4);
                table1.getColumnModel().getColumn(1).setPreferredWidth(120);
                table1.getColumnModel().getColumn(2).setPreferredWidth(25);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //Cerramos la conexion que creamos al iniciar el programa
                try {
                    connection.close();
                    System.out.println("Estamos desconectados ☹");
                    Home.setCreatingOpen(false);
                } catch (SQLException exc) {
                    System.out.println("CONNECTION| "+exc);
                }
            }
        });

        refrescarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTable();
            }
        });
    }

    public static void setCreatingOpen(boolean creatingOpen) {
        Home.creatingOpen = creatingOpen;
    }

    private void loadDataTable() {
        String[][] data = Products.getProducts();
        DefaultTableModel dtm = new DefaultTableModel(data,columnas);

        table1.setModel(dtm);
    }
}
