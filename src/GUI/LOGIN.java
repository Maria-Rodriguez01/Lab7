/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridLayout;


import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import lab_7_binarios.Steam;

/**
 * Login o crear cuenta: - Esta clase sirve como amvbiente visual para crear
 * cuenta o hacer login, -Si alguna de ambas es exitosa manda al catalogo con las
 * opciones para player
 */
public class LOGIN extends JFrame {

    // menu de variables
    private String user, pass;
    private Steam steamManager = new Steam();
    private final JPanel contenido;
    private final JButton login, crear;
    private JTextPane logTxt;
    private JPasswordField passTxt;
    private final JLabel steam, User, Pass;

    // constructor
    public LOGIN() {
        // seteando el frame
        setTitle("Inicio de sesion");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // inicializacion
        contenido = new JPanel();
        steam = new JLabel(" STEAM ");
        User = new JLabel("Username: ");
        Pass = new JLabel("Password: ");
        passTxt = new JPasswordField();
        logTxt = new JTextPane();
        login = new JButton("Log in");
        crear = new JButton("Crear Cuenta");

        // action listener
        login.addActionListener(e -> {
            try {
                login();
            } catch (IOException ex) {
                Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        crear.addActionListener(e -> {
            try {
                crear();
            } catch (IOException ex) {
                Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // agregar al panel
        contenido.setLayout(new GridLayout(4, 2));
        contenido.add(steam);
        contenido.add(new JLabel());
        contenido.add(User);
        contenido.add(logTxt);
        contenido.add(Pass);
        contenido.add(passTxt);
        contenido.add(login);
        contenido.add(crear);
        add(contenido);

        // hacer visible
        setVisible(true);
    }

    /**
     * funcion para login
     *
     * @throws java.io.IOException
     */
    public final void login() throws IOException {
        if (steamManager.login(user = logTxt.getText(), pass = new String (passTxt.getPassword()))) {
            new mainFrame(user,pass);
            dispose();
        }
    }

    /**
     * funcion para crear cuenta
     *
     * @throws java.io.IOException
     */
    public final void crear() throws IOException {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre");

        
        if(steamManager.addPlayer(Calendar.getInstance(), logTxt.getText(), new String(passTxt.getPassword()), nombre, "src\\imagenes\\default.png", "Normal")){
            new mainFrame(logTxt.getText(), new String (passTxt.getPassword()));
            dispose();
        }
        
    }

    public static void main(String[] args) {
        new LOGIN();
    }

}
