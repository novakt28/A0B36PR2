package semestralni.prace.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;

/**
 *
 * @author Tommzs
 */
public class Client extends JFrame implements Runnable {
    // Basic variable setting

    private static GameLayout gameLayout;
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem mainMenu, exitGame;
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
            if (evt.getSource().equals(mainMenu)) {
               dispose();
               Menu menu = new Menu();
               menu.setVisible(true);
            }
            if (evt.getSource().equals(exitGame)) {/*exit game*/
                System.exit(1);
            }
        }
    };

    //Constructor
    public Client(String ip) throws IOException {
        //Layout setting
        super(Strings.gametitle);
        this.ip = ip;
        this.gameRunning = true;

        setTitle(Strings.gametitle);
        setSize(2 * columns * 25 + 200, lines * 25 + 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setResizable(false);


        menuBar = new JMenuBar();
        game = new JMenu(Strings.game);
        mainMenu = new JMenuItem(Strings.newGame);
        exitGame = new JMenuItem(Strings.exitGame);
        menuBar.add(game);
        game.add(mainMenu);
        game.add(exitGame);

        statusBar = new JLabel();
        statusBar.setPreferredSize(new Dimension(100, 16));

        gameLayout = new GameLayout(false);

        mainMenu.addActionListener(actionListener);
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
            Util.writeToFile(this.getClass().getName() + ": " + e);
        } catch (IOException e) {
            setStatusBar(Strings.errorIO);
            Util.writeToFile(this.getClass().getName() + ": " + e);
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

    //Actions during game
    private void whilePlaying() throws IOException {
        String message = Strings.connectedTo + connection.getInetAddress().getHostName();
        DataPack pack;
        setStatusBar(message);
        int[] shot;
        do {
            //Try to read another player's shot
            try {
                pack = (DataPack) input.readObject();
                if (pack.dec) {
                    shot = pack.shot;
                    GameLayout.checkShot(shot);
                }
                if (!pack.dec) {
                    Array get = new Array();
                    get.setArray(pack.boatArray);
                    GameLayout.setBoatArrayOpponent(get);
                    GameLayout.setInfo(Strings.youCanShoot);
                }
                if (pack.win) {
                    GameLayout.finishGame(false);
                }
            } catch (ClassNotFoundException | ClassCastException e) {
                setStatusBar(Strings.invalidData);
                Util.writeToFile(this.getClass().getName() + ": " + e);
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
            GameLayout.setInfo(Strings.closingConnectionError);
            GameLayout.turnButtons(false);
            Util.writeToFile(this.getClass().getName() + ": " + e);
        
        } catch (NullPointerException e) {
            setStatusBar(Strings.couldNotConnect);
            GameLayout.setInfo(Strings.couldNotConnect);
            GameLayout.turnButtons(false);
            Util.writeToFile(this.getClass().getName() + ": " + e);
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
        setStatusBar(Strings.connectedTo + connection.getInetAddress().getHostName());
        gameLayout.turnButtons(true);
        GameLayout.setInfo(Strings.startPlacingBoats);
    }

    //Sending data
    public static void sendShot(int x, int y) throws IOException {
        int[] shot = new int[2];
        shot[0] = x;
        shot[1] = y;
        GameLayout.actualShot = shot;
        DataPack shotObj = new DataPack(shot);
        if (GameLayout.winCheck()) {
            shotObj.win = true;
        }
        try {
            output.writeObject(shotObj);
            output.flush();
        } catch (NullPointerException e) {
            String message = Strings.cannotSendData;
            JOptionPane.showMessageDialog(null, message);
        }
        if (GameLayout.winCheck()) {
            GameLayout.finishGame(true);
        }}


    public static void sendInitArray() throws IOException {
        DataPack initArray = new DataPack(GameLayout.getBoatArray().getArray());
        try {
            output.writeObject(initArray);
            output.flush();
        } catch (NullPointerException e) {
            String message = Strings.cannotSendData;
            JOptionPane.showMessageDialog(null, message);
        }
    }

    

    @Override
    public void run() {
        startRunning();
    }

    public static void getReady() {
        if (GameLayout.getBoatArrayOpponent() == null) {
            GameLayout.setInfo(Strings.opponentIsNotReady);
        } else {
            GameLayout.setInfo(Strings.youCanShoot);
        }
    }
}