package semestralni.prace.boat;

/**
 *
 * @author Tommzs
 */
public abstract class Boat {
    String name;
    int x;
    int y;
    boolean killed;
    boolean[] body;
    
    boolean isKilled(){
        for (int i = 0; i < body.length; i++) {
            if (body[i]==false) {
                killed = true;
                return true;
            }
        }
        return false;
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
