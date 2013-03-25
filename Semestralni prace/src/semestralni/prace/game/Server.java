/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.game;

import java.io.*;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import semestralni.prace.arrays.Array;
/**
 *
 * @author Tommzs
 */
class Server extends BasicGameState {
    Image background;
    Image waitForConnection;
    

    public Server(int state) {
      

    }

    @Override
    public int getID() {
        return GameStates.SERVER.ordinal();
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

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
         
          
    }
    
}
