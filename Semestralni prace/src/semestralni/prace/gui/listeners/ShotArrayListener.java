/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.gui.listeners;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import semestralni.prace.Strings;
import semestralni.prace.gui.*;

/**
 *
 * @author Tommzs
 */
public class ShotArrayListener implements ActionListener {
    /**
    * AL for field used for sending shots
    *
    */
    @Override
    // Handles your input and send it to sendShot method
    public void actionPerformed(ActionEvent ae) {
        if (!GameLayout.isYourTurn() || GameLayout.getBoatArrayOpponent() == null) {
            return;
        }
        GameLayout.setYourTurn(false);
        GameLayout.setInfo(Strings.waitForOpponent);
        
        ButtonXY button = (ButtonXY) ae.getSource();
        int x = button.getX2();
        int y = button.getY2();
        boolean[][] ref = GameLayout.getBoatArrayOpponent().getArray();
        
        button.removeActionListener(this);
        if (ref[x][y] == true) {
            button.setBackground(Color.RED);
            int test = GameLayout.getBoatPartsAlive()-1;
            GameLayout.setBoatPartsAlive(test);
            
        } else {
            button.setBackground(Color.GRAY);
        }
        
        if (GameLayout.isServer()) {
            try {
                Server.sendShot(x, y);
            } catch (IOException ex) {
                GameLayout.setInfo(Strings.cannotSendData);
            }
        } else {
            try {
                Client.sendShot(x, y);
            } catch (IOException ex) {
                GameLayout.setInfo(Strings.cannotSendData);
            }
        }
        
        
    }
}
