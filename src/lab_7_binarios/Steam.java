package lab_7_binarios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maria Gabriela
 */
public class Steam {
    RandomAccessFile codes,games,player;
    File file;
    
    
    public Steam(){
        try{
            file = new File("steam/downloads");
            if(!file.exists()){
                file.mkdirs();
            }
             codes = new RandomAccessFile("steam/codes.stm", "rw");
                initCodes();
            
                games = new RandomAccessFile("steam/games.stm", "rw");
            
                player = new RandomAccessFile("steam/player.stm","rw");
            
            
            }catch (IOException e){
            }
        }
    
    private void initCodes() throws IOException{
        if(codes.length() == 0){
            codes.writeInt(1);
            codes.writeInt(1);
            codes.writeInt(1);
        }
    }
    
    /*
    codes.stm Formato
    int codigo para games
    int codigo para clientes
    int codigo para downloads
    */
    
    
    /**
    0 - games
    1-  user
    2- downloads
    */
    
    
    private int getNextCode(int tipo) throws IOException{
            int pos = tipo * 4;
            codes.seek(pos);
            int code = codes.readInt();
            codes.seek(pos);
            codes.writeInt(code+1);
            return code;

        } 
    
    /**
    formato  games.stm
    int code (4);
    String titulo ();
    char sistema operativo (2)
    int edad minima(4)
    double precio (8)
    int contador downloads(4)
    byte[] image()
    
    
    */
    private void addGame(String juego, char sistemaOperativo, int edadMinima, double precio, byte[] imagen) throws IOException{
        int code = getNextCode(0);
        long pointer = games.length();
        games.seek(pointer);
        games.writeInt(code);
        games.writeUTF(juego);
        games.writeChar(sistemaOperativo);
        games.writeInt(edadMinima);
        games.writeDouble(precio);
        games.writeInt(0);
        games.write(imagen);
    }
    
    /**
    formato player.stm
    int code (4)
    String username ()
    String password()
    String nombre()
    long nacimiento (8)
    int contador downloads(4)
    byte[] imagenPlayer
    String tipoUsuario
    
    */
    
    private void addPlayer(Calendar nacimiento, String username, String password,String nombre, byte[] imagen, String tipoUsuario) 
        throws IOException{
        long pointer = player.length();
        player.seek(pointer);
        int code = getNextCode(1);
        player.writeInt(code);
        player.writeUTF(username);
        player.writeUTF(password);
        player.writeUTF(nombre);
        player.writeLong(nacimiento.getTimeInMillis());
        player.writeInt(0);
        player.write(imagen);
        player.writeUTF(tipoUsuario);
    }
    
    
    }
    
    

