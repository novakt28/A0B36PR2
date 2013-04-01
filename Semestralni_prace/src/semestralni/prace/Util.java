/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralni.prace;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Tommzs
 */
public class Util {

    private static File logger;

    public static boolean writeToFile(String text) {
        Date recentdate = Calendar.getInstance().getTime();
        logger = new File("CRASHLOG.txt");
        try {
            try (FileWriter fw = new FileWriter(logger, true)) {
                fw.write("\n" + recentdate + ": " + text);
                fw.flush();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }

        return true;
    }
}
