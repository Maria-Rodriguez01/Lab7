/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author LENOVO
 */
public class perfil extends JFrame {
    // menu de variables
    private JPanel contenido, perfil;
    private JLabel User, Pass, code, name, nacimiento, tipoUser, foto, downloads;
    private JTextPane userP, passP, codeP, nameP, nacimientoP, downP;
    private JButton tipo, eliminar, cambiar;
    private ImageIcon fotazo;
    
    // dependencias
    private String user, pass;
    
    // constructor
    public perfil(String user, String pass) {
        // seteando el frame
        setTitle ("Inicio de sesion");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));
        
        // dependencias
        this.user = user;
        this.pass = pass;
        
        // Inicialización de paneles
        contenido = new JPanel(new GridLayout(7, 2));
        perfil = new JPanel(new GridLayout(1, 2));
        
        // Inicialización de etiquetas
        User = new JLabel("Usuario:");
        Pass = new JLabel("Contraseña:");
        code = new JLabel("Código:");
        name = new JLabel("Nombre:");
        nacimiento = new JLabel("Nacimiento:");
        tipoUser = new JLabel("Tipo de Usuario:");
        downloads = new JLabel("Descargas:");
        foto = new JLabel();
        
        // Inicialización de campos de texto
        userP = new JTextPane();
        userP.setText(user);
        passP = new JTextPane();
        passP.setText(pass);
        codeP = new JTextPane();
        codeP.setText(getCode()); // funcion para get code del user
        nameP = new JTextPane();
        nameP.setText(getName());
        nacimientoP = new JTextPane();
        nacimientoP.setText(getNacimiento());
        downP = new JTextPane();
        downP.setText(getDownloads());
        
        // Inicialización de botones
        tipo = new JButton("Tipo");
        eliminar = new JButton("Eliminar");
        cambiar = new JButton("Cambiar");
        
        // Inicialización de imagen
        fotazo = new ImageIcon("ruta/a/la/imagen.jpg");
        
        // agregar foto 
        foto.setIcon(fotazo);
        perfil . add (foto);
        perfil.add(cambiar);
        add(perfil);
        
        // contenido
        contenido.add(User);
        contenido.add(userP);
        contenido.add(Pass);
        contenido.add(passP);
        contenido.add(code);
        contenido.add(codeP);
        contenido.add(name);
        contenido.add(nameP);
        contenido.add(nacimiento);
        contenido.add(nacimientoP);
        contenido.add(downloads);
        contenido.add(downP);
        
        
        // contenido : botones
        contenido.add(tipo);
        contenido.add(eliminar);
        add (contenido);
        
        // hacer visible
        setVisible(true);
    }
    
    public void eliminar () {}
    
    public void cambiarTipoUser () {}
    
    // va en codeP.setText()
    public final String getCode () {
        return "";
    }    
    
    //** va en nameP.setText()
    @Override
    public final String getName () {
        return "";
    }    
    
    // va en downP.setText()
    public final String getDownloads () {
        return "";
    }    
    
    // va en nacimientoP.setText()
    public final String getNacimiento () {
        return "";
    }    
    
    public static void main(String[] args) {
        new perfil("robRigattoni", "123");
    }
}
