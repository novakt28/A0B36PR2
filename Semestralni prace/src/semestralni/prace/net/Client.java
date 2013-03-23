package semestralni.prace.net;

import java.io.*;
import java.net.*;
/**
 *
 * @author Tommzs
 */
public final class Client  extends Network{
    private String serverIP;
    private Socket connection;
    

    public Client(String serverIP) throws IOException {
        this.serverIP = serverIP;
        
    }
    
    @Override
    public void startRunning() throws IOException{
        try {
            connectToServer();
            setupStreams();
            whilePlaying();
        } catch (EOFException ex) {
            showMessage("Client terminated connetion");
        } finally {
            closeConnection();
        }
    }

    private void connectToServer() throws IOException {
        connection = new Socket(InetAddress.getByName(serverIP), 7755);
        showMessage("Connected to: "+connection.getInetAddress().getHostName());
    }

   
    
    
    
    
}
