package main;

import javax.swing.*;
import GUI.MainFrame;

public class Main {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
            MainFrame f = new MainFrame("Gestione bilancio");


            f.pack();
            f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
    }