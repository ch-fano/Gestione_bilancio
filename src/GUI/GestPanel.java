package GUI;

import Dati.Voce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestPanel extends JPanel implements ActionListener {

    protected JLabel l;     //probabilmente mi servirà protected perchè verrà modificata all'inserimento di una voce

    public GestPanel() {
        super();

        Voce [] v = new Voce[1];
        v[0] = new Voce("12","saad","11");

        setLayout(new GridLayout(3,1));

        MyTableModel modello = new MyTableModel(v);
        JScrollPane s = new JScrollPane(new JTable(modello));

        l = new JLabel("Totale: 0€");

        add(new JLabel("Scelta data da implementare"));
        add(s);

        JPanel p1 = new JPanel();
        JButton b = new JButton("Inserisci voce");
        b.addActionListener(this);

        p1.add(l);
        p1.add(b);

        add(p1);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JFrame prova = new JFrame("Prova");
        prova.setLocationRelativeTo(null);
        prova.setVisible(true);
    }
}
