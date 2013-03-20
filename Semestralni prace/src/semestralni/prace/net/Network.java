/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import semestralni.prace.arrays.Array;

/**
 *
 * @author Tommzs
 */
public abstract class Network {
    private ObjectOutputStream output;
    private ObjectInputStream input; 
    private Socket connection;
    boolean run;
    Array array;
    
    void showMessage(String text) {
         JOptionPane.showMessageDialog(null, text);
    }
     
     public void pushArray(Array array) throws IOException{
        output.writeObject(array);
        output.flush();
    }
     
     public void closeConnection() {
        try {
            showMessage("Closing connection...");
            output.close();
            input.close();
            connection.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        
        input = new ObjectInputStream(connection.getInputStream());
    }
     
     public void whilePlaying() throws IOException {
        run = true;
        do{
           try{
               array = (Array) input.readObject();
           }catch(ClassNotFoundException ex){
               showMessage("Input error");
           }
        }while(run);
    }
     
     public abstract void startRunning() throws IOException;
     
}
