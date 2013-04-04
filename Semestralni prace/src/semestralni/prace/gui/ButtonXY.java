/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.gui;

import javax.swing.JButton;

/**
 *
 * @author Tommzs
 */
public class ButtonXY extends JButton{
    int x;
    int y;

    public ButtonXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    public int getX2(){
     return x;
    }
    
    public int getY2(){
     return y;
    }
    
    public void setX2(int x){
     this.y = x;
    }
    
    public void setY2(int y){
     this.y = y;
    }
}
