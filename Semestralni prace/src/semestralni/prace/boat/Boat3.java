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

   

    
}
