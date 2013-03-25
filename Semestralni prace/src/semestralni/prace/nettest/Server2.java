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

public class Server2 implements Runnable
{
   private ServerSocket serverSocket;
   Socket server;
   ObjectInputStream in;
   ObjectOutputStream out;
   boolean listen;
   boolean connected;
   Array array;
   int port;
   int backlog;
   
   public Server2() throws IOException
   {
      listen = true;
      connected = false;
      this.port = 7755;
      this.backlog = 100;
      this.serverSocket = new ServerSocket(port, backlog);
      serverSocket.setSoTimeout(100000);
      
   }

    public void pushArray(Array array) throws IOException {
        out.writeObject(array);
    }
    
    public void startListening() throws IOException {
        
        while (listen) {
            try {
                array = (Array) in.readObject();
            } catch (IOException ex) {
                Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Server2.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      while(!connected)
      {
         try
         {
             //server socket setting???
            server = serverSocket.accept();
            connected = true;
         }catch(SocketTimeoutException s)
         {
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
         
      }
      JOptionPane.showMessageDialog(null, "CONNECTED!!");
   }
   
}