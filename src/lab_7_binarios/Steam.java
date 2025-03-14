package lab_7_binarios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Maria Gabriela
 */
public class Steam {

    RandomAccessFile codes, games, player;
    File file;

    public Steam() {
        try {
            file = new File("steam/downloads");
            if (!file.exists()) {
                file.mkdirs();
            }
            codes = new RandomAccessFile("steam/codes.stm", "rw");
            initCodes();

            games = new RandomAccessFile("steam/games.stm", "rw");

            player = new RandomAccessFile("steam/player.stm", "rw");

        } catch (IOException e) {
        }
    }

    private void initCodes() throws IOException {
        if (codes.length() == 0) {
            codes.writeInt(1);
            codes.writeInt(1);
            codes.writeInt(1);
        }
    }

    /**
     * codes.stm Formato int codigo para games int codigo para clientes int
     * codigo para downloads
     */
    /**
     * 0 - games 1- user 2- downloads
     */
    private int getNextCode(int tipo) throws IOException {
        int pos = tipo * 4;
        codes.seek(pos);
        int code = codes.readInt();
        codes.seek(pos);
        codes.writeInt(code + 1);
        return code;

    }

    /**
     * formato games.stm int code (4); String titulo (); char sistema operativo
     * (2) int edad minima(4) double precio (8) int contador downloads(4) byte[]
     * image()
     *
     *
     */
    private void addGame(String juego, char sistemaOperativo, int edadMinima, double precio, byte[] imagen) throws IOException {
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
     * formato player.stm int code (4) String username () String password()
     * String nombre() long nacimiento (8) int contador downloads(4) byte[]
     * imagenPlayer String tipoUsuario
     *
     */
    private void addPlayer(Calendar nacimiento, String username, String password, String nombre, byte[] imagen, String tipoUsuario)
            throws IOException {
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

    public boolean downloadGame(int gameCode, int playerCode, char os) {
        try {
            // Verificar si el juego y el jugador existen
            games.seek(0);
            boolean gameExists = false;
            String gameTitle = "";
            double gamePrice = 0;
            int minAge = 0;

            while (games.getFilePointer() < games.length()) {
                int code = games.readInt();
                String title = games.readUTF();
                char gameOS = games.readChar();
                minAge = games.readInt();
                gamePrice = games.readDouble();
                int downloadCount = games.readInt();
                int imageSize = games.readInt();
                games.skipBytes(imageSize);

                if (code == gameCode && gameOS == os) {
                    gameExists = true;
                    gameTitle = title;
                    break;
                }
            }

            player.seek(0);
            boolean playerExists = false;
            String playerName = "";
            long birthdate = 0;

            while (player.getFilePointer() < player.length()) {
                int code = player.readInt();
                String username = player.readUTF();
                player.readUTF(); // Skip password
                playerName = player.readUTF();
                birthdate = player.readLong();
                int downloadCount = player.readInt();
                int imageSize = player.readInt();
                player.skipBytes(imageSize);
                player.readUTF(); // Skip userType

                if (code == playerCode) {
                    playerExists = true;
                    break;
                }
            }

            if (!gameExists || !playerExists) {
                return false;
            }

            // Verificar la edad del jugador
            Calendar today = Calendar.getInstance();
            Calendar birth = Calendar.getInstance();
            birth.setTimeInMillis(birthdate);
            int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

            if (age < minAge) {
                return false;
            }

            // Crear archivo de descarga
            int downloadCode = getNextCode(2);
            File downloadFile = new File("steam/downloads/download_" + downloadCode + ".stm");
            FileWriter writer = new FileWriter(downloadFile);
            writer.write("[" + new Date() + "]\n");
            writer.write("Download #" + downloadCode + "\n");
            writer.write(playerName + " has downloaded " + gameTitle + " at a price of $ " + gamePrice + "\n");
            writer.close();

            System.out.println("Descarga realizada con éxito.");
            return true;
        } catch (IOException e) {
            System.out.println("Error al descargar el juego: " + e.getMessage());
            return false;
        }
    }

    /*
    formato  games.stm
    int code (4);
    String titulo ();
    char sistema operativo (2)
    int edad minima(4)
    double precio (8)
    int contador downloads(4)
    byte[] image()
    
    
     */
    public void updatePriceFor(int code, double nuevoprice) throws IOException {
        games.seek(0);
        while (games.getFilePointer() < games.length()) {
            int codeTemp = games.readInt();
            long pos = games.getFilePointer();
            games.readUTF();
            games.skipBytes(6);
            long pos2 = games.getFilePointer();
            double oldprice = games.readDouble();
            games.skipBytes(4);
            games.read();
            if (codeTemp == code) {
                games.seek(pos2);
                games.writeDouble(nuevoprice);
                JOptionPane.showMessageDialog(null, "El precio ha cambiado de " + oldprice + " a " + nuevoprice);
            }
        }

    }

    public void reportForClient(int clientCode, String txtFile) {
        try {
            player.seek(0);
            boolean clientFound = false;
            String username = "";
            String password = "";
            String name = "";
            long birthdate = 0;
            int downloads = 0;
            String userType = "";

            while (player.getFilePointer() < player.length()) {
                int code = player.readInt();
                username = player.readUTF();
                password = player.readUTF();
                name = player.readUTF();
                birthdate = player.readLong();
                downloads = player.readInt();
                int imageSize = player.readInt();
                player.skipBytes(imageSize);
                userType = player.readUTF();

                if (code == clientCode) {
                    clientFound = true;
                    break;
                }
            }

            if (clientFound) {
                File reportFile = new File(txtFile);
                FileWriter writer = new FileWriter(reportFile, false); // append en false para recrearlo

                // Formatear fecha de nacimiento
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String birthdateStr = dateFormat.format(new Date(birthdate));

                // Escribir datos del cliente
                writer.write("=== REPORTE DE CLIENTE ===\n");
                writer.write("Codigo: " + clientCode + "\n");
                writer.write("Nombre de usuario: " + username + "\n");
                writer.write("Nombre completo: " + name + "\n");
                writer.write("Fecha de nacimiento: " + birthdateStr + "\n");
                writer.write("Numero de descargas: " + downloads + "\n");
                writer.write("Tipo de usuario: " + userType + "\n");

                writer.close();
                JOptionPane.showMessageDialog(null, "REPORTE CREADO", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDE CREAR REPORTE: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void printGames() {
        try {
            games.seek(0);
            StringBuilder gameList = new StringBuilder();
            gameList.append("=== LISTA DE JUEGOS DISPONIBLES ===\n");
            gameList.append("Código | Título | OS | Edad Mínima | Precio | Descargas\n");

            while (games.getFilePointer() < games.length()) {
                int code = games.readInt();
                String title = games.readUTF();
                char os = games.readChar();
                int minAge = games.readInt();
                double price = games.readDouble();
                int downloadCount = games.readInt();
                int imageSize = games.readInt();
                games.skipBytes(imageSize);

                gameList.append(code).append(" | ").append(title).append(" | ").append(os).append(" | ").append(minAge).append(" | $").append(price).append(" | ").append(downloadCount).append("\n");
            }


            // Mostrar la información en un JOptionPane
            JOptionPane.showMessageDialog(null, gameList.toString(),
                    "Lista de Juegos", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al imprimir juegos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
