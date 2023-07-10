package main;

import Frames.MenuFrame;

/**
 * Classe Main del progetto
 * @author Christofer Fanò
 */
public class Main {
    public static void main(String[] args) {
        MenuFrame f = new MenuFrame("Gestione bilancio");

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}