package GUI;

import javax.swing.*;

import java.awt.*;
import java.io.File;

import javax.swing.JFileChooser;

public class FileFrame {

    private boolean salva;

    public FileFrame(String titolo, boolean salva) {

        this.salva = salva;

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(titolo);

        if(salva)
            chooser.showDialog(null, "Salva");
        else
            chooser.showOpenDialog(null);

        File file = chooser.getSelectedFile();

        operazione(file);
    }

    private void operazione(File f){
        if(salva){
            System.out.println("Salva "+f.getAbsolutePath());
            if(f.exists() && !f.isDirectory()) {

                JFrame conf = new JFrame("Conferma salvataggio");
                JPanel p = new JPanel();
                p.setLayout(new BorderLayout());

                JPanel t1 = new JPanel();
                t1.add(new JLabel(f.getName()+" è già esistente."));

                JPanel t2 = new JPanel();
                t2.add(new JLabel("Sostituire il file?"));

                JPanel b = new JPanel();

                JButton s = new JButton("Sì");
                JButton n = new JButton("No");

                b.add(s);
                b.add(n);

                p.add(t1, BorderLayout.NORTH);
                p.add(t2, BorderLayout.CENTER);
                p.add(b, BorderLayout.SOUTH);

                conf.add(p);

                conf.setLocationRelativeTo(null);
                conf.pack();
                conf.setVisible(true);

            }
        }
        else
        {
            System.out.println("Carica "+f.getAbsolutePath());
        }
    }
}