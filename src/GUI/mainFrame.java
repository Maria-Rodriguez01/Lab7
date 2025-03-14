/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridLayout;
import javax.swing.*;
import lab_7_binarios.Steam;

/**
 *
 * @author LENOVO
 */
public class mainFrame extends JFrame {
    // dependencias
    private String user, pass;
    private Steam steam = new Steam();
    
    // cosas
    JPanel catalogo, perfil;
    JLabel 
    
    public mainFrame(String user, String pass) {
        // dependencias
        this.user = user;
        this.pass = pass;
        
        // seteando el frame
        setTitle ("Inicio de sesion");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));
        
    }
    
    
    
}
