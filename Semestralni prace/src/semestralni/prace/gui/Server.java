package semestralni.prace.gui;

import java.awt.BorderLayout;
import java.io.*;
import java.net.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;

/**
 *
 * @author Tommzs
 */
public class Server extends NetworkParent implements Runnable {
    /**
    *
    * Server for the game with inherited methods from NetworkParent
    */
    private ServerSocket server;
    private static GameLayout gameLayout;
    //Constructor
    public Server() throws IOException {
        //Layout setting
        super();
        gameLayout = new GameLayout(true);
        add(gameLayout, BorderLayout.CENTER);
    }

    //Server handling method
    public void startRunning() {
        this.setVisible(true);
        try {
            server = new ServerSocket(7755, 5);
            while (true) {
                try {
                    waitForConnection();
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
        } catch (IOException e) {
            GameLayout.setInfo(Strings.errorIO);
        }
    }

    //Waiting for connection
    private void waitForConnection() throws IOException {
        setStatusBar(Strings.waitingForConnection);
        connection = server.accept();
        setStatusBar(Strings.connected);
        GameLayout.turnButtons(true);
        GameLayout.setInfo(Strings.startPlacingBoats);
    }

    @Override
    public void run() {
        startRunning();
    }

    // Gets you ready after you setup your field with boats
    public static void getReady() {
        Array test = GameLayout.getBoatArrayOpponent();
        if (test == null) {
            GameLayout.setInfo(Strings.opponentIsNotReady);
        } else {
            GameLayout.setInfo(Strings.waitForOpponent);
        }
        setReady(true);
    }

    public static void setReady(boolean ready) {
        Server.ready = ready;
    }
    
}