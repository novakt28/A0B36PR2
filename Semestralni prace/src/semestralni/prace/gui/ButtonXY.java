
package semestralni.prace.gui;

import javax.swing.JButton;

/**
 *
 * @author Tommzs
 */
public class ButtonXY extends JButton{
    /**
     * "Smart" button with positions
     */
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
