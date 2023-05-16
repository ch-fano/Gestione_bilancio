package GUI;

import javax.swing.*;
import java.awt.*;

import java.io.File;

import javax.swing.JFileChooser;

public class FileFrame  extends JFrame {
    public FileFrame(String titolo) {
        super(titolo);

        JFileChooser chooser = new JFileChooser();

        //chooser.showOpenDialog(null);

        chooser.showDialog(null, "Seleziona");
        File file = chooser.getSelectedFile();
        String filename = file.getName();
        System.out.println("You have selected: " + filename);
    }
}