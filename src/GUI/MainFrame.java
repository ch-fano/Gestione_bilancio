package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {

    private JMenu salva, carica, esporta, stampa;
    private String testo_menu[] = {"Salva bilancio","Carica bilancio", "Esporta bilancio","Stampa bilancio"};
    private String testo_voci[] = {"Salva bilancio", "Carica bilancio", "Formato CSV", "Formato testo", "Formato Excel", "Inizia stampa"};
    private GestPanel gpanel = new GestPanel();

    public MainFrame(String titolo) {
        super(titolo);

        JMenuBar mb = new JMenuBar();

        salva = new JMenu(testo_menu[0]);
        carica = new JMenu(testo_menu[1]);
        esporta = new JMenu(testo_menu[2]);
        stampa = new JMenu(testo_menu[3]);

        JMenuItem s = new JMenuItem(testo_voci[0]);
        JMenuItem c = new JMenuItem(testo_voci[1]);
        JMenuItem e1 = new JMenuItem(testo_voci[2]);
        JMenuItem e2 = new JMenuItem(testo_voci[3]);
        JMenuItem e3 = new JMenuItem(testo_voci[4]);
        JMenuItem st = new JMenuItem(testo_voci[5]);

        salva.add(s);

        carica.add(c);

        esporta.add(e1);
        esporta.add(e2);
        esporta.add(e3);

        stampa.add(st);

        s.addActionListener(this);
        c.addActionListener(this);
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        st.addActionListener(this);

        mb.add(salva);
        mb.add(carica);
        mb.add(esporta);
        mb.add(stampa);

        setJMenuBar(mb);

        add(gpanel);
        gpanel.setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String button = e.getActionCommand();

        if (button.equals(testo_voci[0])){
            FileFrame ff = new FileFrame(button);

            ff.pack();
            ff.setLocationRelativeTo(null);
            ff.setVisible(true);
        }
        if (button.equals(testo_voci[1])){
            FileFrame ff = new FileFrame(button);

            ff.pack();
            ff.setLocationRelativeTo(null);
            ff.setVisible(true);
        }
        if (button.equals(testo_voci[2]) || button.equals(testo_voci[3]) || button.equals(testo_voci[4])){

        }
        if (button.equals(testo_voci[5])){

        }
    }
}
