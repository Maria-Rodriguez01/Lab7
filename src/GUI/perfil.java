/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import lab_7_binarios.Steam;

/**
 *
 * @author LENOVO
 */
public class perfil extends JFrame {
    // menu de variables
    private JPanel contenido, perfil;
    private JLabel User, Pass, code, name, nacimiento, tipoUser, foto, downloads;
    private JTextPane userP, passP, codeP, nameP, nacimientoP, downP;
    private JButton tipo, eliminar, cambiar, regresar;
    private ImageIcon fotazo;
    
    // dependencias
    private String user, pass;
    private Steam steam = new Steam();
    
    // constructor
    public perfil(String user, String pass) throws IOException {
        // seteando el frame
        setTitle ("Perfil");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));
        
        // dependencias
        this.user = user;
        this.pass = pass;
        
        // Inicialización de paneles
        contenido = new JPanel(new GridLayout(8, 2));
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
        codeP.setText(String.valueOf(getCode())); // funcion para get code del user
        nameP = new JTextPane();
        nameP.setText(getNombre());
        nacimientoP = new JTextPane();
        nacimientoP.setText(getNacimiento());
        downP = new JTextPane();
        downP.setText(getDownloads());
        
        // Inicialización de botones
        tipo = new JButton("Tipo");
        eliminar = new JButton("Eliminar");
        cambiar = new JButton("Cambiar");   
        regresar = new JButton("regresar");
        
        // aciones
        cambiar.addActionListener(e -> {
            JOptionPane.showMessageDialog (null, "no disponible D:");

        });
        tipo.addActionListener(e -> {
            String[] opciones = {"admins", "normal"};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Tipo de Usuario", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            try {
                steam.setUserTipo(user, pass, seleccion);
            } catch (IOException ex) {
                Logger.getLogger(perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        regresar.addActionListener(e -> {
            new mainFrame(user, pass);
            dispose();
        });
        eliminar.addActionListener(e -> {
            try {
                if(steam.deletePlayer(user)){
                    new LOGIN();
                    dispose();
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
        // Inicialización de imagen
        fotazo = new ImageIcon(getImage());
        
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
        contenido.add(regresar);
        add (contenido);
        
        // hacer visible
        setVisible(true);
    }
    
    public void eliminar () {}
    
    // va en codeP.setText()
    public final int getCode () throws IOException {
        return steam.getUserCode(user, pass);
    }    
    
    //** va en nameP.setText()
    public final String getNombre () throws IOException {      
        return steam.getName(user, pass);
    }    
    
    // va en downP.setText()
    public final String getDownloads () {
        return "";
    }    
    
    // va en nacimientoP.setText()
    public final String getNacimiento () throws IOException {
        Date date = steam.getBirth(user, pass);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int anno = calendar.get(Calendar.YEAR);
        
        return day + "/" + mes + "/" + anno;
    }    
    
    public final String getImage () {
        try {
            return steam.getImage(user, pass);
        }catch (IOException e) {
            System.out.println("whoops");
        }
        return "";
    }
    
}
