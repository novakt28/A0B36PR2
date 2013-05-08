/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace.gui.listeners;

import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;
import semestralni.prace.boat.BoatParent;
import semestralni.prace.gui.GameLayout;

/**
 *
 * @author Tommzs
 */
public class MoveBoatsButtonsListener implements ActionListener {

    JButton pressed;

    @Override
    public void actionPerformed(ActionEvent ae) {
        pressed = (JButton) ae.getSource();
        GameLayout.setInfo(Strings.boatPlacingInfo);
        switch (pressed.getName()) {
            case "boat1":
                GameLayout.setBoat(GameLayout.getBoat1());
                break;

            case "boat2":
                GameLayout.setBoat(GameLayout.getBoat2());
                break;

            case "boat3":
                GameLayout.setBoat(GameLayout.getBoat3());
                break;

            case "boat4":
                GameLayout.setBoat(GameLayout.getBoat4());
                break;

            default:
                moveButtons();
        }
    }

    private void moveButtons(){
        Array array = GameLayout.getBoatArray();

        try {
            switch (pressed.getName()) {
                case "up":
                    GameLayout.getBoat().moveUp(array);
                    break;
                case "down":
                    GameLayout.getBoat().moveDown(array);
                    break;
                case "left":
                    GameLayout.getBoat().moveLeft(array);
                    break;
                case "right":
                    GameLayout.getBoat().moveRight(array);
                    break;
                case "rotate":
                    GameLayout.getBoat().rotate(array);
                    break;
                case "start": if (GameLayout.getBoat1().getNumberOfBoats() == 0 && GameLayout.getBoat2().getNumberOfBoats() == 0 && GameLayout.getBoat3().getNumberOfBoats() == 0 && GameLayout.getBoat4().getNumberOfBoats() == 0){
                    try {
                        GameLayout.setGameOn();
                    } catch (IOException e) {
                        // OSETRI
                        System.out.println("ERROR, cannot setGameOn");
                    }
                } 
                    break;
                default:
                    JOptionPane.showMessageDialog(null, Strings.unknownButtonPressed);
            }
            GameLayout.localizationArrayUpdate(GameLayout.getBoat());
            GameLayout.repaintGameArray();
        } catch (ArrayIndexOutOfBoundsException e) {
            GameLayout.setInfo(Strings.cannotPutBoat);
        } finally {
            BoatParent.setGoDown(true);
            BoatParent.setGoLeft(true);
            BoatParent.setGoUp(true);
            BoatParent.setGoRight(true);
        }
    }
}
