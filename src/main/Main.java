package main;

import javax.swing.*;
import Frames.MenuFrame;

/**
 * Classe Main del progetto
 * @author Christofer Fanò
 */
public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
            MenuFrame f = new MenuFrame("Gestione bilancio");


            f.pack();
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
    }