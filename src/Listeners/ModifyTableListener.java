package Listeners;

import Frames.InsertFrame;
import Data.MyTableModel;
import Panels.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che gestisce l'inserimento, la modifica e la cancellazione di voci dalla tabella
 * @author Christofer Fanò
 */
public class ModifyTableListener implements ActionListener {
    private final JTable table;
    private final ButtonPanel bp;

    /**
     * Costruttore che inizializza la tabella e il ButtonPanel del progetto
     * @param table JTable del progetto
     * @param bp ButtonPanel del progetto
     */
    public ModifyTableListener(JTable table, ButtonPanel bp) {
        this.table = table;
        this.bp = bp;
    }

    /**
     * Chiama la classe InsertFrame nel caso in cui sia da aggiungere o modificare una voce, altrimenti elimina la voce
     * @param e evento che genera la chiama all'actionPerformed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        InsertFrame f;
        MyTableModel model = (MyTableModel) table.getModel();

        int row = table.getSelectedRow();
        if(row!=-1)
            row = table.convertRowIndexToModel(row);


        if(name.equals("Add")) {
            f = new InsertFrame("Inserisci voce", model, bp);
            f.setLocationRelativeTo(null);
        }
        else {
            if (name.equals("Delete")) {
                if (row != -1) {
                    model.deleteRecord(row);
                    bp.calculateTotal();
                }
            } else {
                f = new InsertFrame("Modifica voce", model, bp, row);
                f.setLocationRelativeTo(null);
            }
        }
    }
}
