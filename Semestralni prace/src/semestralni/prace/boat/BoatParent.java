package semestralni.prace.boat;

import java.util.Arrays;
import javax.swing.JLabel;
import semestralni.prace.arrays.*;

/**
 *
 * @author Tommzs
 */
public abstract class BoatParent {
    String name;
    int x;
    int y;
    int size;
    int numberOfBoats;
    JLabel label;

    public BoatParent(String name, int x, int y, int size, JLabel label) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.label = label;
    }
    
        
    public String getName() {
        return name;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNumberOfBoats() {
        return numberOfBoats;
    }

    public void setNumberOfBoats(int numberOfBoats) {
        label.setText(Integer.toString(numberOfBoats));
        this.numberOfBoats = numberOfBoats;
    }

    @Override
    public String toString() {
        return "BoatParent{" + "name=" + name + '}';
    }
    
    
    public abstract void putInArray(Array array);
    public abstract void moveRight(Array array);
    public abstract void moveLeft(Array array);
    public abstract void moveUp(Array array);
    public abstract void moveDown(Array array);
    public abstract void rotate(Array array);
    public abstract boolean checkSurrounding();
}
