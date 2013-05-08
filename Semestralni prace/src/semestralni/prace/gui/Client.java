package semestralni.prace.gui;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import semestralni.prace.*;

/**
 *
 * @author Tommzs
 */
public class Client extends NetworkParent implements Runnable {
    /**
    *
    * Client for the game with inherited methods from NetworkParent
    */
    private String ip;
    private static GameLayout gameLayout;
    
    //Constructor
    public Client(String ip) throws IOException {
        super();
        this.ip = ip;
        gameLayout = new GameLayout(false);
        add(gameLayout, BorderLayout.CENTER);
    }

    //Client handling method
    public void startRunning() {
        this.setVisible(true);
        try {
            connectToServer();
            setupStreams();
            whilePlaying();
        } catch (EOFException e) {
            setStatusBar(Strings.clientTerminated);
            GameLayout.setInfo(Strings.errorIO);
        } catch (IOException e) {
            setStatusBar(Strings.invalidData);
            GameLayout.setInfo(Strings.errorIO);
        } finally {
            close();
        }
    }

    //Connecting to server
    private void connectToServer() throws IOException {
        setStatusBar(Strings.attemptingConnection);
        connection = new Socket(InetAddress.getByName(ip), 7755);
        setStatusBar(Strings.connectedTo + connection.getInetAddress().getHostName());
        GameLayout.turnButtons(true);
        GameLayout.setInfo(Strings.startPlacingBoats);
    }

    @Override
    public void run() {
        startRunning();
    }
    
     // Gets you ready after you setup your field with boats
     public static void getReady() {
        if (GameLayout.getBoatArrayOpponent() == null) {
            GameLayout.setInfo(Strings.opponentIsNotReady);
        } else {
            GameLayout.setInfo(Strings.youCanShoot);
        }
    }
}