package semestralni.prace.gui;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;
import semestralni.prace.boat.*;
import semestralni.prace.gui.listeners.*;

/**
 *
 * @author Tommzs
 */
public final class GameLayout extends JPanel implements Serializable {
    /**
    * The main Game Layout - buttons, game fields, handles almost everything.
    * Mostly static to easy access and modify from other classes.
    */
    private static JButton[][] boatButtons;
    private static JButton[][] shotButtons;
    private static JButton boat1button;
    private static JButton boat2button;
    private static JButton boat3button;
    private static JButton boat4button;
    private static JButton up;
    private static JButton down;
    private static JButton left;
    private static JButton right;
    private static JButton rotate;
    private static JButton start;
    private static JLabel info;
    private static JLabel boat1label;
    private static JLabel boat2label;
    private static JLabel boat3label;
    private static JLabel boat4label;
    private static int x = 0;
    private static int y = 0;
    public static int[] actualShot;
    private BufferedImage boat1icon;
    private BufferedImage boat2icon;
    private BufferedImage boat3icon;
    private BufferedImage boat4icon;
    private static BoatArrayListener boatArrayListener;
    private MoveBoatsButtonsListener moveBoatsButtonsListener;
    private static ShotArrayListener shotArrayListener;
    private static BoatParent actualBoat;
    private static BoatParent boat1;
    private static BoatParent boat2;
    private static BoatParent boat3;
    private static BoatParent boat4;
    private static Array boatArray;
    private static Array boatArrayOpponent;
    private static BoatLocalizator[][] localizatorArray;
    private static boolean server;
    private int buttonSize;
    private int columns = 10;
    private int lines = 10;
    private static boolean yourTurn;
    private static int boatPartsAlive = 25;

    //Constructor
    public GameLayout(boolean server) throws IOException {
        setLayout(null); /* Using null layout will allow me to set exact bounds
         * - usefull for this application with fixed components */
        boatButtons = new ButtonXY[lines][columns];
        shotButtons = new ButtonXY[lines][columns];
        buttonSize = 25;
        GameLayout.setServer(server);
        boatArray = new Array();
        localizatorArray = new BoatLocalizator[columns][lines];
        boat1label = new JLabel();
        boat1 = new Boat1("boat1", 0, 0, 2, boat1label);
        boat1label.setText("" + boat1.getNumberOfBoats());
        boat2label = new JLabel();
        boat2 = new Boat2("boat2", 0, 0, 4, boat2label);
        boat2label.setText("" + boat2.getNumberOfBoats());
        boat3label = new JLabel();
        boat3 = new Boat3("boat3", 0, 0, 4, boat3label);
        boat3label.setText("" + boat3.getNumberOfBoats());
        boat4label = new JLabel();
        boat4 = new Boat4("boat4", 0, 0, 5, boat4label);
        boat4label.setText("" + boat4.getNumberOfBoats());
        setUpListeners();
        fillGameLayout();
        if (GameLayout.isServer()) {
            yourTurn = false;
        } else {
            yourTurn = true;
        }
        turnButtons(false);

    }
    
    //Sets up listeners
    private void setUpListeners() {
        boatArrayListener = new BoatArrayListener();
        moveBoatsButtonsListener = new MoveBoatsButtonsListener();
        shotArrayListener = new ShotArrayListener();
    }
    
    //Fills panel with gui parts
    private void fillGameLayout() throws IOException {
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j] = new ButtonXY(i, j);
                boatButtons[i][j].setBounds(125 + 25 * i, 30 + 25 * j, buttonSize, buttonSize);
                boatButtons[i][j].setBackground(Color.white);
                boatButtons[i][j].addActionListener(boatArrayListener);
                add(boatButtons[i][j]);
            }
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j] = new ButtonXY(i, j);
                shotButtons[i][j].setBounds(400 + 25 * i, 30 + 25 * j, buttonSize, buttonSize);
                shotButtons[i][j].setBackground(Color.white);
                add(shotButtons[i][j]);
            }
        }
        boat1icon = ImageIO.read(getClass().getResource("/resources/boat2.png"));
        boat2icon = ImageIO.read(getClass().getResource("/resources/boat3.png"));
        boat3icon = ImageIO.read(getClass().getResource("/resources/boat4.png"));
        boat4icon = ImageIO.read(getClass().getResource("/resources/boat5.png"));

        boat1button = new JButton(new ImageIcon(boat1icon));
        boat2button = new JButton(new ImageIcon(boat2icon));
        boat3button = new JButton(new ImageIcon(boat3icon));
        boat4button = new JButton(new ImageIcon(boat4icon));

        boat1button.setName("boat1");
        boat2button.setName("boat2");
        boat3button.setName("boat3");
        boat4button.setName("boat4");

        boat1label.setBounds(31, 62, 10, 10);
        boat2label.setBounds(86, 62, 10, 10);
        boat3label.setBounds(31, 132, 10, 10);
        boat4label.setBounds(86, 132, 10, 10);

        boat1button.setBounds(10, 10, 50, 50);
        boat2button.setBounds(65, 10, 50, 50);
        boat3button.setBounds(10, 80, 50, 50);
        boat4button.setBounds(65, 80, 50, 50);

        boat1button.addActionListener(moveBoatsButtonsListener);
        boat2button.addActionListener(moveBoatsButtonsListener);
        boat3button.addActionListener(moveBoatsButtonsListener);
        boat4button.addActionListener(moveBoatsButtonsListener);

        add(boat1button);
        add(boat2button);
        add(boat3button);
        add(boat4button);
        add(boat1label);
        add(boat2label);
        add(boat3label);
        add(boat4label);

        up = new JButton(Strings.up);
        down = new JButton(Strings.down);
        left = new JButton(Strings.left);
        right = new JButton(Strings.right);
        start = new JButton(Strings.start);
        rotate = new JButton(Strings.rotate);

        up.setName("up");
        down.setName("down");
        left.setName("left");
        right.setName("right");
        start.setName("start");
        rotate.setName("rotate");

        up.setMargin(new Insets(1, 1, 1, 1));
        down.setMargin(new Insets(1, 1, 1, 1));
        left.setMargin(new Insets(1, 1, 1, 1));
        right.setMargin(new Insets(1, 1, 1, 1));
        start.setMargin(new Insets(1, 1, 1, 1));
        rotate.setMargin(new Insets(1, 1, 1, 1));

        up.setBounds(45, 170, 35, 35);
        down.setBounds(45, 207, 35, 35);
        left.setBounds(8, 207, 35, 35);
        right.setBounds(82, 207, 35, 35);
        start.setBounds(8, 244, 109, 35);
        rotate.setBounds(8, 170, 35, 35);

        up.addActionListener(moveBoatsButtonsListener);
        down.addActionListener(moveBoatsButtonsListener);
        left.addActionListener(moveBoatsButtonsListener);
        right.addActionListener(moveBoatsButtonsListener);
        start.addActionListener(moveBoatsButtonsListener);
        rotate.addActionListener(moveBoatsButtonsListener);

        add(up);
        add(down);
        add(left);
        add(right);
        add(start);
        add(rotate);

        info = new JLabel();
        info.setBounds(140, 0, 700, 30);
        setInfo(Strings.waitingForConnection);

        add(info);
    }

    //Turns on/off all buttons
    public static void turnButtons(boolean on) {
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j].setEnabled(on);
            }
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j].setEnabled(on);
            }
        }
        boat1button.setEnabled(on);
        boat2button.setEnabled(on);
        boat3button.setEnabled(on);
        boat4button.setEnabled(on);
        up.setEnabled(on);
        down.setEnabled(on);
        left.setEnabled(on);
        right.setEnabled(on);
        rotate.setEnabled(on);
        start.setEnabled(on);
    }
    //Checks if someone won
    public static boolean winCheck() {
        if(boatPartsAlive == 0) {
            GameLayout.setBoatPartsAlive(25);
            return true;
        }
        return false;
    }

    // Returns if server or client
    public static boolean isServer() {
        return server;
    }

    public static void setServer(boolean server) {
        GameLayout.server = server;
    }
    
    // Sets text into info label
    public static void setInfo(String text) {
        info.setText(text);
    }

    public static void setBoat(BoatParent boat) {
        GameLayout.actualBoat = boat;
    }
    
    public static void setBoatArrayOpponent(Array boatArrayOpponent) {
        GameLayout.boatArrayOpponent = boatArrayOpponent;
    }

    public static Array getBoatArrayOpponent() {
        return boatArrayOpponent;
    }

    public static BoatParent getBoat() {
        return actualBoat;
    }

    public static Array getBoatArray() {
        return boatArray;
    }

    public static int getActualX() {
        return x;
    }

    public static int getActualY() {
        return y;
    }

    public static void setActualY(int y) {
        GameLayout.y = y;
    }

    public static void setActualX(int x) {
        GameLayout.x = x;
    }

    public static void setBoatArray(Array array) {
        GameLayout.boatArray = array;
    }

    public static BoatLocalizator[][] getLocalizatorArray() {
        return localizatorArray;
    }

    public static void setLocalizatorArray(BoatLocalizator[][] localizatorArray) {
        GameLayout.localizatorArray = localizatorArray;
    }

    public static BoatParent getBoat1() {
        return boat1;
    }

    public static BoatParent getBoat2() {
        return boat2;
    }

    public static BoatParent getBoat3() {
        return boat3;
    }

    public static BoatParent getBoat4() {
        return boat4;
    }

    // Repaint boat buttons after adding/moving/rotating boat
    public static void repaintGameArray() {
        boolean[][] array = boatArray.getArray();
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                if (array[i][j] == true) {
                    boatButtons[i][j].setBackground(Color.CYAN);
                } else {
                    boatButtons[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    // Updates localization array after every add/move/rotate
    public static void localizationArrayUpdate(BoatParent boat) {
        for (int i = 0; i < GameLayout.getLocalizatorArray().length; i++) {
            for (int j = 0; j < GameLayout.getLocalizatorArray().length; j++) {
                if (GameLayout.getBoatArray().getArray()[i][j] == true && GameLayout.getLocalizatorArray()[i][j] == null) {
                    GameLayout.getLocalizatorArray()[i][j] = new BoatLocalizator(x - i, y - j, boat);
                }
            }
        }
    }

    //Sets game on, turns on buttons for shooting
    public static void setGameOn() throws IOException {
        turnPlaceButtons(false);
        if (isServer()) {
            Server.getReady();
            Server.sendInitArray();
        } else {
            Client.getReady();
            Client.sendInitArray();
        }
        for (int i = 0; i < shotButtons.length; i++) {
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j].addActionListener(shotArrayListener);
            }
        }
    }
    
    // Turns placing buttons off/on
    public static void turnPlaceButtons(boolean on) {
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j].removeActionListener(boatArrayListener);
            }
        }
        boat1button.setEnabled(on);
        boat2button.setEnabled(on);
        boat3button.setEnabled(on);
        boat4button.setEnabled(on);
        up.setEnabled(on);
        down.setEnabled(on);
        left.setEnabled(on);
        right.setEnabled(on);
        rotate.setEnabled(on);
        start.setEnabled(on);
    }

    // Checks if the shot is hit or miss
    static void checkShot(int[] shot) throws IOException {
        boolean hit = boatArray.getArray()[shot[0]][shot[1]];
        if (hit) {
            boatButtons[shot[0]][shot[1]].setBackground(Color.RED);
        } else {
            boatButtons[shot[0]][shot[1]].setBackground(Color.GRAY);
        }
        setInfo(Strings.yourTurn);
        setYourTurn(true);
    }
    
  
    // Finishes game after someone won
    static void finishGame(boolean win) {
        String message;
        if(win) {
            message = Strings.win;
        } else {
            message = Strings.lost;
        }
        GameLayout.setInfo(message);
        GameLayout.turnButtons(false);
        }

    public static int getBoatPartsAlive() {
        return boatPartsAlive;
    }

    public static void setBoatPartsAlive(int boatPartsAlive) {
        GameLayout.boatPartsAlive = boatPartsAlive;
    }

    public static boolean isYourTurn() {
        return yourTurn;
    }

    public static void setYourTurn(boolean yourTurn) {
        GameLayout.yourTurn = yourTurn;
    }
    
}