package semestralni.prace.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import semestralni.prace.*;

 
/**
 *
 * @author Tommzs
 */
public class GameLayout extends JPanel implements Serializable {
 
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
    private JButton start;
    
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
                String victory = (yourTurn) ? "You WON!" : "You LOST!";
                // Does something like return to menu or play again
                JOptionPane.showMessageDialog(null, String.format("Vyhrava: %s", victory), "Game Over", JOptionPane.PLAIN_MESSAGE);
            }
 
        }
    };
 
    //Constructor
    public GameLayout(boolean yourTurn) {
        setLayout(null);
        boatButtons = new JButton[lines][columns];
        shotButtons = new JButton[lines][columns];
        buttonSize = 25;
        lastShot = new int[2];
        this.yourTurn = yourTurn;
        
        fillGameLayout();
 
    }

    //Fills panel with gui parts
    private void fillGameLayout() {
        for (int i = 0; i < boatButtons.length; i++) {
            for (int j = 0; j < boatButtons[0].length; j++) {
                boatButtons[i][j] = new JButton("");
                boatButtons[i][j].setBounds(125+25*i, 10+25*j, buttonSize, buttonSize);
                boatButtons[i][j].setBackground(Color.white);
                boatButtons[i][j].addActionListener(actionListener);
                add(boatButtons[i][j]);
            }
            
            for (int j = 0; j < shotButtons[0].length; j++) {
                shotButtons[i][j] = new JButton("");
                shotButtons[i][j].setBounds(400+25*i, 10+25*j, buttonSize, buttonSize);
                shotButtons[i][j].setBackground(Color.white);
                shotButtons[i][j].addActionListener(actionListener);
                add(shotButtons[i][j]);
            }
        }
        
        boat1 = new JButton("");
        boat2 = new JButton("");
        boat3 = new JButton("");
        boat4 = new JButton("");
       
        boat1.setBounds(10, 10, 50, 50);
        boat2.setBounds(65, 10, 50, 50);
        boat3.setBounds(10, 80, 50, 50);
        boat4.setBounds(65, 80, 50, 50);
        add(boat1);
        add(boat2);
        add(boat3);
        add(boat4);
        
        up = new JButton("");
        down = new JButton("");
        left = new JButton("");
        right = new JButton("");
        start = new JButton("Start");
        up.setBounds(45, 170, 35, 35);
        down.setBounds(45, 207, 35, 35);
        left.setBounds(8, 207, 35, 35);
        right.setBounds(82, 207, 35, 35);
        start.setBounds(8, 244, 109, 35);
        add(up);
        add(down);
        add(left);
        add(right);
        add(start);
    
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
 
   
}