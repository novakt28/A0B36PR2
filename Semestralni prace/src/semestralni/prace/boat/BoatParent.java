package semestralni.prace.boat;

import java.util.Arrays;
import java.util.Stack;
import javax.swing.JLabel;
import semestralni.prace.arrays.*;
import semestralni.prace.gui.GameLayout;

/**
 *
 * @author Tommzs
 */
public abstract class BoatParent {

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

    public int getNumberOfBoats() {
        return numberOfBoats;
    }

    public void setNumberOfBoats(int numberOfBoats) {
        label.setText(Integer.toString(numberOfBoats));
        this.numberOfBoats = numberOfBoats;
    }

    @Override
    public String toString() {
        return "BoatParent{" + "name=" + name + '}';
    }

    public void moveUp(Array array) {
        boolean[][] a = array.getArray();
        Stack check = stackBoat(a);
        Stack squares = new Stack();
        Stack squares2 = new Stack();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if(!goUp) {
            System.out.println("Cannot collide with boat on UP");
            
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = (BoatLocalizator) check.pop();
            squares.push(located);
            
            if (y - located.y == 0) {
                System.out.println("Cannot go up anymore!");
                return;
            }
        }

        while (!squares.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares2.pop();
            a[x - located.x][y - located.y - 1] = true;
        }
        GameLayout.setActualY(GameLayout.getActualY() - 1);
    }

    public void moveDown(Array array) {
        boolean[][] a = array.getArray();
        Stack check = stackBoat(a);
        Stack squares = new Stack();
        Stack squares2 = new Stack();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if(!goDown) {
            System.out.println("Cannot collide with boat on DOWN");
            
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = (BoatLocalizator) check.pop();
            squares.push(located);
            if (y - located.y == 9) {
                System.out.println("Cannot go down anymore!");
                return;
            }
        }

        while (!squares.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares2.pop();
            a[x - located.x][y - located.y + 1] = true;
        }

        GameLayout.setActualY(GameLayout.getActualY() + 1);


    }

    public void moveLeft(Array array) {
        boolean[][] a = array.getArray();
        Stack check = stackBoat(a);
        Stack squares = new Stack();
        Stack squares2 = new Stack();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if(!goLeft) {
            System.out.println("Cannot collide with boat on LEFT");
            
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = (BoatLocalizator) check.pop();
            squares.push(located);
            if (x - located.x == 0) {
                System.out.println("Cannot go left anymore!");
                return;
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares2.pop();
            a[x - located.x - 1][y - located.y] = true;
        }

        GameLayout.setActualX(GameLayout.getActualX() - 1);


    }

    public void moveRight(Array array) {
        boolean[][] a = array.getArray();
        Stack check = stackBoat(a);
        Stack squares = new Stack();
        Stack squares2 = new Stack();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if(!goRight) {
            System.out.println("Cannot collide with boat on RIGHT");
            
            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = (BoatLocalizator) check.pop();
            squares.push(located);
            if (x - located.x == 9) {
                System.out.println("Cannot go right anymore!");    
                return;
            }
        }
        while (!squares.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares2.pop();
            a[x - located.x + 1][y - located.y] = true;
        }

        GameLayout.setActualX(GameLayout.getActualX() + 1);


    }

    public void rotate(Array array) {
        boolean[][] a = array.getArray();
        Stack check = stackBoat(a);
        Stack squares = new Stack();
        Stack squares2 = new Stack();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        
        while (!check.empty()) {
            BoatLocalizator located = (BoatLocalizator) check.pop();
            boolean test;
            squares.push(located);
            
             if (located.x == 0 && located.y == 0) {
                 System.out.println("Skipping control");
            } else {
                if (located.x != 0) {
                    test = a[x - located.x + located.x][y - located.y - located.x];
                }
                if (located.y != 0) {
                    test = a[x - located.x + located.y][y - located.y + located.y];
                }
            }
//            
        }
        while (!squares.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares.pop();
            localizator[x - located.x][y - located.y] = null;
            a[x - located.x][y - located.y] = false;
            squares2.push(located);
        }
        while (!squares2.empty()) {
            BoatLocalizator located = (BoatLocalizator) squares2.pop();
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

    public abstract void putInArray(Array array);

    private Stack stackBoat(boolean[][] a) {
        Stack squares = new Stack();
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
                if (localizator[i+1][j]!= null) BoatParent.goRight=false;
                if (localizator[i][j+1]!= null) BoatParent.goRight=false;
                if (localizator[i][j-1]!= null) BoatParent.goRight=false;
        } catch (Exception e) {
            System.out.println("Exception catched, it should work..");

        }

        i = x - 1;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                i--;

                testLocalizator = localizator[i][j];

            }
            if (localizator[i-1][j]!= null) BoatParent.goLeft=false;
            if (localizator[i][j+1]!= null) BoatParent.goLeft=false;
            if (localizator[i][j-1]!= null) BoatParent.goLeft=false;
        } catch (Exception e) {
            System.out.println("Exception catched, it should work..");

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
            if (localizator[i][j+1]!= null) BoatParent.goDown=false;
            if (localizator[i-1][j]!= null) BoatParent.goDown=false;
            if (localizator[i+1][j]!= null) BoatParent.goDown=false;
        } catch (Exception e) {
            System.out.println("Exception catched, it should work..");

        }


        j = y - 1;
        try {
            testLocalizator = localizator[i][j];
            while (testLocalizator != null) {
                squares.push(testLocalizator);
                j--;

                testLocalizator = localizator[i][j];

            }
            if (localizator[i][j-1]!= null) BoatParent.goUp=false;
            if (localizator[i-1][j]!= null) BoatParent.goUp=false;
            if (localizator[i+1][j]!= null) BoatParent.goUp=false;
        } catch (Exception e) {
            System.out.println("Exception catched, it should work..");

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

