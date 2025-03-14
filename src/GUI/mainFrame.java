/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    JScrollPane scroll;
    JPanel catalogo, perfil;
    JLabel User, foto;
    private ImageIcon fotazo;
    JButton editar, agregar;
    int i = 0;
    
    public mainFrame(String user, String pass) {
        // dependencias
        this.user = user;
        this.pass = pass;
        
        // seteando el frame
        setTitle ("Steam");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));
     
         // Inicialización de paneles
        catalogo = new JPanel(new GridBagLayout());
        perfil = new JPanel(new GridLayout(4, 1));

        // Inicialización de etiquetas
        User = new JLabel("Usuario: " + user);
        foto = new JLabel();

        // Inicialización de imagen
        fotazo = new ImageIcon("src\\imagenes\\default.png");
        foto.setIcon(fotazo);

        // Inicialización de botón
        editar = new JButton("Editar");
        editar.addActionListener(e -> {
            try {
                new perfil(user, pass);
                dispose();
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        agregar = new JButton ("agregar juego");
        agregar.addActionListener(e -> {
            String titulo = JOptionPane.showInputDialog(null, "ingrese el titulo");
            
            String[] opciones = {"M", "L", "W"};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Tipo de Usuario", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            char os = seleccion.charAt(0);
            int minEdad = Integer.parseInt(JOptionPane.showInputDialog(null, "ingrese la edad minima"));
            double precio = Double.parseDouble(JOptionPane.showInputDialog(null, "ingrese el precio"));
            String Image = "src\\imagenes\\default.png";
            try {
                steam.addGame(titulo, os, minEdad, precio, Image);
                new mainFrame(user, pass);
                dispose();
            } catch (IOException ex) {
                Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // scroll
        initGames();
        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(catalogo);
        
        // agregar a perfil
        perfil.add(foto);
        perfil.add(User);
        perfil.add(editar);
        try {
            if (steam.getUserTipo(user, pass).equals("admins")) {
                perfil.add(agregar);
            }
        } catch (IOException ex) {
            Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        add(perfil);
        add(scroll);
        
        //
        setVisible(true);
        
    }
    
    public final void initGames () {
        try{
            if (steam.games.length() > 0){
                while (steam.games.getFilePointer() < steam.games.length()) {
                        int code =          steam.games.readInt();
                        String title =      steam.games.readUTF();
                        char os =           steam.games.readChar();
                        int minAge =        steam.games.readInt();
                        double price =      steam.games.readDouble();
                        int downloadCount = steam.games.readInt();
                        String image =     steam.games.readUTF();
                        catalogo.add(new Game(code, title, os, minAge, price, downloadCount, image), setGBC(0, i, 5, GridBagConstraints.HORIZONTAL, GridBagConstraints.NONE, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), GridBagConstraints.NORTH));
                        i++;
                }
            }
        }catch(IOException e){
            System.out.println("whoops otra vez");
        }
    }
    
    
    GridBagConstraints setGBC( int x, int y, int GrW, int WX, int WY, int fill, Insets I, int Anch) {
        GridBagConstraints G = new GridBagConstraints();
        G.gridx = x;    // 
        G.gridy = y;    //
        G.gridwidth = GrW; // columnas de espacio
        G.weightx = WX;
        G.weighty = WY; // 
        G.fill = fill; // relleno alrededor
        G.insets = I;
        G.anchor = Anch;
        return G;
    }
}
