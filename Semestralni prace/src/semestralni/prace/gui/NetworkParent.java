package semestralni.prace.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import semestralni.prace.Strings;
import semestralni.prace.arrays.Array;

/**
 *
 * @author Tommzs
 */
public abstract class NetworkParent extends JFrame {
    /**
    * Parent for networking and basic frame, reduces duplication
    *
    */
    private JMenuBar menuBar;
    private JMenu game;
    private JMenuItem exitGame;
    private static JLabel statusBar;
    protected Socket connection;
    private static ObjectOutputStream output;
    private ObjectInputStream input;
    private int lines = 10;
    private int columns = 10;
    private static boolean gameRunning;
    protected static boolean ready = false;

    public NetworkParent() throws IOException {
        //Layout setting
        super(Strings.gametitle);
        NetworkParent.gameRunning = true;
        setTitle(Strings.gametitle);
        setSize(2 * columns * 25 + 200, lines * 25 + 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setResizable(false);

        menuBar = new JMenuBar();
        game = new JMenu(Strings.game);
        
        exitGame = new JMenuItem(Strings.exitGame);
        menuBar.add(game);
        
        game.add(exitGame);

        statusBar = new JLabel();
        statusBar.setPreferredSize(new Dimension(100, 16));

        
        exitGame.addActionListener(actionListener);

        add(menuBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
    }
    // Anonymous class - AL for Items in JMenuBar
    protected ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(exitGame)) {/*exit game*/
                System.exit(1);
            }
        }
    };
    
    //Setting up streams
    protected void setupStreams() throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connection.getInputStream());
    }

    //Actions during game - listening for input objects
    protected void whilePlaying() throws IOException {
        String message = Strings.connectedTo + connection.getInetAddress().getHostName();
        DataPack pack;
        setStatusBar(message);
        int[] shot;
        do {
            //Trying to read another player's data and handling them
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
                    if (this instanceof Server) {
                        if (!ready) {
                            GameLayout.setInfo(Strings.opponentIsReady);
                        } else {
                            GameLayout.setInfo(Strings.waitForOpponent);
                        }
                    } else {
                         if (!ready) {
                            GameLayout.setInfo(Strings.bothReady);
                        } else {
                            GameLayout.setInfo(Strings.youCanShoot);
                        }
                    }
                }
                if (pack.win) {
                    GameLayout.finishGame(false);
                }
            } catch (ClassNotFoundException | ClassCastException e) {
                setStatusBar(Strings.invalidData);
                GameLayout.setInfo(Strings.cannotRecieveData);
            }
        } while (gameRunning);
    }

    // Closes connections
    protected void close() {
        setStatusBar(Strings.closingConnection);
        try {
            output.close();
            input.close();
            connection.close();
        } catch (IOException e) {
            setStatusBar(Strings.closingConnectionError);
            GameLayout.setInfo(Strings.closingConnectionError);
            GameLayout.turnButtons(false);
        } catch (NullPointerException e) {
            setStatusBar(Strings.couldNotConnect);
            GameLayout.setInfo(Strings.couldNotConnect);
            GameLayout.turnButtons(false);
        }
    }

    // Status bar text setting method
    public static void setStatusBar(String message) {
        statusBar.setText(message);
    }

    // method for sending shots, creates DataPack and writes it
    public static void sendShot(int x, int y) throws IOException {
        int[] shot = new int[2];
        shot[0] = x;
        shot[1] = y;
        GameLayout.actualShot = shot;
        DataPack shotObj = new DataPack(shot);
        if (GameLayout.winCheck()) {
            shotObj.win = true;
            GameLayout.finishGame(true);
        }
        try {
            output.writeObject(shotObj);
            output.flush();
        } catch (NullPointerException e) {
            String message = Strings.cannotSendData;
            JOptionPane.showMessageDialog(null, message);
        }
    }

    //Method for sending initial array of boat positions
    protected static void sendInitArray() throws IOException {
        DataPack initArray = new DataPack(GameLayout.getBoatArray().getArray());
        try {
            output.writeObject(initArray);
            output.flush();
        } catch (NullPointerException e) {
            String message = Strings.cannotSendData;
            JOptionPane.showMessageDialog(null, message);
        }
    }

    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean gameRunning) {
        NetworkParent.gameRunning = gameRunning;
    }
    
    
}
