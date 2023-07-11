package Panels;

import Data.MyTableModel;
import Listeners.TableMouseAdapter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * Classe che gestisce il layout del pannello principale inizializzando la tabella e gli altri pannelli del progetto
 * @author Christofer Fanò
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    private final JTable table;
    private final ButtonPanel bp;
    public MainPanel() {
        super();

        setLayout(new BorderLayout());


        MyTableModel model = new MyTableModel();
        table = new JTable(model);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setDefaultRenderer(Float.class, centerRenderer);

        TableRowSorter<MyTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        JScrollPane s = new JScrollPane(table);
        add(s, BorderLayout.CENTER);

        bp = new ButtonPanel(table);
        table.addMouseListener(new TableMouseAdapter(table, bp));
        FilterPanel fp = new FilterPanel(table, bp);

        add(fp, BorderLayout.NORTH);
        add(bp, BorderLayout.SOUTH);
    }

    /**
     * Restituisce l'istanza della JTable del progetto
     * @return JTable del progetto
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Restituisce l'istanza di ButtonPanel
     * @return ButtonPanel del progetto
     */
    public ButtonPanel getButtonPanel(){ return bp; }

    /**
     * Metodo che gestisce la stampa della tabella
     * @return true se si è verificato un errore, false altrimenti
     */
    public boolean printTable(){
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, null, null);
        } catch (java.awt.print.PrinterException e) {
           return true;
        }

        return false;
    }
}
