/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import semestralni.prace.*;
/**
 *
 * @author Tommzs
 */
class ConnectClient extends BasicGameState {
    Image background;
    
    public ConnectClient(int state) {
        
    }

    @Override
    public int getID() {
        return GameStates.CONNECTCLIENT.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("semestralni/prace/res/background.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background, 0, 0);
        g.drawString("This is PLAY screen", 100, 100);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
    }
    
}
