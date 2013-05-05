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
    public boolean putInArray(Array array) {
        boolean[][] a = array.getArray();
        if (x != 0) {
            if (a[x-1][y] == true) return false;
            if (x!=1){
            if (a[x-2][y] == true) return false;
            }
            if (y != 0) {
                if (a[x - 1][y - 1] == true) {
                    return false;
                }
            }
            if (y != 9) {
                if (a[x - 1][y + 1] == true) {
                    return false;
                }
            }
        }
        if (x != 6) {
            if (a[x + 4][y] == true) {
                return false;
            }
            if (y != 0) {
                if (a[x + 3][y - 1] == true) {
                    return false;
                }
            }
            if (y != 9) {
                if (a[x + 3][y + 1] == true) {
                    return false;
                }
            }
        }

        if (y != 9) {
            if (a[x][y + 1] == true) {
                return false;
            }
            if (a[x + 1][y + 1] == true) {
                return false;
            }
            if (a[x + 2][y + 1] == true) {
                return false;
            }
        }
        if (y != 0) {
            if (a[x][y - 1] == true) {
                return false;
            }
            if (a[x + 1][y - 1] == true) {
                return false;
            }
            if (a[x + 2][y - 1] == true) {
                return false;
            }
        }
        a[x][y] = true;
        a[x+1][y] = true;
        a[x-1][y] = true;
        a[x+2][y] = true;
        a[x+3][y] = true;
        return true;
        
    }

   

  
    public boolean checkSurrounding() {
        // include in putInArray and every move!!!!
        return true;
    }

    
}
