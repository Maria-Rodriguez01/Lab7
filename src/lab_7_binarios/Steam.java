package lab_7_binarios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            if(!file.exists()){
                file = new File("steam");
                file.mkdir();
            }
            file = new File("steam/codes");
            if(!file.exists()){
                codes = new RandomAccessFile("steam/codes.stm", "rw");
                initCodes();
            }
            file = new File("steam/games");
            if(!file.exists()){
                games = new RandomAccessFile("steam/games.stm", "rw");
            }
            file = new File("steam/player");
            if(!file.exists()){
                player = new RandomAccessFile("steam/player.stm","rw");
            }
            
            }catch (IOException e){
            }
        }
    
    private void initCodes() throws IOException{
        if(codes.length() == 0){
            codes.writeInt(111);
        }
    }
    
    private int getCode() throws IOException{
        codes.seek(0);
        int code = codes.readInt();
        rcods.seek(0);
        rcods.writeInt(code+1);
        return code;
    }
    }
    
    

