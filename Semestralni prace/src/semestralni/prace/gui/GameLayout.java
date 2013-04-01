package semestralni.prace.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import semestralni.prace.*;

 
/**
 *
 * @author Tommzs
 */
public final class GameLayout extends JPanel implements Serializable {
 
    private JButton[][] boatButtons;
    private JButton[][] shotButtons;
    private JButton boat1;
    private JButton boat2;
    private JButton boat3;
    private JButton boat4;
    private JButton up;
    private JButton down;
    private JButton left;
    private JButton right;
    private JButton rotate;
    private JButton start;
    private JLabel info;
    private BufferedImage boat1icon;
    private BufferedImage boat2icon;
    private BufferedImage boat3icon;
    private BufferedImage boat4icon;

    
    
    private boolean yourTurn = true;  
    private int buttonSize;
    private int columns = Constants.X;
    private int lines = Constants.Y;
    private boolean playerWins = false;
    private int shotCount = 0;
    private int[] lastShot;
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
//            buttonClicked();
            if (shotCount >= 26) {
                winCheck();
            }
            if (playerWins) {
                String victory = (yourTurn) ? Strings.win : Strings.lost;
                // Does something like return to menu or play again
                JOptionPane.showMessageDialog(null, victory, Strings.gameOver, JOptionPane.PLAIN_MESSAGE);
            }
 
        }
    };
 
    //Constructor
    public GameLayout(boolean yourTurn) throws IOException {
        setLayout(null);
        boatButtons = new JButton[lines][columns];
        shotButtons = new JButton[lines][columns];
        buttonSize = 25;
        lastShot = new int[2];
        this.yourTurn = yourTurn;
        
        fillGameLayout();
        turnButtons(false);
 
    }

    //Fills panel with gui parts
    private void fillGameLayout() throws IOException {
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j] = new JButton();
                boatButtons[i][j].setBounds(125+25*i, 30+25*j, buttonSize, buttonSize);
                boatButtons[i][j].setBackground(Color.white);
                boatButtons[i][j].addActionListener(actionListener);
                add(boatButtons[i][j]);
            }
            
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j] = new JButton();
                shotButtons[i][j].setBounds(400+25*i, 30+25*j, buttonSize, buttonSize);
                shotButtons[i][j].setBackground(Color.white);
                shotButtons[i][j].addActionListener(actionListener);
                add(shotButtons[i][j]);
            }
        }
        boat1icon = ImageIO.read(new File("src\\semestralni\\prace\\res\\boat2.png"));
        boat2icon = ImageIO.read(new File("src\\semestralni\\prace\\res\\boat4_2.png"));
        boat3icon = ImageIO.read(new File("src\\semestralni\\prace\\res\\boat4.png"));
        boat4icon = ImageIO.read(new File("src\\semestralni\\prace\\res\\boat6.png"));
        
        
        boat1 = new JButton(new ImageIcon(boat1icon));
        boat2 = new JButton(new ImageIcon(boat2icon));
        boat3 = new JButton(new ImageIcon(boat3icon));
        boat4 = new JButton(new ImageIcon(boat4icon));
        
        boat1.setBounds(10, 10, 50, 50);
        boat2.setBounds(65, 10, 50, 50);
        boat3.setBounds(10, 80, 50, 50);
        boat4.setBounds(65, 80, 50, 50);
        
        add(boat1);
        add(boat2);
        add(boat3);
        add(boat4);
        
        up = new JButton(Strings.up);     
        down = new JButton(Strings.down);
        left = new JButton(Strings.left);
        right = new JButton(Strings.right);
        start = new JButton(Strings.start);
        rotate = new JButton(Strings.rotate);
        
        up.setMargin(new Insets(1,1,1,1)); 
        down.setMargin(new Insets(1,1,1,1)); 
        left.setMargin(new Insets(1,1,1,1)); 
        right.setMargin(new Insets(1,1,1,1)); 
        start.setMargin(new Insets(1,1,1,1)); 
        rotate.setMargin(new Insets(1,1,1,1)); 
        
        up.setBounds(45, 170, 35, 35);
        down.setBounds(45, 207, 35, 35);
        left.setBounds(8, 207, 35, 35);
        right.setBounds(82, 207, 35, 35);
        start.setBounds(8, 244, 109, 35);
        rotate.setBounds(8, 170, 35, 35);
        
        
        add(up);
        add(down);
        add(left);
        add(right);
        add(start);
        add(rotate);
    
        info = new JLabel();
        info.setBounds(140, 0, 700, 30);
        setInfo(Strings.infoStringStart);
        
        add(info);
    }
    
    //Turns on/off all buttons
    public void turnButtons(boolean on){
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j].setEnabled(on);
            }       
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j].setEnabled(on);
            }
        }
        boat1.setEnabled(on);
        boat2.setEnabled(on);
        boat3.setEnabled(on);
        boat4.setEnabled(on);
        up.setEnabled(on);
        down.setEnabled(on);
        left.setEnabled(on);
        right.setEnabled(on);
        rotate.setEnabled(on);
        start.setEnabled(on);
    }
    //Resets game
    private void gameLayoutReset() {
        this.shotCount = 0;
    }
 
    //New Game
    public void newGame() {
        playerWins = false;
        gameLayoutReset();
    }
 
 
    //Checks if someone won
    public void winCheck() {
    }
 
    // Tells you whose turn it is
    public boolean isYourTurn() {
        return yourTurn;
    }
 
    
    //Gets last shot
    public int[] getLastShot() {
        return lastShot;
    }
    
    private void setInfo(String text){
      info.setText(text);  
    }
   
}