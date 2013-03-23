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
import semestralni.prace.arrays.Array;

public class Client implements Runnable{

    String serverIP;
    int port = 7777;
    Socket client;
    OutputStream output;
    ObjectOutputStream outToServer;
    ObjectInputStream input;
    Array array;
    boolean listen = true;

    public Client(String serverIP) throws IOException {
        this.serverIP = serverIP;

    }

    @Override
    public void run(){
        try {
            tryToConnect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tryToConnect() throws UnknownHostException, IOException {
        System.out.println("Connecting to " + serverIP + " on port " + port);
        client = new Socket(serverIP, port);
        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        output = client.getOutputStream();
        outToServer = new ObjectOutputStream(output);
    }

    public void startListening() throws IOException {
        input = new ObjectInputStream(client.getInputStream());
        while (listen) {
            try {
                array = (Array) input.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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