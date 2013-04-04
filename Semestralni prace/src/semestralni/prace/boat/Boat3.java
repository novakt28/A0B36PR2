/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.boat;

import javax.swing.JLabel;
import semestralni.prace.arrays.Array;

/**
 *
 * @author Tommzs
 */
public class Boat3 extends BoatParent {
     public Boat3(String name, int x, int y, int size, JLabel label) {
       super(name, x, y, size, label);
       numberOfBoats = 2;
        }
   
   
    @Override
    public void putInArray(Array array) {
        boolean[][] a = array.getArray();
        a[x][y] = true;
        a[x+1][y] = true;
        a[x-1][y] = true;
        a[x+2][y] = true;
    }

   

    @Override
    public boolean checkSurrounding() {
        // include in putInArray and every move!!!!
        return true;
    }

    @Override
    public void moveRight(Array array) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveLeft(Array array) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveUp(Array array) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void moveDown(Array array) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void rotate(Array array) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
