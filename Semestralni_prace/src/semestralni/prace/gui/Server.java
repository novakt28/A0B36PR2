package semestralni.prace.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import semestralni.prace.*;

/**
 *
 * @author Tommzs
 */
public class Server extends JFrame implements Runnable {

    private static GameLayout gameLayout;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem newGame, exitGame;
    private JLabel statusBar;
    private Socket connection;
    private static ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private int lines = Constants.X;
    private int columns = Constants.Y;
    private boolean gameRunning;
    //ActionListener for JMenuBar 
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(newGame)) {/*clean game*/

            }
            if (evt.getSource().equals(exitGame)) {/*exit game*/

            }
        }
    };

    //Constructor
    public Server() {
        //Layout setting
        super("NETBoats v0.000001");
        this.gameRunning = true;

        setTitle("NETBoats v0.000001");
        setSize(2* columns * 25 + 200, lines * 25 + 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setResizable(false);


        menuBar = new JMenuBar();
        game = new JMenu("Game");
        newGame = new JMenuItem("New Game");
        exitGame = new JMenuItem("Exit Game");
        menuBar.add(game);
        game.add(newGame);
        game.add(exitGame);

        statusBar = new JLabel();
        statusBar.setPreferredSize(new Dimension(100, 16));

        gameLayout = new GameLayout(false);
        newGame.addActionListener(actionListener);
        exitGame.addActionListener(actionListener);

        add(menuBar, BorderLayout.NORTH);
        add(gameLayout, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
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
                    setStatusBar("Client terminated connection");
                    Util.writeToFile(this.getClass().getName() + ": " + e);
                } catch (IOException e) {
                    setStatusBar("Sending/Recieving data error");
                    Util.writeToFile(this.getClass().getName() + ": " + e);
                } finally {
                    close();
                }
            }
        } catch (IOException e) {
            Util.writeToFile(this.getClass().getName()+": "+e);
        }
    }
    
    //Waiting for connection
    private void waitForConnection() throws IOException {
        setStatusBar("Waiting for connection...");
        connection = server.accept();
        setStatusBar("Connected to: " + connection.getInetAddress().getHostName());
    }

    //Setting up streams
    private void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());


    }
    
    //Actions during game
    private void whilePlaying() throws IOException {
        String message = "Connected to: "+connection.getInetAddress().getHostName();
        setStatusBar(message);
        int[] shot;
        do {
            //Try to read another player's shot
            try {
                shot = (int[]) input.readObject();
                // CO DELAT S PRICHOZIMY DATY
            } catch (ClassNotFoundException e) {
                setStatusBar("Invalid data");
                Util.writeToFile(this.getClass().getName()+": "+e);
            }
        } while (gameRunning);
    }

    //Closing connection
    private void close() {
        setStatusBar("Closing Connection...");
        try {
            output.close(); // NullPointerException errors
            input.close();
            connection.close();
        } catch (IOException e) {
             setStatusBar("Closing connection error...");
            Util.writeToFile(this.getClass().getName()+": "+e);
        }

    }

    // Status bar text setting method
    private void setStatusBar(String message) {
        statusBar.setText(message);
    }

     //Sending data
    public static void sendShot(/*object*/) throws IOException {
        
        try {
           // output.writeObject(/*object*/);
        } catch (NullPointerException e) {
            String message = "Cannot send data!";
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