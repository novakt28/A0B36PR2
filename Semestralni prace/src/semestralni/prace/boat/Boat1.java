/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.boat;

import javax.swing.JLabel;
import semestralni.prace.arrays.*;

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
    public boolean putInArray(Array array) {
        checkSurrounding();
        boolean[][] a = array.getArray();
        if (x!=0){
            if (a[x-1][y] == true) return false;
            if (y!=0){
                if (a[x][y-1] == true) return false;
            }
            if (y!=9){
                if (a[x][y+1] == true) return false;
            }}
        if (x!=8){
            if (a[x+2][y] == true) return false;
            if (y!=0){
                if (a[x+1][y-1] == true) return false;
            }
            if (y!=9){
                if (a[x+1][y+1] == true) return false;
            }}
        a[x][y] = true;
        a[x + 1][y] = true;
        // ??? this.setNumberOfBoats(this.getNumberOfBoats() - 1);
        return true;
    }

    
    public boolean checkSurrounding() {
        // add int x, y to know where are we checking!!
        // include in putInArray and every move!!!!
        return true;
    }



    
}
