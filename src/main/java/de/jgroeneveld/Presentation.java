package de.jgroeneveld;

/**
 * Created by jgroeneveld on 11.12.14.
 */
public class Presentation {
    public void display(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println(message);
    }

    public void printStackTrace(Exception e) {
        e.printStackTrace();
    }
}
