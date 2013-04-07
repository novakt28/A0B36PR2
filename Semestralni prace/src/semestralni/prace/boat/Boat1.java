/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.boat;

import javax.swing.JLabel;
import semestralni.prace.arrays.*;
import semestralni.prace.gui.GameLayout;

/**
 *
 * @author Tommzs
 */
public class Boat1 extends BoatParent {

    public Boat1(String name, int x, int y, int size, JLabel label) {
        super(name, x, y, size, label);
        numberOfBoats = 3;
    }

    @Override
    public void putInArray(Array array) {
        checkSurrounding();
        boolean[][] a = array.getArray();
        a[x][y] = true;
        a[x + 1][y] = true;
    }

    
    public boolean checkSurrounding() {
        // add int x, y to know where are we checking!!
        // include in putInArray and every move!!!!
        return true;
    }



    
}
