package View.GUI;

import javax.swing.*;
import java.awt.*;

public class CreateProduct extends JFrame{
    private JLabel Label1;
    private JPanel PanelPrincipal;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;
    private JButton button2;

    public CreateProduct(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(PanelPrincipal);
        this.pack();
    }
}
