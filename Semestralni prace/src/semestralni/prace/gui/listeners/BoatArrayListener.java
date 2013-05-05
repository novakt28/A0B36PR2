/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.gui.listeners;

import java.awt.event.*;
import javax.swing.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;
import semestralni.prace.boat.*;
import semestralni.prace.gui.*;
/**
 *
 * @author Tommzs
 */
public class BoatArrayListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
      
        ButtonXY button = (ButtonXY) ae.getSource();
        boolean[][] boatArray = GameLayout.getBoatArray().getArray();
        int x = button.getX2();
        int y = button.getY2();
        GameLayout.setActualX(x);
        GameLayout.setActualY(y);
        
        if(boatArray[x][y] != true){
        BoatParent boat = GameLayout.getBoat();
        if(boat.getNumberOfBoats()>0){
        boat.setX(x);
        boat.setY(y);
        
        
            try {
                boat.putInArray(new Array()); // trying if it is out of bounds
                boolean a = boat.putInArray(GameLayout.getBoatArray());               
                if (a == true){ boat.setNumberOfBoats(boat.getNumberOfBoats() - 1);
                GameLayout.localizationArrayUpdate(boat);
                GameLayout.repaintGameArray();
                }
            } catch (ArrayIndexOutOfBoundsException e) {   
                 GameLayout.setInfo(Strings.cannotPutBoat);
            } 
            
        
        
        } else {
                JOptionPane.showMessageDialog(null, Strings.noMoreBoats);
            }
    } else {
            BoatLocalizator[][] localizatorArray = GameLayout.getLocalizatorArray();
            GameLayout.setBoat(localizatorArray[x][y].getBoat());
            GameLayout.setActualX(x+localizatorArray[x][y].getX());
            GameLayout.setActualY(y+localizatorArray[x][y].getY());
            System.out.println(localizatorArray[x][y].toString());
        }
    
    
}
    
    
}

