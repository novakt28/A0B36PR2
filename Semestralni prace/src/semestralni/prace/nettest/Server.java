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

public class Server extends Thread
{
   private ServerSocket serverSocket;
   Socket server;
   ObjectInputStream in;
   ObjectOutputStream out;
   boolean listen;
   boolean connected;
   Array array;
   
   public Server(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
      listen = true;
      connected = false;
   }

    public void pushArray(Array array) throws IOException {
        out.writeObject(array);
    }
    
    public void startListening() throws IOException {
        
        while (listen) {
            try {
                array = (Array) in.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundEx");
            }
        }
    }

    public void closeConnection() throws IOException {
        listen = false;
        in.close();
        out.close();
        server.close();
    }
   
    @Override
   public void run()
   {
        try {
            in = new ObjectInputStream(server.getInputStream());
            out = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      while(!connected)
      {
         try
         {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            server = serverSocket.accept();
            System.out.println("Just connected to "+ server.getRemoteSocketAddress());
            connected = false;
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
   
}