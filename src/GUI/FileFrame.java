package GUI;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFileChooser;

public class FileFrame implements ActionListener {

    private boolean salva;
    private JFrame conf;
    private File f;

    private JFileChooser chooser;

    public FileFrame(String titolo, boolean salva) {

        this.salva = salva;

        chooser = new JFileChooser()
        {
            public void approveSelection(){
                ;
            }
        };

        chooser.setDialogTitle(titolo);

        if(salva)
            chooser.showDialog(null, "Salva");
        else
            chooser.showOpenDialog(null);

        f = chooser.getSelectedFile();
        operazione();
    }

    private void operazione(){
        if(salva){
            System.out.println("Salva "+f.getAbsolutePath());

            if(f.exists()) {
                conf = new JFrame("Conferma salvataggio");
                JPanel p = new JPanel();
                p.setLayout(new BorderLayout());

                JPanel t1 = new JPanel();
                t1.add(new JLabel(f.getName() + " è già esistente."));

                JPanel t2 = new JPanel();
                t2.add(new JLabel("Sostituire il file?"));

                JPanel b = new JPanel();

                JButton s = new JButton("Sì");
                JButton n = new JButton("No");

                s.addActionListener(this);
                n.addActionListener(this);

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


    @Override
    public void actionPerformed(ActionEvent e) {
        String scelta = e.getActionCommand();

        if(scelta.equals("Sì"))
            chooser.approveSelection();

        //chiude la finestra
        conf.dispatchEvent(new WindowEvent(conf, WindowEvent.WINDOW_CLOSING));
    }
}