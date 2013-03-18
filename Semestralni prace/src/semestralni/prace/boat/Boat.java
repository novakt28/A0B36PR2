package semestralni.prace.boat;

import java.util.Arrays;

/**
 *
 * @author Tommzs
 */
public abstract class Boat {
    String name;
    int x;
    int y;
    int size;
    boolean killed;
    boolean[] health;

    public Boat(String name, int x, int y, int size) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.size = size;
        this.killed = false;
        this.health = new boolean[size];
        Arrays.fill(health, true);
    }
    
    
    
    boolean isKilled(){
        for (int i = 0; i < health.length; i++) {
            if (health[i]==false) {
                killed = true;
                return killed;
            }
        }
        return false;
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
    
    
    abstract void putInArray();
}
