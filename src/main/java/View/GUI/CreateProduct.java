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
    private Producto producto = new Producto();

    private boolean isEditingProduct = false;

    public CreateProduct(String title){
        super(title);

        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelPrincipal);
        ImageIcon img = new ImageIcon("carrito-de-compras.png");
        this.setIconImage(img.getImage());
        this.pack();

        guardarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success;
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

                    if(!isEditingProduct) {
                        success = Products.createProduct(producto);
                    }else{
                        //Estamos editando un producto
                        success = Products.isUpdatedProduct(producto);
                        isEditingProduct = false;
                        hideWindow();
                    }

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
                hideWindow();
            }
        });
    }

    public void editarProducto(int id, String nombre, int cantidad, String costoUnitario){
        nameField.setText(nombre);
        cantidadSpinner.setValue(cantidad);
        costoField.setText(costoUnitario);

        producto.setId(id);

        isEditingProduct = true;
    }

    private void hideWindow(){
        dispose();
        Home.setCreatingOpen(false);
    }
}
