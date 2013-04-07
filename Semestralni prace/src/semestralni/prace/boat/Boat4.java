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
public class Boat4 extends BoatParent{
   public Boat4(String name, int x, int y, int size, JLabel label) {
       super(name, x, y, size, label);
       numberOfBoats = 1;
        }
   
   
    @Override
    public void putInArray(Array array) {
        boolean[][] a = array.getArray();
        a[x][y] = true;
        a[x+1][y] = true;
        a[x-1][y] = true;
        a[x+2][y] = true;
        a[x][y-1] = true;
        
    }

   

  
    public boolean checkSurrounding() {
        // include in putInArray and every move!!!!
        return true;
    }

    
}
