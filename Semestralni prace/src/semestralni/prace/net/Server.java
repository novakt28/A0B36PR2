/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tommzs
 */
public final class Server extends Network{

    int backlog;
    int port;
    ServerSocket server;
    private Socket connection;

    public Server() {
        run = true;
        port = 7755;
        backlog = 100;
        try {
            this.server = new ServerSocket(port, backlog);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void startRunning() {

        try {
            
            try {
                while (true) {
                    waitForConnection();
                    setupStreams();
                    whilePlaying();
                }
            } catch (EOFException ex) {
                showMessage("Server ended the connection!");
            }finally{
               closeConnection();
           }
            
          
        } catch (IOException ex) {
             ex.printStackTrace();
        }
    }
    
   private void waitForConnection() throws IOException { 
           connection = server.accept();   
           showMessage("Connected to: "+connection.getInetAddress().getHostName());
    }

    
    
    

   
    
    
    
}
