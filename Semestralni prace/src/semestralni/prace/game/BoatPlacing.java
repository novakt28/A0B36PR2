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
class BoatPlacing extends BasicGameState {

    public BoatPlacing(int state) {
    }

    @Override
    public int getID() {
        return GameStates.BOATPLACING.ordinal();
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("This is PLAY screen", 100, 100);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        
    }
    
}
