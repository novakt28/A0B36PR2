/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import semestralni.prace.arrays.Array;
/**
 *
 * @author Tommzs
 */
class Client extends BasicGameState {
    Image background;
    Image waitForConnection;
    
    
    Array array;
    Socket server;
    String ip;
    ObjectOutputStream clientOutputStream;
    ObjectInputStream clientInputStream;
    public Client(int state, String ip) throws UnknownHostException, IOException {
        this.ip = ip;
        server = new Socket(this.ip,7755);
        clientOutputStream = new ObjectOutputStream(server.getOutputStream());
        clientInputStream = new ObjectInputStream(server.getInputStream());
    
    }

    @Override
    public int getID() {
        return GameStates.CLIENT.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("semestralni\\prace\\res\\back.png");
        waitForConnection = new Image("semestralni\\prace\\res\\waitforconnection.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        g.drawImage(waitForConnection, 500, 250);
    }

    
    public void pushArray(Array array) throws IOException{
        clientOutputStream.writeObject(array);
    }
    
    public void close() throws IOException{
    clientOutputStream.close();
    clientInputStream.close();
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        try {         
            array = (Array)clientInputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
