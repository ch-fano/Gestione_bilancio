package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JMenu gestione, memoria, esporta, stampa;
    private String testo_menu[] = {"Gestione bilancio","Salva o carica bilancio", "Esporta bilancio","Stampa bilancio"};
    private String testo_voci[] = {"Visualizza", "Ricerca", "Salva bilancio", "Carica bilancio", "Formato CSV", "Formato testo", "Formato Excel", "Inizia stampa"};
    private GestPanel gpanel = new GestPanel();

    public MainFrame(String titolo) {
        super(titolo);

        JMenuBar mb = new JMenuBar();

        gestione = new JMenu(testo_menu[0]);
        memoria = new JMenu(testo_menu[1]);
        esporta = new JMenu(testo_menu[2]);
        stampa = new JMenu(testo_menu[3]);

        JMenuItem g1 = new JMenuItem(testo_voci[0]);
        JMenuItem g2 = new JMenuItem(testo_voci[1]);
        JMenuItem m1 = new JMenuItem(testo_voci[2]);
        JMenuItem m2 = new JMenuItem(testo_voci[3]);
        JMenuItem e1 = new JMenuItem(testo_voci[4]);
        JMenuItem e2 = new JMenuItem(testo_voci[5]);
        JMenuItem e3 = new JMenuItem(testo_voci[6]);
        JMenuItem s = new JMenuItem(testo_voci[7]);

        gestione.add(g1);
        gestione.add(g2);

        memoria.add(m1);
        memoria.add(m2);

        esporta.add(e1);
        esporta.add(e2);
        esporta.add(e3);

        stampa.add(s);

        g1.addActionListener(this);
        g2.addActionListener(this);
        m1.addActionListener(this);
        m2.addActionListener(this);
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        s.addActionListener(this);

        mb.add(gestione);
        mb.add(memoria);
        mb.add(esporta);
        mb.add(stampa);

        setJMenuBar(mb);

        add(gpanel);
        gpanel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String button = e.getActionCommand();

        if (button.equals(testo_voci[0])){
            gpanel.setVisible(true);
            pack();
        }
        if (button.equals(testo_voci[1])){

        }
        if (button.equals(testo_voci[2]) || button.equals(testo_voci[3])){
            FileFrame ff = new FileFrame(button);

            ff.pack();
            ff.setLocationRelativeTo(null);
            ff.setVisible(true);
        }
        if (button.equals(testo_voci[4]) || button.equals(testo_voci[5]) || button.equals(testo_voci[6])){

        }
        if (button.equals(testo_voci[7])){

        }
    }
}
