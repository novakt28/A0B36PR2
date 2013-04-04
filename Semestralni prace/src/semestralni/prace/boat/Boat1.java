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
        numberOfBoats = 2;
    }

    @Override
    public void putInArray(Array array) {
        checkSurrounding();
        boolean[][] a = array.getArray();
        a[x][y] = true;
        a[x + 1][y] = true;
    }

    @Override
    public boolean checkSurrounding() {
        // add int x, y to know where are we checking!!
        // include in putInArray and every move!!!!
        return true;
    }

    private void setActualXY() {
        this.setX(GameLayout.getActualX());
        this.setY(GameLayout.getActualY());
    }
    
    private void eraseFromInfoArray(){
        BoatLocalizator[][] boatLocalizator = GameLayout.getLocalizatorArray();
        boatLocalizator[x][y] = null;
        boatLocalizator[x + 1][y] = null;
    }

    @Override
    public void moveRight(Array array) {
        try {
            setActualXY();
            if (!checkSurrounding()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            eraseFromInfoArray();
            boolean[][] a = array.getArray();
            a[x][y] = false;
            a[x + 1][y] = false;

            a[x + 1][y] = true;
            a[x + 2][y] = true;

            GameLayout.setActualX(GameLayout.getActualX() + 1);
            this.setX(x + 1);
            GameLayout.setBoat(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void moveLeft(Array array) {
        try {
            setActualXY();
            if (!checkSurrounding()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            eraseFromInfoArray();
            boolean[][] a = array.getArray();
            a[x][y] = false;
            a[x + 1][y] = false;

            a[x - 1][y] = true;
            a[x][y] = true;

            GameLayout.setActualX(GameLayout.getActualX() - 1);
            this.setX(x - 1);
            GameLayout.setBoat(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void moveUp(Array array) {
        try {
            setActualXY();
            if (!checkSurrounding()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            eraseFromInfoArray();
            boolean[][] a = array.getArray();
            BoatLocalizator[][] boatLocalizator = GameLayout.getLocalizatorArray();
            boatLocalizator[x][y] = null;
            boatLocalizator[x + 1][y] = null;
            a[x][y] = false;
            a[x + 1][y] = false;

            a[x][y - 1] = true;
            a[x + 1][y - 1] = true;

            GameLayout.setActualY(GameLayout.getActualY() - 1);
            this.setY(y - 1);
            GameLayout.setBoat(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void moveDown(Array array) {
        try {
            setActualXY();
            if (!checkSurrounding()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            eraseFromInfoArray();
            boolean[][] a = array.getArray();
            a[x][y] = false;
            a[x + 1][y] = false;
            
            
            a[x][y + 1] = true;
            a[x + 1][y + 1] = true;

            GameLayout.setActualY(GameLayout.getActualY() + 1);
            this.setY(y + 1);
            GameLayout.setBoat(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void rotate(Array array) {
         try {
            setActualXY();
            if (!checkSurrounding()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            eraseFromInfoArray();
            boolean[][] a = array.getArray();
            a[x][y] = false;
            a[x + 1][y] = false;
            
            
            a[x][y] = true;
            a[x][y + 1] = true;

            
            GameLayout.setBoat(this);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
