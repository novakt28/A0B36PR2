/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.gui;

import java.io.Serializable;

/**
 *
 * @author Tommzs
 */
public class DataPack implements Serializable {

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
