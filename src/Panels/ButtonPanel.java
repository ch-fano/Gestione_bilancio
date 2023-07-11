package Panels;

import Listeners.ModifyTableListener;

import javax.swing.*;
import java.awt.*;


/**
 * Classe che crea i bottoni di inserimento e cancellazione e calcola il totale degli importi delle voci della tabella
 * @author Christofer Fanò
 */
public class ButtonPanel extends JPanel {

    private final JLabel total = new JLabel("Totale: 0€");
    private final JTable table;

    /**
     * Costruttore che inizializza i bottoni di inserimento e cancellazione
     * @param table JTable del progetto da memorizzare per il conto del totale
     */
    public ButtonPanel(JTable table) {
        super();

        this.table = table;
        setLayout(new BorderLayout());

        JButton b_add = new JButton("Inserisci");
        JButton b_del = new JButton("Elimina");

        b_add.addActionListener(new ModifyTableListener(table, this));
        b_del.addActionListener(new ModifyTableListener(table, this));

        JPanel b_panel = new JPanel();
        b_panel.add(b_add);
        b_panel.add(b_del);

        JPanel tot_panel = new JPanel();
        tot_panel.add(total);

        add(b_panel, BorderLayout.NORTH);
        add(tot_panel, BorderLayout.CENTER);
    }

    /**
     * Metodo che conta il totale degli importi delle voci visibili nella tabella
     */
    public void calculateTotal(){
        float tot = 0;
        for(int row=0; row < table.getRowCount(); row++)
            tot += (Float) table.getModel().getValueAt(table.convertRowIndexToModel(row), 2);

        total.setText("Totale: "+tot+"€");
    }

}
