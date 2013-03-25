package semestralni.prace.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import semestralni.prace.gui.Lobby_Connect;
/**
 *
 * @author Tommzs
 */
public class Client  extends Network{
    private String serverIP;
    private Socket connection;
    Lobby_Connect lobby;

    public Client(String serverIP, Lobby_Connect lobby) throws IOException {
        this.lobby = lobby;
        this.serverIP = serverIP;
        
    }
    
    
    public void startRunning() throws IOException{
        try {
            connectToServer();
            setupStreams();
            lobby.dispose();
            
            //START THE THREAD FOR GAME
            
            whilePlaying();
        } catch (EOFException ex) {
            showMessage("Client terminated connetion");
        }
    }

    private void connectToServer() throws IOException {
        connection = new Socket(InetAddress.getByName(serverIP), 7755);
        showMessage("Connected to: "+connection.getInetAddress().getHostName());
    }

    @Override
    public void run() {
        try {
            startRunning();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
    
    
    
}
