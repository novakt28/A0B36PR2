
package semestralni.prace.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import semestralni.prace.*;
/*@author Tommzs*/


public class Game extends StateBasedGame{
    
    public static final String gamename = Strings.gametitle;
    public static final int client = GameStates.CLIENT.ordinal();
    public static final int boatPlacing = GameStates.BOATPLACING.ordinal();
    public static final int play = GameStates.PLAY.ordinal();
    public static final int server = GameStates.SERVER.ordinal();
    String ip = null;
    boolean isClient;
    
    public Game() throws UnknownHostException, IOException {
        super(gamename);
        this.addState(new Client(client, ip));
        this.addState(new BoatPlacing(boatPlacing));
        this.addState(new Play(play));
        this.addState(new Server(server));
        isClient = false;
        }
    
    public Game(String ip) throws UnknownHostException, IOException {
        super(gamename);
        this.addState(new Client(client, ip));
        this.addState(new BoatPlacing(boatPlacing));
        this.addState(new Play(play));
        this.addState(new Server(server));
        this.ip = ip;
        isClient = true;
        }
   
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(client).init(gc, this);
        this.getState(boatPlacing).init(gc, this);
        this.getState(play).init(gc, this);
        this.getState(server).init(gc, this);
        
        if(isClient) {
            this.enterState(client);
        } else {
            this.enterState(server);
        }
    }

    public String getIp() {
        return ip;
    }
     
    
    public void start(){
      AppGameContainer appgc;
      try{
          appgc = new AppGameContainer(this);
          appgc.setDisplayMode(1300, 600, false);
          appgc.start();
         
      } catch(SlickException e){
             e.printStackTrace();
      }
    }
}
