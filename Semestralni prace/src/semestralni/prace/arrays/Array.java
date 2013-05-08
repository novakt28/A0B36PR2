package semestralni.prace.arrays;

import java.util.Arrays;
import semestralni.prace.*;

/**
 *
 * @author Tommzs
 */
public class Array { // Object holding Arrays for the game

    boolean[][] array;

    public Array() {
        this.array = new boolean[10][10];
    }

    public boolean[][] getArray() {
        return this.array;
    }

    public void setArray(boolean[][] array) {
        this.array = array;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Arrays.deepHashCode(this.array);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Array other = (Array) obj;
        if (!Arrays.deepEquals(this.array, other.array)) {
            return false;
        }
        return true;
    }
}
