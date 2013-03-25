/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.net;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import semestralni.prace.arrays.Array;
import semestralni.prace.gui.*;

/**
 *
 * @author Tommzs
 */
public abstract class Network implements Runnable{
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
            } catch (NullPointerException ex){
                showMessage("ERROR: Closing closed...");
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

    @Override
    public abstract void run();
     
}
