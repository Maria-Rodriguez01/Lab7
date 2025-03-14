/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import lab_7_binarios.Steam;

/**
 *
 * @author LENOVO
 */
public class Game extends JPanel {
    // 
    int code;
    String titulo;
    char OS;
    int minEdad;
    double precio;
    int downloads;
    String Image;
    
    //
    JButton download, delete;
    JLabel foto, codeL, tituloL, OSL, minEdadL, precioL, downloadsL;
    
    /**
     * formato games.stm int code (4); String titulo (); char sistema operativo
     * (2) int edad minima(4) double precio (8) int contador downloads(4) byte[]
     * image()
     *
     *
     */
    public Game(int code, String titulo, char OS, int minEdad, double precio, int downloads, String Image) {
        
        setBackground (Color.BLACK);
        
        // inicializar variables
        this.code = code;
        this.titulo = titulo;
        this.OS = OS;
        this.minEdad = minEdad;
        this.precio = precio;
        this.downloads = downloads;
        this.Image = Image;
       
        // inicializar componentes
            // Botones
            download = new JButton("descargar");
            delete = new JButton(" borrar ");

            //label
            foto = new JLabel();
            codeL = new JLabel("code: " + String.valueOf(code));
            tituloL = new JLabel(titulo);
            OSL = new JLabel();
            minEdadL = new JLabel("Edad minima: " + String.valueOf(minEdad));
            precioL = new JLabel("$ " + String.valueOf(precio));
            downloadsL = new JLabel("descargas: " + String.valueOf(downloads));
            foto.setIcon(new ImageIcon(Image));
            OSL.setText(new StringBuilder(OS).toString());
        
        // add
        add(foto);
        add(codeL);
        add(tituloL);
        add(OSL);
        add(minEdadL);
        add(precioL);
        add(downloadsL);
    }
    
    
    
}
