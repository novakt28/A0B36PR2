
package semestralni.prace.game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import semestralni.prace.*;
/*@author Tommzs*/


public class Game extends StateBasedGame{
    
    public static final String gamename = Strings.gametitle;
    public static final int boatPlacing = GameStates.BOATPLACING.ordinal();
    public static final int play = GameStates.PLAY.ordinal();

    public Game(String gamename) {
        super(gamename);
        }
   
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
    }
     
    
    public static void main(String[] args){
      AppGameContainer appgc;
      try{
          appgc = new AppGameContainer(new Game(gamename));
          appgc.setDisplayMode(1280, 600, false);
          appgc.start();
      } catch(SlickException e){
             e.printStackTrace();
      }
    }
}
