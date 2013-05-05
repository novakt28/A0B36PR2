package semestralni.prace.boat;

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
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goUp) {
            System.out.println("Cannot collide with boat on UP");

            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (y - located.y == 0) {
                System.out.println("Cannot go up anymore!");
                return;
            }
            // KONROLA KOLIZE
            if (localizator[testX][testY - 1] == null) {
                
                if ((testY) != 0){
                if ((testY-1) != 0){
                if (localizator[testX][testY - 2] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}}
                
                if (testX !=0){
                if (localizator[testX - 1][testY-1] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}
                
                if (testX !=9){
                if (localizator[testX + 1][testY-1] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}
                
                
            }
            //KONEC KONTROLY KOLIZE
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

    public void moveDown(Array array) {
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goDown) {
            System.out.println("Cannot collide with boat on DOWN");

            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (y - located.y == 9) {
                System.out.println("Cannot go down anymore!");
                return;
            }
            // KONROLA KOLIZE
            if (localizator[testX][testY + 1] == null) {
                
                if ((testY) != 9){
                if ((testY+1) != 9){
                if (localizator[testX][testY + 2] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}}
                
                if (testX !=0){
                if (localizator[testX - 1][testY+1] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}
                
                if (testX !=9){
                if (localizator[testX + 1][testY+1] != null) {
                    System.out.println("Cannot go down anymore!");
                    return;
                }}
                
                
            }
            //KONEC KONTROLY KOLIZE
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

    public void moveLeft(Array array) {
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goLeft) {
            System.out.println("Cannot collide with boat on LEFT");

            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (x - located.x == 0) {
                System.out.println("Cannot go left anymore!");
                return;
            }
            // KONROLA KOLIZE
            if (localizator[testX - 1][testY] == null) {
                
                if ((testX) != 0){
                if ((testX-1) != 0){
                if (localizator[testX - 2][testY] != null) {
                    System.out.println("Cannot go left anymore!");
                    return;
                }}}
                
                if (testY !=0){
                if (localizator[testX - 1][testY-1] != null) {
                    System.out.println("Cannot go left anymore!");
                    return;
                }}
                
                if (testY !=9){
                if (localizator[testX - 1][testY+1] != null) {
                    System.out.println("Cannot go left anymore!");
                    return;
                }}
                
                
            }
            //KONEC KONTROLY KOLIZE
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

    public void moveRight(Array array) {
        boolean[][] a = array.getArray();
        Stack<BoatLocalizator> check = stackBoat(a);
        Stack<BoatLocalizator> squares = new Stack<>();
        Stack<BoatLocalizator> squares2 = new Stack<>();
        BoatLocalizator[][] localizator = GameLayout.getLocalizatorArray();
        if (!goRight) {
            System.out.println("Cannot collide with boat on RIGHT");

            return;
        }
        while (!check.empty()) {
            BoatLocalizator located = check.pop();
            squares.push(located);
            int testX = x - located.x;
            int testY = y - located.y;
            if (testX == 9) {
                System.out.println("Cannot go right anymore!");
                return;
            }
// KONROLA KOLIZE
            if (localizator[testX + 1][testY] == null) {
                
                if ((testX) != 9){
                if ((testX+1) != 9){
                if (localizator[testX + 2][testY] != null) {
                    System.out.println("Cannot go right anymore!");
                    return;
                }}}
                
                if (testY !=0){
                if (localizator[testX + 1][testY-1] != null) {
                    System.out.println("Cannot go right anymore!");
                    return;
                }}
                
                if (testY !=9){
                if (localizator[testX + 1][testY+1] != null) {
                    System.out.println("Cannot go right anymore!");
                    return;
                }}
                
                
            }
            //KONEC KONTROLY KOLIZE
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

    public void rotate(Array array) {
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
                System.out.println("Skipping control");
            } else {
                if (located.x != 0) {
                    test = a[x][y - located.y - located.x];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                    
                    int sign = (int) Math.signum(located.x);
                    if (testX != 9 && sign == -1 || testX != 0 && sign == 1){
                    test = a[x][y - located.y - located.x - sign];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }}
                    
                    test = a[x-1][y - located.y - located.x];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                    
                    test = a[x+1][y - located.y - located.x];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                    
                    
                }
                if (located.y != 0) {
//                    test = a[x - located.x + located.y][y];
//                    if(test != false){
//                    System.out.println("Cannot rotate!");
//                    return;
//                } 
                    test = a[x + located.y + located.x][y];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                    
                    int sign = (int) Math.signum(located.y);
                    if (testY != 0 && sign == -1 || testY != 9 && sign == 1){
                    test = a[x + located.y + located.x + sign][y];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }}
                    
                    test = a[x + located.y + located.x][y-1];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                    
                    test = a[x + located.y + located.x][y+1];
                    if(test != false){
                    System.out.println("Cannot rotate!");
                    return;
                    }
                }
            }
//            
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

    public abstract boolean putInArray(Array array);

    private Stack stackBoat(boolean[][] a) {
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
//                if (localizator[i+1][j]!= null) BoatParent.goRight=false;
//                if (localizator[i][j+1]!= null) BoatParent.goRight=false;
//                if (localizator[i][j-1]!= null) BoatParent.goRight=false;
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
//            if (localizator[i-1][j]!= null) BoatParent.goLeft=false;
//            if (localizator[i][j+1]!= null) BoatParent.goLeft=false;
//            if (localizator[i][j-1]!= null) BoatParent.goLeft=false;
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
//            if (localizator[i][j+1]!= null) BoatParent.goDown=false;
//            if (localizator[i-1][j]!= null) BoatParent.goDown=false;
//            if (localizator[i+1][j]!= null) BoatParent.goDown=false;
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
//            if (localizator[i][j-1]!= null) BoatParent.goUp=false;
//            if (localizator[i-1][j]!= null) BoatParent.goUp=false;
//            if (localizator[i+1][j]!= null) BoatParent.goUp=false;
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
