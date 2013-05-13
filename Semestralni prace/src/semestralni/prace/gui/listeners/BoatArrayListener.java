package semestralni.prace.gui.listeners;

import java.awt.event.*;
import semestralni.prace.*;
import semestralni.prace.arrays.Array;
import semestralni.prace.boat.*;
import semestralni.prace.gui.*;

/**
 *
 * @author Tommzs
 */
public class BoatArrayListener implements ActionListener {

    /**
     * AL for field used for placing boats
     *
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        // Prepares needed data
        ButtonXY button = (ButtonXY) ae.getSource();
        boolean[][] boatArray = GameLayout.getBoatArray().getArray();
        int x = button.getX2();
        int y = button.getY2();
        GameLayout.setActualX(x);
        GameLayout.setActualY(y);

        if (boatArray[x][y] != true) {
            try {
                BoatParent boat = GameLayout.getBoat();
                if (boat.getNumberOfBoats() > 0) {
                    boat.setX(x);
                    boat.setY(y);

                    // Tries to put the boat into field

                    boat.putInArray(new Array()); // Just checking if it would not be OOB, if so the rest is skipped (the exception is thrown and cached
                    boolean a = boat.putInArray(GameLayout.getBoatArray());
                    if (a == true) {
                        boat.setNumberOfBoats(boat.getNumberOfBoats() - 1);
                        GameLayout.localizationArrayUpdate(boat);
                        GameLayout.repaintGameArray();
                    }

                } else {
                    GameLayout.setInfo(Strings.noMoreBoats);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                GameLayout.setInfo(Strings.cannotPutBoat);
            } catch (NullPointerException e) {
                GameLayout.setInfo(Strings.selectBoat);
            }
        } else {
            // If you click on boat this localize the boat using BoatLoacalizator
            BoatLocalizator[][] localizatorArray = GameLayout.getLocalizatorArray();
            GameLayout.setBoat(localizatorArray[x][y].getBoat());
            GameLayout.setActualX(x + localizatorArray[x][y].getX());
            GameLayout.setActualY(y + localizatorArray[x][y].getY());
        }


    }
}
