/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import semestralni.prace.gui.*;

/**
 *
 * @author Tommzs
 */
public final class Server extends Network{

    int backlog;
    int port;
    ServerSocket server;
    private Socket connection;
    Lobby lobby;

    public Server(Lobby lobby) {
        this.lobby = lobby;
        run = true;
        port = 7755;
        backlog = 100;
        
        
    }

    public void startRunning() {
try {
            this.server = new ServerSocket(port, backlog);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            try {
                while (true) {
                    waitForConnection();
                    setupStreams();
                    lobby.dispose();
                    
                    //START THE THREAD HERE
                    
                    whilePlaying();
                }
            } catch (EOFException ex) {
                showMessage("Server ended the connection!");
            }
            
          
        } catch (IOException ex) {
             ex.printStackTrace();
        }
    }
    
   private void waitForConnection() throws IOException { 
           connection = server.accept();   
           showMessage("Connected to: "+connection.getInetAddress().getHostName());
    }

    @Override
    public void run() {
        startRunning();
    }

    
    
    

   
    
    
    
}
