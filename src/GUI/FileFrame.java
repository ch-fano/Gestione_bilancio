package GUI;

import javax.swing.*;
import java.awt.*;

public class FileFrame  extends JFrame {

    private JTextField nomefile;

    private JRadioButton salva, carica;
    private JButton ok;

    public FileFrame(String titolo) {
        super(titolo);

        JPanel np = new JPanel();
        nomefile = new JTextField(20);
        np.add(new JLabel("Nome file"));
        np.add(nomefile);

        JPanel cp = new JPanel();
        salva = new JRadioButton("Salva");
        carica = new JRadioButton("Carica");

        ButtonGroup grp = new ButtonGroup();
        grp.add(salva);
        grp.add(carica);
        cp.add(salva);
        cp.add(carica);

        JPanel sp = new JPanel();
        ok = new JButton("OK");
        sp.add(ok);

        add(np, BorderLayout.NORTH);
        add(cp, BorderLayout.CENTER);
        add(sp, BorderLayout.SOUTH);
    }
}
