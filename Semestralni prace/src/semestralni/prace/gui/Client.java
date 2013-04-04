package semestralni.prace.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import semestralni.prace.*;
 
/**
 *
 * @author Tommzs
 */
public class Client extends JFrame implements Runnable {
    // Basic variable setting
    private static GameLayout gameLayout;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem newGame, exitGame;
    private JLabel statusBar;
    private Socket connection;
    private static ObjectOutputStream output;
    private ObjectInputStream input;
    private String ip;
    private int lines = Constants.X;
    private int columns = Constants.Y;
    
    private boolean gameRunning;
    //ActionListener for JMenuBar 
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if(evt.getSource().equals(newGame)) {/*clean game*/}
            if(evt.getSource().equals(exitGame)) {/*exit game*/}
        }
    };
 
    //Constructor
    public Client(String ip) throws IOException {
        //Layout setting
        super(Strings.gametitle);
        this.ip = ip;
        this.gameRunning = true;

        setTitle(Strings.gametitle);
        setSize(2*columns * 25 + 50, lines * 25 + 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo( null );
        setResizable(false);
 
        
        menuBar = new JMenuBar();
        game = new JMenu(Strings.game);
        newGame = new JMenuItem(Strings.newGame);
        exitGame = new JMenuItem(Strings.exitGame);
        menuBar.add(game);
        game.add(newGame);
        game.add(exitGame);
        
        statusBar = new JLabel();
        statusBar.setPreferredSize(new Dimension(100,16));
        
        gameLayout = new GameLayout(false);
        
        newGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);

        add(menuBar, BorderLayout.NORTH);
        add(gameLayout, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
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
            Util.writeToFile(this.getClass().getName()+": "+e);
        } catch (IOException e) {
            setStatusBar(Strings.errorIO);
            Util.writeToFile(this.getClass().getName()+": "+e);
        } finally {
            close();
        }
    }
 
    //Setting up streams
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
 
 
    }
 
    //Actions durring game
    private void whilePlaying() throws IOException {
        String message = Strings.connectedTo+connection.getInetAddress().getHostName();
        int[] shot;
        setStatusBar(message);
 
        do {
            //Try to read another player's shot
            try {
                shot = (int[]) input.readObject();
                gameLayout.repaint();
                System.out.println(Strings.shot+Arrays.toString(shot));
                setStatusBar(message);
            } catch (ClassNotFoundException e) {
                setStatusBar(Strings.invalidData);
                Util.writeToFile(this.getClass().getName()+": "+e);
            }
        } while (gameRunning);
    }
 
    // Close connetion
    private void close() {
        setStatusBar(Strings.closingConnection);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {
            setStatusBar(Strings.closingConnectionError);
            Util.writeToFile(this.getClass().getName()+": "+e);
        }
 
    }
 
    // Status bar text setting method
    public void setStatusBar(String message) {
        statusBar.setText(message);
    }
 
    //Connecting to server
    private void connectToServer() throws IOException {
        setStatusBar(Strings.attemptingConnection);
        connection = new Socket(InetAddress.getByName(ip), 7755);
        setStatusBar(Strings.connectedTo+ connection.getInetAddress().getHostName());
        gameLayout.turnButtons(true);
    }
 
    //Sending data
    public static void sendShot(/*object*/) throws IOException {
        
        try {
           // output.writeObject(/*object*/);
        } catch (NullPointerException e) {
            String message = Strings.cannotSendData;
            JOptionPane.showMessageDialog(null, message);
        }
        output.flush();
        // TURN OF SENDING SHOTS
    }
 
    
    @Override
    public void run() {
        startRunning();
    }
}