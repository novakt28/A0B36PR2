package semestralni.prace.arrays;

import java.io.Serializable;
import java.util.Arrays;
import semestralni.prace.*;
/**
 *
 * @author Tommzs
 */
public class Array implements Serializable{
    boolean[][] array;

    public Array() {
        this.array = new boolean[Constants.X][Constants.Y];
    }
    
    
    boolean[][] getArray(){  
        return this.array;
    }
    
    void setArray(boolean[][] array){    
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
