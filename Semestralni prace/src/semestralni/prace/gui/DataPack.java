package semestralni.prace.gui;

import java.io.Serializable;

/**
 *
 * @author Tommzs
 */
public class DataPack implements Serializable {
    /**
    *
    * Object for unified data sending (will decide what data it is by boolean dec)
    */
    int[] shot;
    boolean[][] boatArray;
    boolean dec;
    boolean win;
    
    public DataPack(int[] shot) {
        this.shot = shot;
        this.dec = true;
        this.win = false;
    }

    public DataPack(boolean[][] boatArray) {
        this.boatArray = boatArray;
        this.dec = false;
    }
}
