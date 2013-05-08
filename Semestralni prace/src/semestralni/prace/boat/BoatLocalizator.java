/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.boat;

/**
 *
 * @author Tommzs
 */
public class BoatLocalizator {
    /* Special class used for localizating boats in Array,
    * holds information about the boat you click on - it's default square x and y possition
    and type of boat itself*/
    int x;
    int y;
    BoatParent boat;

    public BoatLocalizator(int x, int y, BoatParent boat) {
        this.x = x;
        this.y = y;
        this.boat = boat;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BoatParent getBoat() {
        return boat;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBoat(BoatParent boat) {
        this.boat = boat;
    }

}
