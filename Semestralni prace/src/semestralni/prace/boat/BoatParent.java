package semestralni.prace.boat;

import java.util.Stack;
import javax.swing.JLabel;
import semestralni.prace.Strings;
import semestralni.prace.arrays.*;
import semestralni.prace.gui.GameLayout;

/**
 *
 * @author Tommzs
 */
public abstract class BoatParent {
    /* Parent of all boats, has methods for moving, rotating etc.*/

    String name;
    int x;
    int y;
    int size;
    int numberOfBoats;
    JLabel label;
    static boolean goRight = true;
    static boolean goLeft = true;
    static boolean goDown = true;
    static boolean goUp = true;

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

    public int getNumberOfBoats() { // Returns number of actuall boats possible to add to field
        return numberOfBoats;
    }

    public void setNumberOfBoats(int numberOfBoats) { // Sets number of actuall boats possible to add to field
        label.setText(Integer.toString(numberOfBoats));
        this.numberOfBoats = numberOfBoats;
    }

    public void moveUp(Array array) { // Moves boat up, controlls if boat will not collide with other boat (or rules of game) or the end of the field
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goUp) {
            GameLayout.setInfo(Strings.cannotGoUp);
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (y - located.y == 0) {
                GameLayout.setInfo(Strings.cannotGoUp);
                return;
            }
            
            if (localizator[testX][testY - 1] == null) {

                if ((testY) != 0) {
                    if ((testY - 1) != 0) {
                        if (localizator[testX][testY - 2] != null) {
                            GameLayout.setInfo(Strings.cannotGoUp);
                            return;
                        }
                    }
                }

                if (testX != 0) {
                    if (localizator[testX - 1][testY - 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoUp);
                        return;
                    }
                }

                if (testX != 9) {
                    if (localizator[testX + 1][testY - 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoUp);
                        return;
                    }
                }
            }
        }

        while (!squares.empty()) {
            BoatLocalizator located = squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = squares2.pop();
            a[x - located.x][y - located.y - 1] = true;
        }
        GameLayout.setActualY(GameLayout.getActualY() - 1);
    }

    public void moveDown(Array array) { // Moves boat down, controlls if boat will not collide with other boat (or rules of game) or the end of the field
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goDown) {
            GameLayout.setInfo(Strings.cannotGoDown);
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (y - located.y == 9) {
                GameLayout.setInfo(Strings.cannotGoDown);
                return;
            }
            if (localizator[testX][testY + 1] == null) {
                if ((testY) != 9) {
                    if ((testY + 1) != 9) {
                        if (localizator[testX][testY + 2] != null) {
                            GameLayout.setInfo(Strings.cannotGoDown);
                            return;
                        }
                    }
                }
                if (testX != 0) {
                    if (localizator[testX - 1][testY + 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoDown);
                        return;
                    }
                }
                if (testX != 9) {
                    if (localizator[testX + 1][testY + 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoDown);
                        return;
                    }
                }
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = squares2.pop();
            a[x - located.x][y - located.y + 1] = true;
        }
        GameLayout.setActualY(GameLayout.getActualY() + 1);
    }

    public void moveLeft(Array array) { // Moves boat left, controlls if boat will not collide with other boat (or rules of game) or the end of the field
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goLeft) {
            GameLayout.setInfo(Strings.cannotGoLeft);
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (x - located.x == 0) {
                GameLayout.setInfo(Strings.cannotGoLeft);
                return;
            }
            if (localizator[testX - 1][testY] == null) {
                if ((testX) != 0) {
                    if ((testX - 1) != 0) {
                        if (localizator[testX - 2][testY] != null) {
                            GameLayout.setInfo(Strings.cannotGoLeft);
                            return;
                        }
                    }
                }
                if (testY != 0) {
                    if (localizator[testX - 1][testY - 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoLeft);
                        return;
                    }
                }
                if (testY != 9) {
                    if (localizator[testX - 1][testY + 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoLeft);
                        return;
                    }
                }
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = squares2.pop();
            a[x - located.x - 1][y - located.y] = true;
        }
        GameLayout.setActualX(GameLayout.getActualX() - 1);
    }

    public void moveRight(Array array) { // Moves boat right, controlls if boat will not collide with other boat (or rules of game) or the end of the field
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goRight) {
            GameLayout.setInfo(Strings.cannotGoRight);
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (testX == 9) {
                GameLayout.setInfo(Strings.cannotGoRight);
                return;
            }
            if (localizator[testX + 1][testY] == null) {
                if ((testX) != 9) {
                    if ((testX + 1) != 9) {
                        if (localizator[testX + 2][testY] != null) {
                            GameLayout.setInfo(Strings.cannotGoRight);
                            return;
                        }
                    }
                }
                if (testY != 0) {
                    if (localizator[testX + 1][testY - 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoRight);
                        return;
                    }
                }
                if (testY != 9) {
                    if (localizator[testX + 1][testY + 1] != null) {
                        GameLayout.setInfo(Strings.cannotGoRight);
                        return;
                    }
                }
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = squares2.pop();
            a[x - located.x + 1][y - located.y] = true;
        }
        GameLayout.setActualX(GameLayout.getActualX() + 1);
    }

    public void rotate(Array array) { //Rotate boat, controlls if boat will not collide with other boat (or rules of game) or the end of the field
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            boolean test;
            squares.push(located);
            int testX = y - located.x;
            int testY = x + located.y;
            if (located.x == 0 && located.y == 0) {
                //skipping control - default square will not move
            } else {
                if (located.x != 0) {
                    test = a[x][y - located.y - located.x];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                    int sign = (int) Math.signum(located.x);
                    if (testX != 9 && sign == -1 || testX != 0 && sign == 1) {
                        test = a[x][y - located.y - located.x - sign];
                        if (test != false) {
                            GameLayout.setInfo(Strings.cannotRotate);
                            return;
                        }
                    }
                    test = a[x - 1][y - located.y - located.x];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                    test = a[x + 1][y - located.y - located.x];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                }
                if (located.y != 0) {
                    test = a[x + located.y + located.x][y];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                    int sign = (int) Math.signum(located.y);
                    if (testY != 0 && sign == -1 || testY != 9 && sign == 1) {
                        test = a[x + located.y + located.x + sign][y];
                        if (test != false) {
                            GameLayout.setInfo(Strings.cannotRotate);
                            return;
                        }
                    }
                    test = a[x + located.y + located.x][y - 1];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                    test = a[x + located.y + located.x][y + 1];
                    if (test != false) {
                        GameLayout.setInfo(Strings.cannotRotate);
                        return;
                    }
                }
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = squares2.pop();
            if (located.x == 0 && located.y == 0) {
                a[x - located.x][y - located.y] = true;
            } else {
                if (located.x != 0) {
                    a[x - located.x + located.x][y - located.y - located.x] = true;
                }
                if (located.y != 0) {
                    a[x - located.x + located.y][y - located.y + located.y] = true;
                }
            }
        }
    }

    public abstract boolean putInArray(Array array); // Abstract will MAKE me create this method if I will make any descendants

    private Stack<BoatLocalizator> stackBoat(boolean[][] a) { // This method will make Stack out of the boat you selected in purpose of moving it.
        Stack<BoatLocalizator> squares = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        this.setX(GameLayout.getActualX());
        this.setY(GameLayout.getActualY());
        int i = x;
        int j = y;
        BoatLocalizator testLocalizator;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                i++;
                testLocalizator = localizator[i][j];
            }
        } catch (Exception e) {
            //exception catched, no need to treat it (will explain if needed)
        }
        i = x - 1;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                i--;
                testLocalizator = localizator[i][j];
            }
        } catch (Exception e) {
            //exception catched, no need to treat it (will explain if needed)
        }
        i = x;
        j = y + 1;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                j++;
                testLocalizator = localizator[i][j];
            }
        } catch (Exception e) {
            //exception catched, no need to treat it (will explain if needed)
        }
        j = y - 1;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                j--;
                testLocalizator = localizator[i][j];
            }
        } catch (Exception e) {
            //exception catched, no need to treat it (will explain if needed)
        }
        return squares;
    }

    public static void setGoRight(boolean goRight) {
        BoatParent.goRight = goRight;
    }
    public static void setGoLeft(boolean goLeft) {
        BoatParent.goLeft = goLeft;
    }
    public static void setGoDown(boolean goDown) {
        BoatParent.goDown = goDown;
    }
    public static void setGoUp(boolean goUp) {
        BoatParent.goUp = goUp;
    }
}
