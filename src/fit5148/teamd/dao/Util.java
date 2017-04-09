/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author Varun
 */
public class Util {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EML_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Validate s with regular expression
     *
     * @param s hex for validation
     * @return true valid s, false invalid s
     */
    public static boolean validateEml(String s) {
        if (s == null || s.compareTo("") == 0) {
            return false;
        }
        pattern = Pattern.compile(EML_PATTERN);
        System.out.println("string for validation " + s);
        matcher = pattern.matcher(s);
        return matcher.matches();

    }

    public static void setErrorMessage(JLabel jl, String msg) {
        jl.setText(msg);
        jl.setVisible(true);
    }

    public static void clearErrorMessage(JLabel... jl) {

        for (int i = 0; i < jl.length; i++) {
            jl[i].setText("");
            jl[i].setVisible(false);
        }

    }

    public static void displayToggleComponent(JComponent jc) {
        if (jc.isEnabled() || jc.isVisible()) {
            jc.setEnabled(false);
            jc.setVisible(false);
        } else {
            jc.setVisible(true);
            jc.setEnabled(true);
        }

    }
}
