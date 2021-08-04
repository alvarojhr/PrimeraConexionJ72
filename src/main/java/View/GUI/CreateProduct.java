package View.GUI;

import Controller.Products;
import Model.Crud;
import Model.DbConnection;
import Model.Models.Producto;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateProduct extends JFrame{
    private JLabel Label1;
    private JPanel PanelPrincipal;
    private JTextField nameField;
    private JTextField costoField;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JSpinner cantidadSpinner;


    public CreateProduct(String title){
        super(title);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelPrincipal);
        ImageIcon img = new ImageIcon("carrito-de-compras.png");
        this.setIconImage(img.getImage());
        this.pack();


        Producto producto = new Producto();

        guardarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Aca deberian ir las validaciones
                String name = nameField.getText();
                int cantidad =Integer.parseInt(cantidadSpinner.getValue().toString());
                String costoStr = costoField.getText();
                double costo;
                try {
                    costo = Double.parseDouble(costoStr);
                }catch (NumberFormatException nExc){
                    System.out.println("Este valor no es valido");
                    costo = -1;
                }

                if (!name.isBlank() && cantidad > 0 && costo>0) {
                    producto.setNombre(name);
                    producto.setCantidad(cantidad);
                    producto.setCostoUnitario(costo);

                    boolean success = Products.createProduct(producto);
                    if(success){
                        nameField.setText("");
                        cantidadSpinner.setValue(0);
                        costoField.setText("");
                    }
                    System.out.println(success);
                }else{
                    System.out.println("Alguno de los valores no es válido");
                }
            }

        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //Cambiamos la bandera para controlar que solo tengamos un frame de creacion abierto
                  Home.setCreatingOpen(false);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home.setCreatingOpen(false);
            }
        });
    }
}
