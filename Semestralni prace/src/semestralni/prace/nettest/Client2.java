/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.nettest;

/**
 *
 * @author Tommzs
 */
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import semestralni.prace.arrays.Array;

public class Client2 implements Runnable{

    String serverIP;
    int port = 7755;
    Socket client;
    OutputStream output;
    ObjectOutputStream outToServer;
    ObjectInputStream input;
    Array array;
    boolean listen = true;

    public Client2(String serverIP) throws IOException {
        this.serverIP = serverIP;

    }

    @Override
    public void run(){
        try {
            tryToConnect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tryToConnect() throws UnknownHostException, IOException {
        output = client.getOutputStream();
        outToServer = new ObjectOutputStream(output);
        input = new ObjectInputStream(client.getInputStream());
        client = new Socket(serverIP, port);
        JOptionPane.showMessageDialog(null, "CONNECTED!!");
    }

    public void startListening() throws IOException {
        
        while (listen) {
            try {
                array = (Array) input.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundEx");
            }
        }
    }

    public void pushArray(Array array) throws IOException {
        outToServer.writeObject(array);
    }

    public void closeConnection() throws IOException {
        listen = false;
        outToServer.close();
        output.close();
        input.close();
        client.close();
    }
}